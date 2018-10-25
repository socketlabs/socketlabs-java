package examples.Basic;

import com.socketLabs.injectionApi.SendResponse;
import com.socketLabs.injectionApi.SocketLabsClient;
import com.socketLabs.injectionApi.message.Attachment;
import com.socketLabs.injectionApi.message.BasicMessage;
import com.socketLabs.injectionApi.message.CustomHeader;
import com.socketLabs.injectionApi.message.EmailAddress;
import examples.Example;

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

        message.setCharset("ASCII");
        message.setSubject("Sending A Complex Test Message (Basic Send)");

        message.setHtmlBody("<html><body><h1>Sending A Complex Test Message</h1><p>This is the Html Body of my message.</p><h2>Unicode Characters:</h2><p>✔ - Check</p><h2>Embedded Image:</h2><p><img src=\"cid:bus\" /></p></body></html>");
        message.setPlainTextBody("This is the Plain Text Body of my message.");

        message.setFrom(new EmailAddress("from@example.com"));
        message.setReplyTo(new EmailAddress("replyto@example.com"));

        // Adding To Recipients
        // =========================
        // Add Email Addresses using an Array
        List<EmailAddress> to_recipients = new ArrayList<>();
        to_recipients.add(new EmailAddress("david.schrenker@socketlabs.com"));
        to_recipients.add(new EmailAddress("developers@socketlabs.com", "Recipient #2"));
        message.setTo(to_recipients);

        // Add Email Addresses using new EmailAddress
        message.addToEmailAddress(new EmailAddress("sl@socketlabs.com",  "Recipient #3"));

        // Add Email Addresses using the addToEmailAddress function
        message.addToEmailAddress("recipient4@example.com");
        message.addToEmailAddress("recipient5@example.com", "Recipient #5");

        // Adding CC Recipients
        // =========================
        // Add Email Addresses using an Array
        List<EmailAddress>  cc_recipients = new ArrayList<>();
        cc_recipients.add(new EmailAddress("cc_recipients1@example.com"));
        cc_recipients.add(new EmailAddress("cc_recipients2@example.com", "Recipient #2"));
        message.setCc(cc_recipients);

        // Add Email Addresses using new EmailAddress
        message.addCcEmailAddress(new EmailAddress("cc_recipients3@example.com",  "Recipient #3" ));

        // Add Email Addresses using the addCcEmailAddress function
        message.addCcEmailAddress("cc_recipients4@example.com");
        message.addCcEmailAddress("cc_recipients5@example.com", "Recipient #5");

         // Adding Bcc Recipients
        // =========================
        // Add Email Addresses using an Array
        List<EmailAddress>  bcc_recipients = new ArrayList<>();
        bcc_recipients.add(new EmailAddress("bcc_recipients1@example.com"));
        bcc_recipients.add(new EmailAddress("bcc_recipients2@example.com", "Recipient #2"));
        message.setBcc(bcc_recipients);

        // Add Email Addresses using new EmailAddress
        message.addBccEmailAddress(new EmailAddress( "bcc_recipients3@example.com",  "Recipient #3" ));

        // Add Email Addresses using the addBccEmailAddress function
        message.addBccEmailAddress("bcc_recipients4@example.com");
        message.addBccEmailAddress("bcc_recipients5@example.com", "Recipient #5");

        // Adding Attachments
        // =========================
        // Add Attachment directly to the Array
        List<Attachment>  attachments = new ArrayList<>();
        attachments.add(new Attachment("bus.png", "image/png", "src/main/java/examples/Img/bus.png"));
        message.setAttachments(attachments);

        // Add Attachment using the addAttachments function
        Attachment attachment2 = new Attachment("bus2", "image/png", "src/main/java/examples/Img/bus.png");
        attachment2.setContentId("bus");
        message.addAttachments(attachment2);

        // Add Attachment a filePath {string} to the array
        message.addAttachments(new Attachment("src/main/java/examples/Html/SimpleEmail.html"));


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

        SocketLabsClient client = new SocketLabsClient(19742, "Ng5x6HEc4f7CFk92Kpn3");
        SendResponse response =  client.send(message);
        return response;


    }
}
