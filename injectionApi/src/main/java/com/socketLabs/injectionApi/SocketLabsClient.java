package com.socketLabs.injectionApi;

import com.google.common.collect.Lists;
import com.socketLabs.injectionApi.core.*;
import com.socketLabs.injectionApi.core.serialization.InjectionRequestFactory;
import com.socketLabs.injectionApi.core.serialization.InjectionResponseParser;
import com.socketLabs.injectionApi.message.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.message.BasicHeader;
import org.apache.http.nio.reactor.ConnectingIOReactor;


/**
 * SocketLabsClient is a wrapper for the SocketLabs Injection API that makes it easy to send messages and parse responses.
 */
public class SocketLabsClient implements SocketLabsClientAPI {

    private final int serverId;
    private final String apiKey;
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

    private final String VERSION = "1.4.4";
    private final String userAgent  = String.format("SocketLabs-java/%s(%s)", VERSION, Package.getPackage("java.util").getImplementationVersion());

    private List<Header> getDefaultHeaders() {
        List<Header> headers = Lists.newArrayList(new BasicHeader(HttpHeaders.USER_AGENT, this.userAgent));
        headers.add(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json"));
        headers.add(new BasicHeader(HttpHeaders.ACCEPT, "application/json"));
        return headers;
    }

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

        ApiKeyParser keyParser = new ApiKeyParser();
        ApiKeyParseResult parseResult = keyParser.Parse(this.apiKey);

        if (parseResult != null && parseResult != ApiKeyParseResult.Success)
        {
            SendResponse errorResponse = new SendResponse();
            errorResponse.setResult(SendResult.AuthenticationValidationFailed);
            return errorResponse;
        }

        String body = new InjectionRequestFactory(this.serverId, this.apiKey).GenerateRequest(message);
        HttpPost httpPost = getHttpPost(body);

        CloseableHttpClient client = buildHttpClient();
        RetryHandler retryHandler = new RetryHandler(new RetrySettings(this.numberOfRetries));
        CloseableHttpResponse response = retryHandler.send(client, httpPost);

        InjectionResponseParser parser = new InjectionResponseParser();
        return parser.Parse(response);
    }

    /**
     * Synchronously sends a basic email message and returns the response from the Injection API.
     * @param message A BasicMessage object to be sent.
     * @return A SendResponse of an SocketLabsClient send request.
     * @param httpClient A CloseableHttpClient instance to use when making http calls.
     * @throws Exception exception
     */
    @Override
    public SendResponse send(BasicMessage message, CloseableHttpClient httpClient) throws Exception {

        SendResponse result = Validate(message);
        if (result.getResult() != SendResult.Success)
            return result;

        ApiKeyParser keyParser = new ApiKeyParser();
        ApiKeyParseResult parseResult = keyParser.Parse(this.apiKey);

        if (parseResult != null && parseResult != ApiKeyParseResult.Success)
        {
            SendResponse errorResponse = new SendResponse();
            errorResponse.setResult(SendResult.AuthenticationValidationFailed);
            return errorResponse;
        }

        String body = new InjectionRequestFactory(this.serverId, this.apiKey).GenerateRequest(message);
        HttpPost httpPost = getHttpPost(body);

        CloseableHttpClient client = buildHttpClient();
        RetryHandler retryHandler = new RetryHandler(new RetrySettings(this.numberOfRetries));
        CloseableHttpResponse response = retryHandler.send(client, httpPost);

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

        ApiKeyParser keyParser = new ApiKeyParser();
        ApiKeyParseResult parseResult = keyParser.Parse(this.apiKey);

        if (parseResult != null && parseResult != ApiKeyParseResult.Success)
        {
            SendResponse errorResponse = new SendResponse();
            errorResponse.setResult(SendResult.AuthenticationValidationFailed);
            return errorResponse;
        }

        String body = new InjectionRequestFactory(this.serverId, this.apiKey).GenerateRequest(message);
        HttpPost httpPost = getHttpPost(body);

        CloseableHttpClient client = buildHttpClient();
        RetryHandler retryHandler = new RetryHandler( new RetrySettings(this.numberOfRetries));
        CloseableHttpResponse response = retryHandler.send(client, httpPost);

        InjectionResponseParser parser = new InjectionResponseParser();
        return parser.Parse(response);

    }

    /**
     * Synchronously sends a bulk email message and returns the response from the Injection API.
     * @param message A BulkMessage object to be sent.
     * @return A SendResponse of an SocketLabsClient send request.
     * @param httpClient A CloseableHttpClient instance to use when making http calls.
     * @throws Exception exception
     */
    @Override
    public SendResponse send(BulkMessage message, CloseableHttpClient httpClient) throws Exception {

        SendResponse result = Validate(message);
        if (result.getResult() != SendResult.Success)
            return result;

        ApiKeyParser keyParser = new ApiKeyParser();
        ApiKeyParseResult parseResult = keyParser.Parse(this.apiKey);

        if (parseResult != null && parseResult != ApiKeyParseResult.Success)
        {
            SendResponse errorResponse = new SendResponse();
            errorResponse.setResult(SendResult.AuthenticationValidationFailed);
            return errorResponse;
        }

        String body = new InjectionRequestFactory(this.serverId, this.apiKey).GenerateRequest(message);
        HttpPost httpPost = getHttpPost(body);

        CloseableHttpClient client = buildHttpClient();
        RetryHandler retryHandler = new RetryHandler(new RetrySettings(this.numberOfRetries));
        CloseableHttpResponse response = retryHandler.send(client, httpPost);

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

        ApiKeyParser keyParser = new ApiKeyParser();
        ApiKeyParseResult parseResult = keyParser.Parse(this.apiKey);

        if (parseResult != null && parseResult != ApiKeyParseResult.Success)
        {
            SendResponse errorResponse = new SendResponse();
            errorResponse.setResult(SendResult.AuthenticationValidationFailed);
            callback.onResponse(errorResponse);
        }

        String body = new InjectionRequestFactory(this.serverId, this.apiKey).GenerateRequest(message);
        HttpPost httpPost = getHttpPost(body);

        InjectionResponseParser parser = new InjectionResponseParser();

        CloseableHttpAsyncClient client = buildHttpAsyncClient();
        RetryHandler retryHandler = new RetryHandler(new RetrySettings(this.numberOfRetries));

        retryHandler.sendAsync(client, httpPost, new FutureCallback<HttpResponse>() {
            public void completed(final HttpResponse response) {
                try {
                    callback.onResponse(parser.Parse(response));
                } catch (IOException ex) {
                    callback.onError(ex);
                }
            }

            public void failed(final Exception ex) {
                callback.onError(ex);
            }

            public void cancelled() {
                callback.onError(new Exception("sendAsync cancelled"));
            }

        });

    }

    /**
     * Asynchronously sends a basic email message and returns the response from the Injection API.
     * @param message A BasicMessage object to be sent.
     * @param callback A SendAsyncCallback to handle error and response from the Injection API.
     * @param httpAsyncClient A CloseableHttpAsyncClient instance to use when making http calls.
     * @throws Exception exception
     */
    @Override
    public void sendAsync(BasicMessage message, final SendAsyncCallback callback, CloseableHttpAsyncClient httpAsyncClient) throws Exception {

        SendResponse result = Validate(message);
        if (result.getResult() != SendResult.Success) {
            callback.onResponse(result);
            return;
        }

        ApiKeyParser keyParser = new ApiKeyParser();
        ApiKeyParseResult parseResult = keyParser.Parse(this.apiKey);

        if (parseResult != null && parseResult != ApiKeyParseResult.Success)
        {
            SendResponse errorResponse = new SendResponse();
            errorResponse.setResult(SendResult.AuthenticationValidationFailed);
            callback.onResponse(errorResponse);
        }

        String body = new InjectionRequestFactory(this.serverId, this.apiKey).GenerateRequest(message);
        HttpPost httpPost = getHttpPost(body);

        InjectionResponseParser parser = new InjectionResponseParser();

        CloseableHttpAsyncClient client = buildHttpAsyncClient();
        RetryHandler retryHandler = new RetryHandler(new RetrySettings(this.numberOfRetries));

        retryHandler.sendAsync(client, httpPost, new FutureCallback<HttpResponse>() {
            public void completed(final HttpResponse response) {
                try {
                    callback.onResponse(parser.Parse(response));
                } catch (IOException ex) {
                    callback.onError(ex);
                }
            }

            public void failed(final Exception ex) {
                callback.onError(ex);
            }

            public void cancelled() {
                callback.onError(new Exception("sendAsync cancelled"));
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

        ApiKeyParser keyParser = new ApiKeyParser();
        ApiKeyParseResult parseResult = keyParser.Parse(this.apiKey);

        if (parseResult != null && parseResult != ApiKeyParseResult.Success)
        {
            SendResponse errorResponse = new SendResponse();
            errorResponse.setResult(SendResult.AuthenticationValidationFailed);
            callback.onResponse(errorResponse);
        }

        String body = new InjectionRequestFactory(this.serverId, this.apiKey).GenerateRequest(message);
        HttpPost httpPost = getHttpPost(body);

        InjectionResponseParser parser = new InjectionResponseParser();

        CloseableHttpAsyncClient client = buildHttpAsyncClient();
        RetryHandler retryHandler = new RetryHandler(new RetrySettings(this.numberOfRetries));

        retryHandler.sendAsync(client, httpPost, new FutureCallback<HttpResponse>() {
            public void completed(final HttpResponse response) {
                try {
                    callback.onResponse(parser.Parse(response));
                } catch (IOException ex) {
                    callback.onError(ex);
                }
            }

            public void failed(final Exception ex) {
                callback.onError(ex);
            }

            public void cancelled() {
                callback.onError(new Exception("sendAsync cancelled"));
            }

        });

    }

    /**
     * Asynchronously sends a bulk email message and returns the response from the Injection API.
     * @param message A BulkMessage object to be sent.
     * @param callback A SendAsyncCallback to handle error and response from the Injection API.
     * @param httpAsyncClient A CloseableHttpAsyncClient instance to use when making http calls.
     * @throws Exception exception
     */
    @Override
    public void sendAsync(BulkMessage message, final SendAsyncCallback callback, CloseableHttpAsyncClient httpAsyncClient) throws Exception {

        SendResponse result = Validate(message);
        if (result.getResult() != SendResult.Success) {
            callback.onResponse(result);
            return;
        }

        ApiKeyParser keyParser = new ApiKeyParser();
        ApiKeyParseResult parseResult = keyParser.Parse(this.apiKey);

        if (parseResult != null && parseResult != ApiKeyParseResult.Success)
        {
            SendResponse errorResponse = new SendResponse();
            errorResponse.setResult(SendResult.AuthenticationValidationFailed);
            callback.onResponse(errorResponse);
        }

        String body = new InjectionRequestFactory(this.serverId, this.apiKey).GenerateRequest(message);
        HttpPost httpPost = getHttpPost(body);

        InjectionResponseParser parser = new InjectionResponseParser();

        CloseableHttpAsyncClient client = buildHttpAsyncClient();
        RetryHandler retryHandler = new RetryHandler(new RetrySettings(this.numberOfRetries));

        retryHandler.sendAsync(client, httpPost, new FutureCallback<HttpResponse>() {
            public void completed(final HttpResponse response) {
                try {
                    callback.onResponse(parser.Parse(response));
                } catch (IOException ex) {
                    callback.onError(ex);
                }
            }

            public void failed(final Exception ex) {
                callback.onError(ex);
            }

            public void cancelled() {
                callback.onError(new Exception("sendAsync cancelled"));
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

    private CloseableHttpClient buildHttpClient() {

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(20);
        cm.setValidateAfterInactivity(10000);

        List<Header> headers = getDefaultHeaders();
        headers.add(new BasicHeader(HttpHeaders.AUTHORIZATION, "Bearer " + this.apiKey));

        return HttpClients.custom()
                .setDefaultHeaders(headers)
                .setConnectionManager(cm)
                .setDefaultRequestConfig(getDefaultRequestConfig())
                .build();

    }
    private CloseableHttpAsyncClient buildHttpAsyncClient() throws Exception {

        final ConnectingIOReactor ioReactor = new DefaultConnectingIOReactor();
        PoolingNHttpClientConnectionManager cm = new PoolingNHttpClientConnectionManager(ioReactor);
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(20);

        List<Header> headers = getDefaultHeaders();
        headers.add(new BasicHeader(HttpHeaders.AUTHORIZATION, "Bearer " + this.apiKey));

        return HttpAsyncClients.custom()
                .setDefaultHeaders(headers)
                .setConnectionManager(cm)
                .setDefaultRequestConfig(getDefaultRequestConfig())
                .build();

    }

    private RequestConfig getDefaultRequestConfig() {

        return RequestConfig.custom()
                .setConnectTimeout(this.requestTimeout)
                .setConnectionRequestTimeout(this.requestTimeout)
                .setSocketTimeout(this.requestTimeout)
                .build();
    }

    private HttpPost getHttpPost(String body) throws UnsupportedEncodingException {

        HttpPost httpPost = new HttpPost(this.endPointUrl);
        StringEntity entity = new StringEntity(body);
        httpPost.setEntity(entity);

        if (this.proxy != null) {
            InetSocketAddress address = (InetSocketAddress) this.proxy.address();
            RequestConfig requestConfig = RequestConfig.copy(getDefaultRequestConfig())
                    .setProxy(new HttpHost(address.getHostName(), address.getPort()))
                    .build();
            httpPost.setConfig(requestConfig);
        }
        return httpPost;
    }
}
