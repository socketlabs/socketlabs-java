package com.socketLabs.injectionApi.core;

import com.socketLabs.injectionApi.SendResponse;
import com.socketLabs.injectionApi.core.serialization.InjectionResponseParser;
import okhttp3.*;
import okhttp3.Request.Builder;


import java.io.*;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class HttpRequest {

    /**
     * Enumeration of HTTP Request Methods
     */
    public enum HttpRequestMethod {
        GET,
        POST,
        PUT,
        DELETE;
    }

    /** The HTTP Request Method to use */
    private HttpRequestMethod method;
    /** The URL to use for the HTTP request */
    private String endPointUrl;
    /** The HTTP request body to send */
    private String body;
    /** The Proxy to use when making the HTTP request */
    private Proxy proxy;
    /** The headers to add to the HTTP Request */
    private Map<String, String> headers = new HashMap<>();
    private int timeout;


    /**
     * Creates a new instance of the HTTP Request class
     * @param method HTTpRequestMethod
     * @param endPointUrl String
     * @param timeout int
     */
    public HttpRequest(HttpRequestMethod method, String endPointUrl, int timeout) {
        this.method = method;
        this.endPointUrl = endPointUrl;
        this.timeout = timeout;
    }

    /**
     * Sets the HTTP request body
     * @param value String
     */
    public void setBody(String value) {
        this.body = value;
    }

    /**
     * Sets the headers for the HTTP Request
     * @param key String
     * @param value String
     */
    public void setHeader(String key, String value) {
        this.headers.put(key, value);
    }

    /**
     * Sets the Proxy to use when making the HTTP request
     * @param value String
     */
    public void setProxy(Proxy value) { this.proxy = value; }

    /**
     * Media Type to use to force JSON
     */
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    /**
     * Send the HTTP Request
     * @return A SendResponse from the Injection Api response
     * @throws IOException in case of a network error.
     */
    public Response SendRequest()  throws IOException  {

        Call call = BuildClientCall();

        Response response = call.execute();

        return response;

    }

    /**
     * Send an HTTP Request asynchronously
     * @param callback the SendAsyncCallback.
     */
    public void SendAsyncRequest(final Callback callback) {

        Call call = BuildClientCall();

        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {

                callback.onResponse(call, response);
                // ...
            }

            public void onFailure(Call call, IOException ex) {
                callback.onFailure(call, ex);
            }
        });

    }


    /**
     * Build the HTTP Client call
     * @return Call
     */
    private Call BuildClientCall() {

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .connectTimeout(this.timeout, TimeUnit.SECONDS)
                .writeTimeout(this.timeout, TimeUnit.SECONDS)
                .readTimeout(this.timeout, TimeUnit.SECONDS)
                .callTimeout(this.timeout, TimeUnit.SECONDS);

        OkHttpClient client = clientBuilder.build();

        if (this.proxy != null)
            client = clientBuilder
                    .proxy(this.proxy)
                    .build();

        Builder builder = new Builder().url(this.endPointUrl);

        //add request method
        RequestBody reqBody = RequestBody.create(JSON, this.body);
        switch (this.method) {
            case POST:
                builder.post(reqBody);
                break;
            case GET:
                builder.get();
                break;
            case PUT:
                builder.put(reqBody);
                break;
            case DELETE:
                builder.delete(reqBody);
                break;
        }

        //add request header
        for (Map.Entry<String, String> header : this.headers.entrySet()) {
            builder.addHeader(header.getKey(), header.getValue());
        }

        return client.newCall(builder.build());

    }

    /**
     * Parse the Response into a SendResponse
     * @param response the response from the HTTP request
     * @return SendResponse
     * @throws IOException
     */
    private SendResponse ParseResponse(Response response) throws IOException {

        InjectionResponseParser parser = new InjectionResponseParser();
        return parser.Parse(response);

    }
}
