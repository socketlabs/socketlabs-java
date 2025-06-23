package com.socketLabs.injectionApi.core;
import com.socketLabs.injectionApi.RetrySettings;
import com.socketLabs.injectionApi.core.serialization.InjectionResponseParser;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class RetryHandler {

    private final RetrySettings retrySettings;
    private int attempts = 0;

    private final Set<Integer> ErrorStatusCodes = new HashSet<>(Arrays.asList(500, 502, 503, 504));
    private final Set<Class< ? extends Exception>> Exceptions = new HashSet<Class< ? extends Exception>>(Arrays.asList(
            SocketTimeoutException.class,
            InterruptedIOException.class
            ));

    /**
     *
     * Creates a new instance of the RetryHandler.
     * @param settings A RetrySettings instance.
     */
    public RetryHandler(RetrySettings settings){
        this.retrySettings = settings;
    }


    public CloseableHttpResponse send(CloseableHttpClient httpClient, HttpPost httpPost) throws IOException, InterruptedException {

        if (retrySettings.getMaximumNumberOfRetries() == 0) {
            CloseableHttpResponse response = httpClient.execute(httpPost);
            return response;
        }

        do {

            Duration waitInterval = retrySettings.getNextWaitInterval(attempts);

            try{
                CloseableHttpResponse response = httpClient.execute(httpPost);
                if (ErrorStatusCodes.contains(response.getStatusLine().getStatusCode()))
                    throw new IOException("Received Http Status Code : " + response.getStatusLine().getStatusCode());
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

    public void sendAsync (CloseableHttpAsyncClient httpAsyncClient, HttpPost httpPost, final FutureCallback<HttpResponse> callback) throws IOException, InterruptedException{

        InjectionResponseParser parser = new InjectionResponseParser();
        Duration waitInterval = retrySettings.getNextWaitInterval(attempts);

        httpAsyncClient.start();
        final Future<HttpResponse> future = httpAsyncClient.execute(httpPost,
                new FutureCallback<HttpResponse>() {

                    public void completed(final HttpResponse response) {

                        if (ErrorStatusCodes.contains(response.getStatusLine().getStatusCode()) && attempts <= retrySettings.getMaximumNumberOfRetries()){
                            attempts++;

                            try {
                                TimeUnit.MILLISECONDS.sleep(waitInterval.toMillis());
                                sendAsync(httpAsyncClient, httpPost, callback);
                            }
                            catch (IOException ioException) {
                                callback.failed(ioException);
                            }
                            catch (InterruptedException interruptedException) {
                                callback.failed(interruptedException);
                            }

                        }

                        else {
                            callback.completed(response);
                        }

                    }

                    public void failed(final Exception exception) {

                        if(Exceptions.contains(exception.getClass()) && attempts <= retrySettings.getMaximumNumberOfRetries()) {
                            attempts++;

                            try {
                                TimeUnit.MILLISECONDS.sleep(waitInterval.toMillis());
                                sendAsync(httpAsyncClient, httpPost, callback);
                            }
                            catch (IOException ioException) {
                                callback.failed(ioException);
                            }
                            catch (InterruptedException interruptedException) {
                                callback.failed(interruptedException);
                            }

                        }
                        else {
                            attempts = retrySettings.getMaximumNumberOfRetries() + 1;
                            callback.failed(exception);
                        }

                    }

                    public void cancelled() {
                        callback.cancelled();
                    }

                });

        httpAsyncClient.close();
    }

}
