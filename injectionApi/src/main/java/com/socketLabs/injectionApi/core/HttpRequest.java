package com.socketLabs.injectionApi.core;

import com.socketLabs.injectionApi.SendResponse;
import com.socketLabs.injectionApi.core.serialization.InjectionResponseParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;

import java.io.*;
import java.util.concurrent.Future;

/**
 *
 */
public class HttpRequest {

    /**
     * Creates a new instance of the HTTP Request class
     */
    public HttpRequest() {    }

    /**
     * Send the HTTP Request
     * @param httpClient A CloseableHttpClient instance to use when making http calls.
     * @param httpPost A HttpPost instance to send.
     * @return A SendResponse from the Injection Api response
     * @throws IOException in case of a network error.
     */
    public CloseableHttpResponse SendRequest(CloseableHttpClient httpClient, HttpPost httpPost)  throws IOException  {
        return httpClient.execute(httpPost);
    }

    /**
     * Send an HTTP Request
     * @param httpAsyncClient A CloseableHttpAsyncClient instance to use when making http calls.
     * @param httpPost A HttpPost instance to send.
     * @param callback the SendAsyncCallback.
     * @throws IOException in case of a network error.
     */
    public void SendAsyncRequest(CloseableHttpAsyncClient httpAsyncClient, HttpPost httpPost, final FutureCallback<HttpResponse> callback) throws IOException {

        httpAsyncClient.start();
        final Future<HttpResponse> future = httpAsyncClient.execute(httpPost,
                new FutureCallback<HttpResponse>() {

                    public void completed(final HttpResponse response) {
                        callback.completed(response);
                    }

                    public void failed(final Exception exception) {
                        callback.failed(exception);
                    }

                    public void cancelled() {
                        callback.cancelled();
                    }

                });

        httpAsyncClient.close();

    }

    /**
     * Parse the Response into a SendResponse
     * @param response the response from the HTTP request
     * @return SendResponse
     */
    private SendResponse ParseResponse(HttpResponse response) throws IOException {
        InjectionResponseParser parser = new InjectionResponseParser();
        return parser.Parse(response);

    }
}
