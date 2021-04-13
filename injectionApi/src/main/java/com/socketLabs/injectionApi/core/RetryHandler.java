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

        if (retrySettings.getMaximumNumberOfRetries() == 0) {
            Response response =  httpRequest.SendRequest();
            return response;
        }

        do {

            Duration waitInterval = retrySettings.getNextWaitInterval(attempts);

            try{

                Response response = httpRequest.SendRequest();
                if (ErrorStatusCodes.contains(response.networkResponse().code()))
                    throw new IOException("Received Http Status Code : " + response.networkResponse().code());
                return response;

            }
            catch (SocketTimeoutException exception)
            {
                attempts++;
                if (attempts > retrySettings.getMaximumNumberOfRetries()) throw new SocketTimeoutException();
                TimeUnit.MILLISECONDS.sleep(waitInterval.toMillis());
            }
            catch (InterruptedIOException exception)
            {
                attempts++;
                if (attempts > retrySettings.getMaximumNumberOfRetries()) throw new InterruptedIOException();
                TimeUnit.MILLISECONDS.sleep(waitInterval.toMillis());
            }
            catch (IOException exception)
            {
                attempts++;
                if (attempts > retrySettings.getMaximumNumberOfRetries()) throw new IOException(exception.getMessage());
                TimeUnit.MILLISECONDS.sleep(waitInterval.toMillis());
            }

        } while (true);

    }

    public void sendAsync (final SendAsyncCallback callback) throws IOException, InterruptedException{

        InjectionResponseParser parser = new InjectionResponseParser();
        Duration waitInterval = retrySettings.getNextWaitInterval(attempts);

        httpRequest.SendAsyncRequest(new Callback() {

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (ErrorStatusCodes.contains(response.networkResponse().code()) && attempts <= retrySettings.getMaximumNumberOfRetries()){

                    attempts++;

                    try {
                        TimeUnit.MILLISECONDS.sleep(waitInterval.toMillis());
                        sendAsync(callback);
                    }
                    catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }

                }

                else {
                    callback.onResponse(response);
                }

            }

            @Override
            public void onFailure(Call call, IOException exception) {

                if(Exceptions.contains(exception.getClass()) && attempts <= retrySettings.getMaximumNumberOfRetries()) {

                    attempts++;

                    try {
                        TimeUnit.MILLISECONDS.sleep(waitInterval.toMillis());
                        sendAsync(callback);
                    }

                    catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }

                }
                else {
                    attempts = retrySettings.getMaximumNumberOfRetries() + 1;
                    callback.onError(exception);
                }

            }

        });

    }

}
