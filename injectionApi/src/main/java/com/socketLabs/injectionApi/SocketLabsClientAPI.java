package com.socketLabs.injectionApi;

import com.socketLabs.injectionApi.core.SendAsyncCallback;
import com.socketLabs.injectionApi.message.BasicMessage;
import com.socketLabs.injectionApi.message.BulkMessage;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;

/**
 * SocketLabsClientAPI is an interface that defines the SocketLabs Injection API client and its public methods.
 */
public interface SocketLabsClientAPI {

    /**
     * Asynchronously sends a basic email message and returns the response from the Injection API.
     * @param message A BasicMessage object to be sent.
     * @param callback A SendAsyncCallback to handle error and response from the Injection API.
     * @throws Exception exception
     */
    void sendAsync(BasicMessage message, final SendAsyncCallback callback) throws Exception;

    /**
     * Asynchronously sends a basic email message and returns the response from the Injection API.
     * @param message A BasicMessage object to be sent.
     * @param callback A SendAsyncCallback to handle error and response from the Injection API.
     * @param httpAsyncClient A CloseableHttpAsyncClient instance to use when making http calls.
     * @throws Exception exception
     */
    void sendAsync(BasicMessage message, final SendAsyncCallback callback, CloseableHttpAsyncClient httpAsyncClient) throws Exception;

    /**
     * Asynchronously sends a bulk email message and returns the response from the Injection API.
     * @param message A BulkMessage object to be sent.
     * @param callback A SendAsyncCallback to handle error and response from the Injection API.
     * @throws Exception exception
     */
    void sendAsync(BulkMessage message, final SendAsyncCallback callback) throws Exception;


    /**
     * Asynchronously sends a bulk email message and returns the response from the Injection API.
     * @param message A BulkMessage object to be sent.
     * @param callback A SendAsyncCallback to handle error and response from the Injection API.
     * @param httpAsyncClient A CloseableHttpAsyncClient instance to use when making http calls.
     * @throws Exception exception
     */
    void sendAsync(BulkMessage message, final SendAsyncCallback callback, CloseableHttpAsyncClient httpAsyncClient) throws Exception;


    /**
     * Synchronously sends a basic email message and returns the response from the Injection API.
     * @param message A BasicMessage object to be sent.
     * @return A SendResponse of an SocketLabsClient send request.
     * @throws Exception exception
     */
    SendResponse send(BasicMessage message) throws Exception;

    /**
     * Synchronously sends a basic email message and returns the response from the Injection API.
     * @param message A BasicMessage object to be sent.
     * @return A SendResponse of an SocketLabsClient send request.
     * @param httpClient A CloseableHttpClient instance to use when making http calls.
     * @throws Exception exception
     */
    SendResponse send(BasicMessage message, CloseableHttpClient httpClient) throws Exception;

    /**
     * Synchronously sends a bulk email message and returns the response from the Injection API.
     * @param message A BulkMessage object to be sent.
     * @return A SendResponse of an SocketLabsClient send request.
     * @throws Exception exception
     */
    SendResponse send(BulkMessage message) throws Exception;


    /**
     * Synchronously sends a bulk email message and returns the response from the Injection API.
     * @param message A BulkMessage object to be sent.
     * @return A SendResponse of an SocketLabsClient send request.
     * @param httpClient A CloseableHttpClient instance to use when making http calls.
     * @throws Exception exception
     */
    SendResponse send(BulkMessage message, CloseableHttpClient httpClient) throws Exception;
}
