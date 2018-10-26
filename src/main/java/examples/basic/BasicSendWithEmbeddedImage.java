package examples.basic;

import com.socketLabs.injectionApi.*;
import com.socketLabs.injectionApi.message.*;
import examples.*;

public class BasicSendWithEmbeddedImage implements Example {

    public SendResponse RunExample ()  throws Exception {

        BasicMessage message = new BasicMessage();

        message.setSubject("Sending An Email With An Embedded Image");
        message.setHtmlBody("<html><body><h1>Sending An Email With An Embedded Image</h1><p>This is the Html Body of my message.</p><h2>Embedded Image:</h2><p><img src=\"cid:bus\" /></p></body></html>");
        message.setPlainTextBody("This is the Plain Text Body of my message.");

        message.setFrom(new EmailAddress("from@example.com"));
        message.addToEmailAddress("david.schrenker@socketlabs.com");

        Attachment attachment = new Attachment(
                "bus.png",
                "image/png",
                "src/main/java/examples/img/bus.png"
        );
        attachment.setContentId("bus");

        message.addAttachments(attachment);

        SocketLabsClient client = new SocketLabsClient(ExampleConfig.ServerId, ExampleConfig.ApiKey);
        SendResponse response =  client.send(message);

        return response;

    }
}