package examples.bulk;

import com.socketLabs.injectionApi.*;
import com.socketLabs.injectionApi.message.*;
import examples.*;

public class BulkSend implements Example {
    @Override
    public SendResponse RunExample() throws Exception {

        BulkMessage message = new BulkMessage();

        message.setSubject("Sending A Test Message (Bulk Send)");
        message.setHtmlBody("<html><body><h1>Sending A Test Message</h1><p>This is the Html Body of my message.</p></body></html>");
        message.setPlainTextBody("This is the Plain Text Body of my message.");

        message.setFrom(new EmailAddress("from@example.com"));
        message.setReplyTo(new EmailAddress("replyto@example.com"));

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
