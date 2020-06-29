package examples.bulk;

import com.socketLabs.injectionApi.SendResponse;
import com.socketLabs.injectionApi.SocketLabsClient;
import com.socketLabs.injectionApi.message.BulkMessage;
import com.socketLabs.injectionApi.message.BulkRecipient;
import com.socketLabs.injectionApi.message.EmailAddress;
import examples.*;

public class BulkSendWithAmpBodyExample implements Example {
    @Override

    /*For more information on AMP Html, visit the following link: amp.dev/documentation */

    public SendResponse RunExample() throws Exception {

        BulkMessage message = new BulkMessage();

        message.setSubject("Sending A Test Message (Bulk Send)");
        message.setHtmlBody("<html><body><h1>Sending A Test Message</h1><p>This HTML will show if AMP is not supported.</p></body></html>");
        message.setAmpBody("<!doctype html>"
                + "<html amp4email>"
                + "<head>" + "  <meta charset=\"utf-8\">"
                + "  <script async src=\"https://cdn.ampproject.org/v0.js\"></script>"
                + "  <style amp4email-boilerplate>body{visibility:hidden}</style>"
                + "  <style amp-custom>"
                + "    h1 {"
                + "      margin: 1rem;"
                + "    }"
                + "  </style>"
                + "</head>"
                + "<body>"
                + "  <h1>This is the AMP Html Body of my message</h1>"
                + "</body>"
                + "</html>");

        message.setFrom(new EmailAddress("from@example.com"));
        message.setReplyTo(new EmailAddress("recipient@example.com"));

        message.getTo().add(new BulkRecipient("recipient1@example.com"));
        message.getTo().add(new BulkRecipient("recipient2@example.com", "Recipient #2"));
        message.getTo().add(new BulkRecipient("recipient3@example.com"));
        message.getTo().add(new BulkRecipient("recipient4@example.com", "Recipient #4"));

        // create the client
        SocketLabsClient client = new SocketLabsClient(ExampleConfig.ServerId, ExampleConfig.ApiKey);

        // send the message
        SendResponse response =  client.send(message);

        return response;
    }
}
