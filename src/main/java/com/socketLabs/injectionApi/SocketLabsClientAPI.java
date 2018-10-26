package com.socketLabs.injectionApi;

import com.socketLabs.injectionApi.core.SendAsyncCallback;
import com.socketLabs.injectionApi.message.BasicMessage;
import com.socketLabs.injectionApi.message.BulkMessage;

public interface SocketLabsClientAPI {

    // Async basic
    SendResponse sendAsync(BasicMessage message, final SendAsyncCallback callback) throws Exception;

    // Async bulk
    void sendAsync(BulkMessage message, final SendAsyncCallback callback) throws Exception;

    // Sync basic
    SendResponse send(BasicMessage message) throws Exception;

    // Sync bulk
    SendResponse send(BulkMessage message) throws Exception;
}
