package com.socketLabs;

import com.socketLabs.core.SendValidator;
import com.socketLabs.core.serialization.InjectionRequestFactory;
import com.socketLabs.core.serialization.InjectionResponseParser;
import com.socketLabs.models.BasicMessage;
import com.socketLabs.models.BulkMessage;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

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

    private final String VERSION = "1.0.0";
    private final String userAgent  = String.format("socketlabs-sava/$s(%s)", VERSION, Package.getPackage("java.lang.String").getImplementationVersion());

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

        InjectionRequestFactory injectionRequest = new InjectionRequestFactory(this.serverId, this.apiKey);

        /*


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
    public SendResponse send(BulkMessage message) throws Exception {

        SendValidator validator = new SendValidator();

        SendResponse result = validator.ValidateCredentials(this.serverId, this.apiKey);
        if (result.getResult() != SendResult.Success)
            return result;

        result = validator.ValidateMessage(message);
        if (result.getResult() != SendResult.Success)
            return result;

        InjectionRequestFactory injectionRequest = new InjectionRequestFactory(this.serverId, this.apiKey);

        String requestJson = injectionRequest.GenerateRequest(message);
        // Parse and send the message here

        return sendPost(requestJson);

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



    // HTTP POST request
    private SendResponse sendPost(String json) throws Exception {

        URL obj = new URL(this.getEndPointUrl());
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", this.userAgent);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(json);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();

        System.out.println("\nSending 'POST' request to URL : " + this.getEndPointUrl());
        System.out.println("Post body : " + json);
        System.out.println("Response Code : " + responseCode);

        InputStream stream = con.getInputStream();

        InjectionResponseParser parser = new InjectionResponseParser();
        SendResponse response = parser.Parse(stream);

        return response;
    }


}
