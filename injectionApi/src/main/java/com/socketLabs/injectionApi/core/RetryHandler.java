package com.socketLabs.injectionApi.core;
import com.socketLabs.injectionApi.RetrySettings;

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
import java.util.concurrent.CountDownLatch;
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
            return httpClient.execute(httpPost);
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

        Duration waitInterval = retrySettings.getNextWaitInterval(attempts);

        // Create a default CloseableHttpAsyncClient instance
        try {
            // Start the client
            httpAsyncClient.start();

            // Use a CountDownLatch to wait for the asynchronous operation to complete
            final CountDownLatch latch = new CountDownLatch(1);

            // Execute the request asynchronously with a FutureCallback
            httpAsyncClient.execute(httpPost, new FutureCallback<HttpResponse>() {
                @Override
                public void completed(final HttpResponse response) {
                    if (ErrorStatusCodes.contains(response.getStatusLine().getStatusCode()) && attempts <= retrySettings.getMaximumNumberOfRetries()){
                        attempts++;

                        try {
                            TimeUnit.MILLISECONDS.sleep(waitInterval.toMillis());
                            sendAsync(httpAsyncClient, httpPost, callback);
                        }
                        catch (IOException | InterruptedException ioException) {
                            callback.failed(ioException);
                        }
                    }

                    else {
                        callback.completed(response);
                    }
                    latch.countDown(); // Signal completion
                }

                @Override
                public void failed(final Exception exception) {
                    if(Exceptions.contains(exception.getClass()) && attempts <= retrySettings.getMaximumNumberOfRetries()) {
                        attempts++;

                        try {
                            TimeUnit.MILLISECONDS.sleep(waitInterval.toMillis());
                            sendAsync(httpAsyncClient, httpPost, callback);
                        }
                        catch (IOException | InterruptedException ioException) {
                            callback.failed(ioException);
                        }

                    }
                    else {
                        attempts = retrySettings.getMaximumNumberOfRetries() + 1;
                        callback.failed(exception);
                    }
                    latch.countDown(); // Signal completion even on failure
                }

                @Override
                public void cancelled() {
                    callback.cancelled();
                    latch.countDown(); // Signal completion on cancellation
                }
            });

            // Wait for the asynchronous operation to complete
            latch.await();

        } catch (Exception exception) {
            callback.failed(exception);
        } finally {
            httpAsyncClient.close();
        }



    }


}
