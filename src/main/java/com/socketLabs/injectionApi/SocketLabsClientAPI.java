package com.socketLabs.injectionApi;

import com.socketLabs.injectionApi.core.HttpCallback;
import com.socketLabs.injectionApi.models.BasicMessage;
import com.socketLabs.injectionApi.models.BulkMessage;

public interface SocketLabsClientAPI {

    // Async Basic
    SendResponse sendAsync(BasicMessage message, final HttpCallback callback) throws Exception;

    // Async Bulk
    void sendAsync(BulkMessage message, final HttpCallback callback) throws Exception;

    // Sync Basic
    SendResponse send(BasicMessage message) throws Exception;

    // Sync Bulk
    SendResponse send(BulkMessage message) throws Exception;
}
