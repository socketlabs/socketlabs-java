package examples.Basic;

import com.socketLabs.injectionApi.SendResponse;
import com.socketLabs.injectionApi.SocketLabsClient;
import com.socketLabs.injectionApi.models.Attachment;
import com.socketLabs.injectionApi.models.BasicMessage;
import com.socketLabs.injectionApi.models.EmailAddress;
import examples.Example;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class BasicSendWithAttachment implements Example {

    public SendResponse RunExample ()  throws Exception {


        SocketLabsClient client = new SocketLabsClient(19742, "Ng5x6HEc4f7CFk92Kpn3");

        BasicMessage message = new BasicMessage();

        message.setSubject("Sending A Test Message");
        message.setHtmlBody("<html>This is the Html Body of my message.</html>");
        message.setPlainTextBody("This is the Plain Text Body of my message.");

        message.setFrom(new EmailAddress("from@example.com"));
        message.setReplyTo(new EmailAddress("replyto@example.com"));

        message.addToEmailAddress("recipient1@example.com");
        message.addToEmailAddress("recipient2@example.com", "Recipient #1");
        message.addToEmailAddress(new EmailAddress("recipient3@example.com"));
        message.addToEmailAddress(new EmailAddress("recipient4@example.com", "Recipient #4"));

        // using file stream
        File initialFile = new File("src/main/java/examples/Img/bus.png");
        InputStream stream = new FileInputStream(initialFile);

        Attachment attachment = new Attachment("bus.png", "image/png", stream);

        // add custom headers to attachment
        attachment.addCustomHeader("Color", "Orange");
        attachment.addCustomHeader("Place", "Beach");

        message.addAttachments(attachment);

        // using file path
        Attachment attachment2 = new Attachment("src/main/java/examples/Html/SimpleEmail.html");

        message.addAttachments(attachment2);

        SendResponse response =  client.send(message);
        return response;
    }
}
