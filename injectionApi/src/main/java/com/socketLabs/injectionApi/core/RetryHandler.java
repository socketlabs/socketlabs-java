package com.socketLabs.injectionApi.core;

import com.socketLabs.injectionApi.RetrySettings;
import com.socketLabs.injectionApi.SendResponse;

import com.socketLabs.injectionApi.core.serialization.InjectionResponseParser;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
//import javax.ws.rs.ServerErrorException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RetryHandler {

    private HttpRequest httpRequest;
    private String endPointUrl;
    private RetrySettings retrySettings;
    private int attempts = 0;

    private Set<Integer> ErrorStatusCodes = new HashSet<>(Arrays.asList(500, 502, 503, 504));
    private Set<Class< ? extends Exception>> Exceptions = new HashSet<Class< ? extends Exception>>(Arrays.asList(
            SocketTimeoutException.class,
            InterruptedIOException.class
            ));

    /// <summary>
    /// Creates a new instance of the <c>RetryHandler</c>.
    /// </summary>
    /// <param name="request">A <c>HttpRequest</c> instance</param>
    /// <param name="endpointUrl">The SocketLabs Injection API endpoint Url</param>
    /// <param name="settings">A <c>RetrySettings</c> instance</param>
    public RetryHandler(HttpRequest request, String endpointUrl, RetrySettings settings){
        httpRequest = request;
        endPointUrl = endpointUrl;
        retrySettings = settings;
    }

    public SendResponse send() throws IOException, InterruptedException {

        InjectionResponseParser parser = new InjectionResponseParser();

        if (retrySettings.getMaximumNumberOfRetries() == 0){
            Response response =  httpRequest.SendRequest();
            return parser.Parse(response);
        }

//        int attempts = 0;

        do {
            Duration waitInterval = retrySettings.getNextWaitInterval(attempts);

            try{
                Response response = httpRequest.SendRequest();
                System.out.println("RESPONSE : " + response.networkResponse().code());
                if (ErrorStatusCodes.contains(response.networkResponse().code()))
                    throw new IOException();
                return parser.Parse(response);
            }

            catch (SocketTimeoutException exception)
            {
                attempts++;
                System.out.println("Exception Occured in SocketTimeOutException : " + exception + " Attempt : " + attempts);
                if (attempts > retrySettings.getMaximumNumberOfRetries()) throw new SocketTimeoutException();
                TimeUnit.MILLISECONDS.sleep(waitInterval.toMillis());

            }

            catch (InterruptedIOException exception)
            {
                attempts++;
                System.out.println("Exception Occured in InterruptedIOException : " + exception + " Attempt : " + attempts);
                if (attempts > retrySettings.getMaximumNumberOfRetries()) throw new InterruptedIOException();
                TimeUnit.MILLISECONDS.sleep(waitInterval.toMillis());

            }

            catch (IOException exception)
            {
                attempts++;
                System.out.println("Exception Occured in IOException : " + exception + " Attempt : " + attempts);
                if (attempts > retrySettings.getMaximumNumberOfRetries()) throw new IOException();
                TimeUnit.MILLISECONDS.sleep(waitInterval.toMillis());
            }

        } while (true);

    }

    public void sendAsync (final SendAsyncCallback callback) throws IOException, InterruptedException{
        InjectionResponseParser parser = new InjectionResponseParser();
        do {
            if(attempts <= retrySettings.getMaximumNumberOfRetries()){
                final SendResponse[] sendResp = {new SendResponse()};
                httpRequest.SendAsyncRequest(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        if(Exceptions.contains(e.getClass())){
                            attempts++;
                            System.out.println("onFailure Exception : " + e.getClass());
                            try {
                                sendAsync(callback);
                            } catch (IOException exception) {
                                exception.printStackTrace();
                            } catch (InterruptedException interruptedException) {
                                interruptedException.printStackTrace();
                            }
                        }
                        else{
                            attempts = retrySettings.getMaximumNumberOfRetries() + 1;
                            System.out.println("Different Exception with attempt : " + attempts);
                            callback.onError(e);
                        }

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        System.out.println("Response code : " + response.networkResponse().code());
                        if (ErrorStatusCodes.contains(response.networkResponse().code())){
                            attempts++;
                            try {
                                sendAsync(callback);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        else {
                            sendResp[0] = parser.Parse(response);
                            System.out.println("Response : " + sendResp[0]);
                            callback.onResponse(sendResp[0]);
                        }


                    }
                });
                System.out.println("Attempt : " + attempts);
            }

        } while (false);
    }

}
