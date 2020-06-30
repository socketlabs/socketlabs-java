package examples.basic;

import com.socketLabs.injectionApi.SendResponse;
import com.socketLabs.injectionApi.SocketLabsClient;
import com.socketLabs.injectionApi.message.BasicMessage;
import com.socketLabs.injectionApi.message.EmailAddress;
import examples.*;

import javax.swing.*;

public class BasicSendWithAmpBodyExample implements Example {

    /*For more information on AMP Html, visit the following link: amp.dev/documentation */

    public SendResponse RunExample ()  throws Exception {

        BasicMessage message = new BasicMessage();

        message.setSubject("Sending AMP Test Message (Basic Send)");
        message.setHtmlBody("<html><body><h1>Sending A Test Message</h1><p>This HTML will show if AMP is not supported.</p></body></html>");
        message.setAmpBody("<!doctype html>" +
                "<html amp4email>" +
                "<head>" +
                "  <meta charset=\"utf-8\">" +
                "  <script async src=\"https://cdn.ampproject.org/v0.js\"></script>" +
                "  <style amp4email-boilerplate>body{visibility:hidden}</style>" +
                "  <style amp-custom>" +
                "    h1 {" +
                "      margin: 1rem;" +
                "    }" +
                "  </style>" +
                "</head>" +
                "<body>" +
                "  <h1>This is the AMP Html Body of my message</h1>" +
                "</body>" +
                "</html>");

        message.setFrom(new EmailAddress("from@example.com"));
        message.addToEmailAddress("recipient1@example.com");
        message.addToEmailAddress("recipient2@example.com", "Recipient #2");
        message.addToEmailAddress(new EmailAddress("recipient3@example.com"));
        message.addToEmailAddress(new EmailAddress("recipient4@example.com", "Recipient #4"));

        // create the client
        SocketLabsClient client = new SocketLabsClient(ExampleConfig.ServerId, ExampleConfig.ApiKey);

        // send the message
        SendResponse response =  client.send(message);

        return response;

    }

}
