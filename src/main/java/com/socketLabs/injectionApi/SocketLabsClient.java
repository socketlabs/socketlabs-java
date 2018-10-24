package com.socketLabs.injectionApi;

import com.socketLabs.injectionApi.core.HttpRequest;
import com.socketLabs.injectionApi.core.HttpResponse;
import com.socketLabs.injectionApi.core.SendValidator;
import com.socketLabs.injectionApi.core.serialization.InjectionRequestFactory;
import com.socketLabs.injectionApi.core.serialization.InjectionResponseParser;
import com.socketLabs.injectionApi.models.*;


public class SocketLabsClient implements SocketLabsClientAPI {

    private int serverId;
    private String apiKey;
    private String endPointUrl = "https://inject.socketlabs.com/api/v1/email";

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

    @Override
    public SendResponse send(BasicMessage message) throws Exception {

        SendValidator validator = new SendValidator();

        SendResponse result = validator.ValidateCredentials(this.serverId, this.apiKey);
        if (result.getResult() != SendResult.Success)
            return result;

        result = validator.ValidateMessage(message);
        if (result.getResult() != SendResult.Success)
            return result;

        InjectionRequestFactory injectionRequest = new InjectionRequestFactory(this.serverId, this.apiKey);
        String requestJson = injectionRequest.GenerateRequest(message);

        HttpRequest request = new HttpRequest(HttpRequestMethod.POST, this.endPointUrl);
        request.setHeader("User-Agent", this.userAgent);
        request.setHeader("content-type", "application/json");
        request.setBody(requestJson);

        InjectionResponseParser parser = new InjectionResponseParser();
        return parser.Parse(request.SendRequest());

    }

    @Override
    public SendResponse send(BulkMessage message) throws Exception {

        SendValidator validator = new SendValidator();

        SendResponse result = validator.ValidateCredentials(this.serverId, this.apiKey);
        if (result.getResult() != SendResult.Success)
            return result;

        result = validator.ValidateMessage(message);
        if (result.getResult() != SendResult.Success)
            return result;

        InjectionRequestFactory injectionRequest = new InjectionRequestFactory(this.serverId, this.apiKey);
        String requestJson = injectionRequest.GenerateRequest(message);

        HttpRequest request = new HttpRequest(HttpRequestMethod.POST, this.endPointUrl);
        request.setHeader("User-Agent", this.userAgent);
        request.setBody(requestJson);

        InjectionResponseParser parser = new InjectionResponseParser();
        return parser.Parse(request.SendRequest());

    }

/*
    public static SendResponse QuickSend(int serverId, String apiKey, String toAddress, String fromAddress, String subject, String htmlContent, String textContent)  {

        SocketLabsClient client = new SocketLabsClient(serverId, apiKey);

        models.BasicMessage message = new models.BasicMessage();
        message.addToAddress(new EmailAddress(toAddress));
        message.setFrom(new EmailAddress(fromAddress));
        message.setSubject(subject);
        message.setHtmlBody(htmlContent);
        message.setPlainTextBody(textContent);

        return client.send(message);

    return null;
    }
*/




}
