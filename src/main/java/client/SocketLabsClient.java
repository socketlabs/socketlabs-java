package client;

import interfaces.BasicMessage;
import interfaces.BulkMessage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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

        AddressResult addressResult = new AddressResult("ross.brazuk@socketlabs.com", false, "234");
        List<AddressResult> addressResults = new ArrayList<>();
        addressResults.add(addressResult);

        return new SendResponse(SendResult.EmptySubject, "Transaction Reciept", addressResults);
    }
}
