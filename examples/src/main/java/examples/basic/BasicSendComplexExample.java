package examples.basic;

import com.socketLabs.injectionApi.SendResponse;
import com.socketLabs.injectionApi.SocketLabsClient;
import com.socketLabs.injectionApi.message.Attachment;
import com.socketLabs.injectionApi.message.BasicMessage;
import com.socketLabs.injectionApi.message.CustomHeader;
import com.socketLabs.injectionApi.message.EmailAddress;
import com.socketLabs.injectionApi.message.Metadata;
import examples.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BasicSendComplexExample implements Example {
    @Override
    public SendResponse RunExample() throws Exception {

        // Build the message
        // =========================
        BasicMessage message = new BasicMessage();

        message.setMessageId("ComplexExample");
        message.setMailingId("BasicSend");

        message.setSubject("Sending A Complex Test Message (Basic Send)");

        message.setHtmlBody("<html>" +
                "<body>" +
                "  <h1>Sending A Complex Test Message</h1>" +
                "  <p>This is the html Body of my message.</p>" +
                "  <h2>Embedded Image:</h2>" +
                "  <p><img src=\"cid:bus\" /></p>" +
                "</body>" +
                "</html>");

        message.setPlainTextBody("This is the Plain Text Body of my message.");

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
        message.setReplyTo(new EmailAddress("replyto@example.com"));

        // Adding To Recipients
        // =========================
        // Add Email Addresses using an Array
        List<EmailAddress> toRecipients = new ArrayList<>();
        toRecipients.add(new EmailAddress("recipient1@example.com"));
        toRecipients.add(new EmailAddress("recipient2@example.com", "Recipient #2"));
        message.setTo(toRecipients);

        // Add Email Addresses using new EmailAddress
        message.addToEmailAddress(new EmailAddress("recipient3@example.com",  "Recipient #3"));

        // Add Email Addresses using the addToEmailAddress function
        message.addToEmailAddress("recipient4@example.com");
        message.addToEmailAddress("recipient5@example.com", "Recipient #5");

        // Adding CC Recipients
        // =========================
        // Add Email Addresses using an Array
        List<EmailAddress>  ccRecipients = new ArrayList<>();
        ccRecipients.add(new EmailAddress("ccRecipients1@example.com"));
        ccRecipients.add(new EmailAddress("ccRecipients2@example.com", "Recipient #2"));
        message.setCc(ccRecipients);

        // Add Email Addresses using new EmailAddress
        message.addCcEmailAddress(new EmailAddress("ccRecipients3@example.com",  "Recipient #3" ));

        // Add Email Addresses using the addCcEmailAddress function
        message.addCcEmailAddress("ccRecipients4@example.com");
        message.addCcEmailAddress("ccRecipients5@example.com", "Recipient #5");

         // Adding Bcc Recipients
        // =========================
        // Add Email Addresses using an Array
        List<EmailAddress>  bccRecipients = new ArrayList<>();
        bccRecipients.add(new EmailAddress("bccRecipients1@example.com"));
        bccRecipients.add(new EmailAddress("bccRecipients2@example.com", "Recipient #2"));
        message.setBcc(bccRecipients);

        // Add Email Addresses using new EmailAddress
        message.addBccEmailAddress(new EmailAddress( "bccRecipients3@example.com",  "Recipient #3" ));

        // Add Email Addresses using the addBccEmailAddress function
        message.addBccEmailAddress("bccRecipients4@example.com");
        message.addBccEmailAddress("bccRecipients5@example.com", "Recipient #5");

        // Adding Attachments
        // =========================
        // Add Attachment directly to the Array
        List<Attachment>  attachments = new ArrayList<>();
        attachments.add(new Attachment("bus.png", "image/png", "src/main/java/examples/img/bus.png"));
        message.setAttachments(attachments);

        // Add Attachment using the addAttachments function
        Attachment attachment2 = new Attachment("bus2", "image/png", "src/main/java/examples/img/bus.png");
        attachment2.setContentId("bus");
        message.addAttachments(attachment2);

        // Add Attachment a filePath {string} to the array
        message.addAttachments(new Attachment("src/main/java/examples/html/SimpleEmail.html"));

        // using file stream
        File initialFile = new File("src/main/java/examples/img/bus.png");
        InputStream stream = new FileInputStream(initialFile);
        Attachment attachment4 = new Attachment("yellow-bus.png", "image/png", stream);

        // add custom headers to attachment
        attachment4.addCustomHeader("Color", "Yellow");
        attachment4.addCustomHeader("Place", "Beach");

        message.addAttachments(attachment4);

        // Adding Custom Headers
        // =========================
        // Add Custom Headers using an Array
        List<CustomHeader>  headers = new ArrayList<>();
        headers.add(new CustomHeader("example-type", "basic-send-complex"));
        headers.add(new CustomHeader("message-contains", "attachments, headers"));
        message.setCustomHeaders(headers);

        // Add Custom Headers directly to the Array
        message.addCustomHeader(new CustomHeader("message-has-attachments", "true"));

        // Add Custom Headers using the addCustomHeaders function
        message.addCustomHeader("testMessageHeader", "I am a message header");

        // Adding Metadata
        // =========================
        // Add Metadata using an Array
        List<Metadata>  metadata = new ArrayList<>();
        metadata.add(new Metadata("example-type", "basic-send-complex"));
        metadata.add(new Metadata("message-contains", "attachments, headers"));
        message.setMetadata(metadata);

        // Add Metadata directly to the Array
        message.addMetadata(new Metadata("message-has-attachments", "true"));

        // Add Metadata using the addMetadata function
        message.addMetadata("testMessageHeader", "I am a message header");

        // Adding Tags
        // =========================
        // Add Tags using an Array
        List<String>  tags = new ArrayList<>();
        tags.add("example-type:basic-send-complex");
        message.setTags(tags);

        // Add Tags directly to the Array
        message.addTags("message-has-attachments:true");

        // Add Tags using the addTags function
        message.addTags("I am a test message");


        // create the client
        SocketLabsClient client = new SocketLabsClient(ExampleConfig.ServerId, ExampleConfig.ApiKey);

        // send the message
        SendResponse response =  client.send(message);

        return response;


    }
}
