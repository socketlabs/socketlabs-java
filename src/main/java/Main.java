import client.SendResponse;
import client.SocketLabsClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import models.Attachment;
import models.BasicMessage;
import models.CustomHeader;
import models.EmailAddress;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
//        BasicMessage message = new BasicMessage();
//
//        List<EmailAddress> addresses = new ArrayList<>();
//
//        addresses.add(new EmailAddress("ross.brazuk@socketlabs.com", "Ross Brazuk"));
//
//        message.setTo(addresses);
//
//        message.setSubject("Sent from the Java SDK");
//
//        message.setPlainTextBody("Java test plain text body");
//
//        message.setHtmlBody("<h1>Java Test</h1><p>SDK test</p>");
//
//        message.setMailingId("java");
//
//        message.setMessageId("javaTest1");
//
//        message.setFrom(new EmailAddress("ross.brazuk@gekodesign.net", "Java SDK"));
//
//        message.setReplyTo(new EmailAddress("ross.brazuk@gekodesign.net", "Ross Brazuk"));
//
//        // Attachment
//
//        List<Attachment> attachments = new ArrayList<>();
//
//        Attachment attachment = new Attachment("Nic","image/jpeg","/Users/ross.brazuk/Desktop/nic.jpg");
//
//        attachments.add(attachment);
//
//        message.setAttachments(attachments);
//
//        // Custom Header
//
//        CustomHeader customHeader = new CustomHeader("custom-header", "test");
//
//        List<CustomHeader> customHeaders = new ArrayList<>();
//        customHeaders.add(customHeader);
//
//        message.setCustomHeaders(customHeaders);
//
//        SocketLabsClient client = new SocketLabsClient(17067, "r4W7SaJi38FyLp6k5NKs");
//
//        SendResponse response = client.send(message);
//
//        System.out.println(response);

        SocketLabsClient.QuickSend(
                17067,
                "r4W7SaJi38FyLp6k5NKs",
                "ross.brazuk@socketlabs.com",
                "ross.brazuk@gekodesign.net",
                "QuickSend",
                "<p>content</p>",
                "Quicksend"
        );




    }
}