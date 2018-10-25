package examples.basic;

import com.socketLabs.injectionApi.SendResponse;
import com.socketLabs.injectionApi.SocketLabsClient;
import com.socketLabs.injectionApi.message.Attachment;
import com.socketLabs.injectionApi.message.BasicMessage;
import com.socketLabs.injectionApi.message.EmailAddress;
import examples.Example;
import examples.ExampleConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class BasicSendWithAttachment implements Example {

    public SendResponse RunExample ()  throws Exception {

        BasicMessage message = new BasicMessage();

        message.setSubject("Sending An Email With An Attachment");
        message.setHtmlBody("<html><body><h1>Sending An Email With An Attachment</h1><p>This is the Html Body of my message.</p></body></html>");
        message.setPlainTextBody("This is the Plain Text Body of my message.");

        message.setFrom(new EmailAddress("from@example.com"));
        message.addToEmailAddress("david.schrenker@socketlabs.com");

        Attachment attachment = new Attachment(
                "bus.png",
                "image/png",
                "src/main/java/examples/img/bus.png"
        );
        message.addAttachments(attachment);

        SocketLabsClient client = new SocketLabsClient(ExampleConfig.ServerId, ExampleConfig.ApiKey);
        SendResponse response =  client.send(message);

        return response;

    }
}
