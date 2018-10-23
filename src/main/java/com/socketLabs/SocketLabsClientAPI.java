package com.socketLabs;

import com.socketLabs.models.BasicMessage;
import com.socketLabs.models.BulkMessage;

public interface SocketLabsClientAPI {

    // Async basic

    // Async bulk

    // Sync basic

    SendResponse send(BasicMessage message) throws Exception;

    // Sync bulk

    SendResponse send(BulkMessage message) throws Exception;
}
