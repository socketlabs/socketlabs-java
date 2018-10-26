package com.socketLabs.injectionApi.core;

import com.socketLabs.injectionApi.SendResponse;
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
    public SendResponse SendRequest()  throws IOException  {

        Call call = BuildClientCall();

        Response response = call.execute();

        return ParseResponse(response);

    }


    /**
     * Send an HTTP Request asynchronously
     * @param callback the SendAsyncCallback.
     */
    public void SendAsyncRequest(final SendAsyncCallback callback) {

        Call call = BuildClientCall();

        final SendResponse[] sendResp = {new SendResponse()};

        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {

                sendResp[0] = ParseResponse(response);
                callback.onResponse(sendResp[0]);
                // ...
            }

            public void onFailure(Call call, IOException ex) {
                callback.onError(ex);
            }
        });

    }



    private Call BuildClientCall() {

        OkHttpClient client = new OkHttpClient();

        if (this.proxy != null)
            client = new OkHttpClient.Builder()
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

    private SendResponse ParseResponse(Response response) throws IOException {

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

        InjectionResponseParser parser = new InjectionResponseParser();
        return parser.Parse(resp);

    }
}
