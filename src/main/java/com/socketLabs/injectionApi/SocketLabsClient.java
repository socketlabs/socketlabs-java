package com.socketLabs.injectionApi;

import com.socketLabs.injectionApi.core.*;
import com.socketLabs.injectionApi.core.serialization.InjectionRequestFactory;
import com.socketLabs.injectionApi.message.*;

import java.net.Proxy;

public class SocketLabsClient implements SocketLabsClientAPI {

    private int serverId;
    private String apiKey;
    private String endPointUrl = "https://inject.socketlabs.com/api/v1/email";
    private Proxy proxy;

    public void setEndPointUrl(String endPointUrl) {
        this.endPointUrl = endPointUrl;
    }

    private final String VERSION = "1.0.0";
    private final String userAgent  = String.format("SocketLabs-java/%s(%s)", VERSION, Package.getPackage("java.util").getImplementationVersion());

    // TODO: Need Proxy - Property and Constructor


    public SocketLabsClient(int serverId, String apiKey) {
        this.serverId = serverId;
        this.apiKey = apiKey;
    }

    public SocketLabsClient(int serverId, String apiKey, Proxy optionalProxy) {
        this.serverId = serverId;
        this.apiKey = apiKey;
        this.proxy = optionalProxy;
    }


    @Override
    public SendResponse send(BasicMessage message) throws Exception {

        SendResponse result = Validate(message);
        if (result.getResult() != SendResult.Success)
            return result;

        HttpRequest request = buildHttpRequest(this.proxy);
        request.setBody(new InjectionRequestFactory(this.serverId, this.apiKey).GenerateRequest(message));

        return request.SendRequest();

    }

    @Override
    public SendResponse send(BulkMessage message) throws Exception {

        SendResponse result = Validate(message);
        if (result.getResult() != SendResult.Success)
            return result;

        HttpRequest request = buildHttpRequest(this.proxy);
        request.setBody(new InjectionRequestFactory(this.serverId, this.apiKey).GenerateRequest(message));

        return request.SendRequest();

    }

    @Override
    public SendResponse sendAsync(BasicMessage message, final SendAsyncCallback callback) throws Exception {

        SendResponse result = Validate(message);
        if (result.getResult() != SendResult.Success) {
            callback.onResponse(result);
            return result;
        }

        HttpRequest request = buildHttpRequest(this.proxy);
        request.setBody(new InjectionRequestFactory(this.serverId, this.apiKey).GenerateRequest(message));

        request.SendAsyncRequest(callback);

        return result;
    }

    @Override
    public void sendAsync(BulkMessage message, final SendAsyncCallback callback) throws Exception {

        SendResponse result = Validate(message);
        if (result.getResult() != SendResult.Success) {
            callback.onResponse(result);
            return;
        }

        HttpRequest request = buildHttpRequest(this.proxy);
        request.setBody(new InjectionRequestFactory(this.serverId, this.apiKey).GenerateRequest(message));

        request.SendAsyncRequest(callback);

    }


    private SendResponse Validate(BasicMessage message) {

        SendValidator validator = new SendValidator();

        SendResponse result = validator.ValidateCredentials(this.serverId, this.apiKey);
        if (result.getResult() != SendResult.Success)
            return result;

        return validator.ValidateMessage(message);

    }

    private SendResponse Validate(BulkMessage message) {

        SendValidator validator = new SendValidator();

        SendResponse result = validator.ValidateCredentials(this.serverId, this.apiKey);
        if (result.getResult() != SendResult.Success)
            return result;

        return validator.ValidateMessage(message);

    }

    private HttpRequest buildHttpRequest(Proxy optionalProxy) {

        HttpRequest request = new HttpRequest(HttpRequestMethod.POST, this.endPointUrl);

        request.setHeader("User-Agent", this.userAgent);
        request.setHeader("content-type", "application/json");
        request.setHeader("Accept", "application/json");

        if(optionalProxy != null) {
            request.setProxy(optionalProxy);
        }

        return request;
    }



}
