package com.socketLabs.injectionApi.core;

import com.socketLabs.injectionApi.core.serialization.InjectionResponseParser;
import okhttp3.*;
import okhttp3.Request.Builder;

import java.io.*;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    private HttpRequestMethod method;
    private String endPointUrl;
    private String body;
    private Proxy proxy;
    private Map<String, String> headers = new HashMap<>();

    public HttpRequest(HttpRequestMethod method, String endPointUrl) {
        this.method = method;
        this.endPointUrl = endPointUrl;

    }

    public void setBody(String value) {
        this.body = value;
    }
    public void setHeader(String key, String value) {
        this.headers.put(key, value);
    }
    public void setProxy(Proxy value) { this.proxy = value; }

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    /**
     * Send the HTTP Request
     * @return A SendResponse from the Injection Api response
     * @throws Exception in case of a network error.
     */
    public HttpResponse SendRequest()  throws IOException  {

        OkHttpClient client = new OkHttpClient();

        if (this.proxy != null)
            client = new OkHttpClient.Builder()
                .proxy(this.proxy)
                .build();

        Builder builder = new Builder().url("https://inject.socketlabs.com/api/v1/email");

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

        // make the call
        Response response = client.newCall(builder.build()).execute();

        // TODO: remove (used for debugging)
        int responseCode = response.networkResponse().code();
        String responseMessage = response.networkResponse().message();
        String requestMethod = response.request().method();
        System.out.println(String.format("\nSending '%s' request to URL : %s", requestMethod, this.endPointUrl));
        System.out.println("Request body : ");
        System.out.println(this.body);
        System.out.println();
        System.out.println("Response Code : " + responseCode);
        System.out.println("Response Message : " + responseMessage);
        // TODO: end remove

        //HttpResponse response = new HttpResponse(responseCode, ParseStream(stream));
        HttpResponse resp = new HttpResponse(response.networkResponse().code(), response.body().string());
        resp.setResponseMessage(response.networkResponse().message());
        return resp;
    }

    /**
     * Attempt an API call. This method executes the API call asynchronously
     * on an internal thread pool. If the call is rate limited, the thread
     * will retry up to the maximum configured time. The supplied callback
     * will be called in the event of an error, or a successful response.
     * @param callback the callback.
     */
    public void SendAsyncRequest(final HttpCallback callback) {

        new Thread(() -> {

            HttpResponse response = new HttpResponse();

            try {
                response = SendRequest();
            } catch (IOException ex) {
                // Stop retrying if there is a network error.
                callback.onError(ex);
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }

            InjectionResponseParser parser = new InjectionResponseParser();
            try {
                callback.onResponse(parser.Parse(response));
            } catch (IOException ex) {
                // Stop retrying if there is a network error.
                callback.onError(ex);
                return;
            }
            return;

        }).start();

    }

}
