package client;

import interfaces.BasicMessage;
import interfaces.BulkMessage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class SocketLabsClient implements client.interfaces.SocketLabsClient {

    private String apiKey;
    private String endpointUrl = "https://inject.socketlabs.com/api/v1/email";

    public SocketLabsClient(int serverId, String apiKey) {
        int serverId1 = serverId;
        this.apiKey = apiKey;
    }

    @Override
    public SendResponse send(BasicMessage message) {
        return null;
    }

    @Override
    public SendResponse send(BulkMessage message) {
        return null;
    }

    public SendResponse QuickSend() throws IOException {
        URL url = new URL(endpointUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");

        return null;
    }
}
