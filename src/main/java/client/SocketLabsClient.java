package client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import interfaces.BasicMessage;
import interfaces.BulkMessage;
import interfaces.MessageBase;
import models.EmailAddress;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SocketLabsClient implements client.interfaces.SocketLabsClient {

    private static final String ENDPOINT_URL = "https://inject.socketlabs.com/api/v1/email";

    private int serverId;
    private String apiKey;
    private ObjectMapper mapper = new ObjectMapper();

    public SocketLabsClient(int serverId, String apiKey) {
        this.serverId = serverId;
        this.apiKey = apiKey;
    }

    @Override
    public SendResponse send(BasicMessage message) {
        URL url;
        HttpURLConnection connection = null;
        InjectionRequest injectionRequest;
        List<MessageBase> messages = new ArrayList<>();
        messages.add(message);
        injectionRequest = new InjectionRequest(String.valueOf(this.serverId), this.apiKey, messages);

        try {
            url = new URL(ENDPOINT_URL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            DataOutputStream request = new DataOutputStream(connection.getOutputStream());
            request.writeBytes(mapper.writeValueAsString(injectionRequest));
            request.flush();
            request.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            InputStream is = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while((line = reader.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            reader.close();

            return mapper.readValue(response.toString(), SendResponse.class);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }

        return null;
    }

    @Override
    public SendResponse send(BulkMessage message) {
        return null;
    }

    public static SendResponse QuickSend(int serverId, String apiKey, String toAddress, String fromAddress, String subject, String htmlContent, String textContent)  {

        SocketLabsClient client = new SocketLabsClient(serverId, apiKey);

        models.BasicMessage message = new models.BasicMessage();
        message.addToAddress(new EmailAddress(toAddress));
        message.setFrom(new EmailAddress(fromAddress));
        message.setSubject(subject);
        message.setHtmlBody(htmlContent);
        message.setPlainTextBody(textContent);

        return client.send(message);
    }
}
