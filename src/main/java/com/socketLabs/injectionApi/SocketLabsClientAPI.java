package com.socketLabs.injectionApi;

import com.socketLabs.injectionApi.models.BasicMessage;
import com.socketLabs.injectionApi.models.BulkMessage;

public interface SocketLabsClientAPI {

    // Async basic

    // Async bulk

    // Sync basic

    SendResponse send(BasicMessage message) throws Exception;

    // Sync bulk

    SendResponse send(BulkMessage message) throws Exception;
}
