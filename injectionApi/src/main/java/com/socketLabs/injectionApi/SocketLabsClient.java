package com.socketLabs.injectionApi;

import com.socketLabs.injectionApi.core.*;
import com.socketLabs.injectionApi.core.serialization.InjectionRequestFactory;
import com.socketLabs.injectionApi.core.serialization.InjectionResponseParser;
import com.socketLabs.injectionApi.message.*;

import java.io.IOException;
import java.net.Proxy;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * SocketLabsClient is a wrapper for the SocketLabs Injection API that makes it easy to send messages and parse responses.
 */
public class SocketLabsClient implements SocketLabsClientAPI {

    private int serverId;
    private String apiKey;
    private String endPointUrl = "https://inject.socketlabs.com/api/v1/email";
    private Proxy proxy;
    private int requestTimeout = 100;
    private int numberOfRetries = 0;

    /**
     * Set the SocketLabs Injection API endpoint Url
     * @param value String
     */
    public void setEndPointUrl(String value) {
        this.endPointUrl = value;
    }

    /**
     * Set the Timeout period used by the HttpClient (in seconds)
     * @param value int
     */
    public void setRequestTimeout(int value) { this.requestTimeout = value; }

    /**
     * Set the Number of Retries used by the HttpClient
     * @param value int
     */
    public void  setNumberOfRetries(int value) { this.numberOfRetries = value; }

    private final String VERSION = "1.0.0";
    private final String userAgent  = String.format("SocketLabs-java/%s(%s)", VERSION, Package.getPackage("java.util").getImplementationVersion());

    /**
     * Creates a new instance of the SocketLabsClient.
     * @param serverId Your SocketLabs ServerId number.
     * @param apiKey Your SocketLabs Injection API key.
     */
    public SocketLabsClient(int serverId, String apiKey) {
        this.serverId = serverId;
        this.apiKey = apiKey;
    }

    /**
     *
     * Creates a new instance of the SocketLabsClient.
     * @param serverId Your SocketLabs ServerId number.
     * @param apiKey Your SocketLabs Injection API key.
     * @param optionalProxy The Proxy you would like to use.
     */
    public SocketLabsClient(int serverId, String apiKey, Proxy optionalProxy) {
        this.serverId = serverId;
        this.apiKey = apiKey;
        this.proxy = optionalProxy;
    }

    /**
     * Synchronously sends a basic email message and returns the response from the Injection API.
     * @param message A BasicMessage object to be sent.
     * @return A SendResponse of an SocketLabsClient send request.
     * @throws Exception exception
     */
    @Override
    public SendResponse send(BasicMessage message) throws Exception {

        SendResponse result = Validate(message);
        if (result.getResult() != SendResult.Success)
            return result;

        HttpRequest request = buildHttpRequest(this.proxy);

        ApiKeyParser keyParser = new ApiKeyParser();
        ApiKeyParseResult parseResult = keyParser.Parse(this.apiKey);

        if (parseResult != null && parseResult == ApiKeyParseResult.Success)
        {
            request.setHeader("Authorization", "Bearer " + this.apiKey);
        }

        request.setBody(new InjectionRequestFactory(this.serverId, this.apiKey).GenerateRequest(message));
        RetryHandler retryHandler = new RetryHandler(request, this.endPointUrl, new RetrySettings(this.numberOfRetries));
        Response response = retryHandler.send();

        InjectionResponseParser parser = new InjectionResponseParser();
        return parser.Parse(response);
    }

    /**
     * Synchronously sends a bulk email message and returns the response from the Injection API.
     * @param message A BulkMessage object to be sent.
     * @return A SendResponse of an SocketLabsClient send request.
     * @throws Exception exception
     */
    @Override
    public SendResponse send(BulkMessage message) throws Exception {

        SendResponse result = Validate(message);
        if (result.getResult() != SendResult.Success)
            return result;

        HttpRequest request = buildHttpRequest(this.proxy);

        ApiKeyParser keyParser = new ApiKeyParser();
        ApiKeyParseResult parseResult = keyParser.Parse(this.apiKey);

        if (parseResult != null && parseResult == ApiKeyParseResult.Success)
        {
            request.setHeader("Authorization", "Bearer " + this.apiKey);
        }

        request.setBody(new InjectionRequestFactory(this.serverId, this.apiKey).GenerateRequest(message));

        RetryHandler retryHandler = new RetryHandler(request, this.endPointUrl, new RetrySettings(this.numberOfRetries));
        Response response = retryHandler.send();

        InjectionResponseParser parser = new InjectionResponseParser();
        return parser.Parse(response);

    }

    /**
     * Asynchronously sends a basic email message and returns the response from the Injection API.
     * @param message A BasicMessage object to be sent.
     * @param callback A SendAsyncCallback to handle error and response from the Injection API.
     * @throws Exception exception
     */
    @Override
    public void sendAsync(BasicMessage message, final SendAsyncCallback callback) throws Exception {

        SendResponse result = Validate(message);
        if (result.getResult() != SendResult.Success) {
            callback.onResponse(result);
            return;
        }

        HttpRequest request = buildHttpRequest(this.proxy);

        ApiKeyParser keyParser = new ApiKeyParser();
        ApiKeyParseResult parseResult = keyParser.Parse(this.apiKey);

        if (parseResult != null && parseResult == ApiKeyParseResult.Success)
        {
            request.setHeader("Authorization", "Bearer " + this.apiKey);
        }

        request.setBody(new InjectionRequestFactory(this.serverId, this.apiKey).GenerateRequest(message));

        RetryHandler retryHandler = new RetryHandler(request, this.endPointUrl, new RetrySettings(this.numberOfRetries));
        InjectionResponseParser parser = new InjectionResponseParser();

        retryHandler.sendAsync(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                callback.onResponse(parser.Parse(response));
            }

            public void onFailure(Call call, IOException ex) {
                callback.onError(ex);
            }
        });

    }

    /**
     * Asynchronously sends a bulk email message and returns the response from the Injection API.
     * @param message A BulkMessage object to be sent.
     * @param callback A SendAsyncCallback to handle error and response from the Injection API.
     * @throws Exception exception
     */
    @Override
    public void sendAsync(BulkMessage message, final SendAsyncCallback callback) throws Exception {

        SendResponse result = Validate(message);
        if (result.getResult() != SendResult.Success) {
            callback.onResponse(result);
            return;
        }

        HttpRequest request = buildHttpRequest(this.proxy);

        ApiKeyParser keyParser = new ApiKeyParser();
        ApiKeyParseResult parseResult = keyParser.Parse(this.apiKey);

        if (parseResult != null && parseResult == ApiKeyParseResult.Success)
        {
            request.setHeader("Authorization", "Bearer " + this.apiKey);
        }
        
        request.setBody(new InjectionRequestFactory(this.serverId, this.apiKey).GenerateRequest(message));

        RetryHandler retryHandler = new RetryHandler(request, this.endPointUrl, new RetrySettings(this.numberOfRetries));
        InjectionResponseParser parser = new InjectionResponseParser();

        retryHandler.sendAsync(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                callback.onResponse(parser.Parse(response));
            }

            public void onFailure(Call call, IOException ex) {
                callback.onError(ex);
            }
        });


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

        HttpRequest request = new HttpRequest(HttpRequest.HttpRequestMethod.POST, this.endPointUrl, this.requestTimeout);

        request.setHeader("User-Agent", this.userAgent);
        request.setHeader("content-type", "application/json");
        request.setHeader("Accept", "application/json");

        if(optionalProxy != null) {
            request.setProxy(optionalProxy);
        }

        return request;
    }

}
