package com.socketLabs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.socketLabs.core.SendValidator;
import com.socketLabs.models.BasicMessage;
import com.socketLabs.models.BulkMessage;

public class SocketLabsClient implements SocketLabsClientAPI {

    private int serverId;
    private String apiKey;
    private String endPointUrl = "https://inject.socketlabs.com/api/v1/email";

    public String getEndPointUrl() {
        return endPointUrl;
    }
    public void setEndPointUrl(String endPointUrl) {
        this.endPointUrl = endPointUrl;
    }

    // TODO: Need Proxy - Property and Constructor


    public SocketLabsClient(int serverId, String apiKey) {
        this.serverId = serverId;
        this.apiKey = apiKey;
    }

    @Override
    public SendResponse send(BasicMessage message) {

        SendValidator validator = new SendValidator();

        SendResponse result = validator.ValidateCredentials(this.serverId, this.apiKey);
        if (result.getResult() != SendResult.Success)
            return result;

        result = validator.ValidateMessage(message);
        if (result.getResult() != SendResult.Success)
            return result;


        /*
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
    */
        return null;

    }

    @Override
    public SendResponse send(BulkMessage message) {

        SendValidator validator = new SendValidator();

        SendResponse result = validator.ValidateCredentials(this.serverId, this.apiKey);
        if (result.getResult() != SendResult.Success)
            return result;

        result = validator.ValidateMessage(message);
        if (result.getResult() != SendResult.Success)
            return result;

        // Parse and send the message here

        return null;
    }

/*
    public static SendResponse QuickSend(int serverId, String apiKey, String toAddress, String fromAddress, String subject, String htmlContent, String textContent)  {

        SocketLabsClient client = new SocketLabsClient(serverId, apiKey);

        models.BasicMessage message = new models.BasicMessage();
        message.addToAddress(new EmailAddress(toAddress));
        message.setFrom(new EmailAddress(fromAddress));
        message.setSubject(subject);
        message.setHtmlBody(htmlContent);
        message.setPlainTextBody(textContent);

        return client.send(message);

    return null;
    }
*/

}
