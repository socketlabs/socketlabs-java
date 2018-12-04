package com.socketLabs.injectionApi;

import com.socketLabs.injectionApi.message.Attachment;
import com.socketLabs.injectionApi.message.BasicMessage;
import com.socketLabs.injectionApi.message.CustomHeader;
import com.socketLabs.injectionApi.message.EmailAddress;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        SocketLabsClient client = new SocketLabsClient(17067, "r4W7SaJi38FyLp6k5NKs");

        BasicMessage message = new BasicMessage();

        message.setMessageId("ComplexExample");
        message.setMailingId("BasicSend");

        message.setSubject("Sending A Complex Test Message (basic Send)");

        message.setHtmlBody("<html><body><h1>Sending A Complex Test Message</h1><p>This is the html Body of my message.</p><h2>Embedded Image:</h2><p><img src=\"cid:bus\" /></p></body></html>");
        message.setPlainTextBody("This is the Plain Text Body of my message.");

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

        System.out.println(message.toString());

        // send the message
        SendResponse response =  client.send(message);



    }
}
