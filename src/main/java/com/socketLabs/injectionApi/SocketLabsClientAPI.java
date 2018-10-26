package com.socketLabs.injectionApi;

import com.socketLabs.injectionApi.core.SendAsyncCallback;
import com.socketLabs.injectionApi.message.BasicMessage;
import com.socketLabs.injectionApi.message.BulkMessage;

/**
 * SocketLabsClientAPI is an interface that defines the SocketLabs Injection API client and its public methods.
 */
public interface SocketLabsClientAPI {

    /**
     * Asynchronously sends a basic email message and returns the response from the Injection API.
     * @param message A BasicMessage object to be sent.
     * @param callback A SendAsyncCallback to handle error and response from the Injection API.
     * @throws Exception
     */
    void sendAsync(BasicMessage message, final SendAsyncCallback callback) throws Exception;

    /**
     * Asynchronously sends a bulk email message and returns the response from the Injection API.
     * @param message A BulkMessage object to be sent.
     * @param callback A SendAsyncCallback to handle error and response from the Injection API.
     * @throws Exception
     */
    void sendAsync(BulkMessage message, final SendAsyncCallback callback) throws Exception;

    /**
     * Synchronously sends a basic email message and returns the response from the Injection API.
     * @param message A BasicMessage object to be sent.
     * @return A SendResponse of an SocketLabsClient send request.
     * @throws Exception
     */
    SendResponse send(BasicMessage message) throws Exception;

    /**
     * Synchronously sends a bulk email message and returns the response from the Injection API.
     * @param message A BulkMessage object to be sent.
     * @return A SendResponse of an SocketLabsClient send request.
     * @throws Exception
     */
    SendResponse send(BulkMessage message) throws Exception;
}
