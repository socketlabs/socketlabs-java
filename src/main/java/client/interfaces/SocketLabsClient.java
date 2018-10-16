package client.interfaces;

import client.SendResponse;
import interfaces.BasicMessage;
import interfaces.BulkMessage;

public interface SocketLabsClient {

    // Async basic

    // Async bulk

    // Sync basic

    SendResponse send(BasicMessage message);

    // Sync bulk

    SendResponse send(BulkMessage message);
}
