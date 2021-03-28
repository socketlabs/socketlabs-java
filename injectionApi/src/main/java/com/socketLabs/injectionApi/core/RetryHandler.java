package com.socketLabs.injectionApi.core;

import com.socketLabs.injectionApi.RetrySettings;
import com.socketLabs.injectionApi.SendResponse;

import com.socketLabs.injectionApi.core.serialization.InjectionResponseParser;
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

    private Set<Integer> ErrorStatusCodes = new HashSet<>(Arrays.asList(500, 502, 503, 504));

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

        int attempts = 0;

        do {
            Duration waitInterval = retrySettings.getNextWaitInterval(attempts);

            try{
                Response response = httpRequest.SendRequest();
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

//            catch ()
        } while (true);

    }

}
