package examples.bulk;

import com.socketLabs.injectionApi.*;
import com.socketLabs.injectionApi.message.*;
import examples.*;

public class BulkSend implements Example {
    @Override
    public SendResponse RunExample() throws Exception {

        BulkMessage message = new BulkMessage();

        message.setSubject("Sending A Test Message");
        message.setHtmlBody("<html>This is the Html Body of my message.</html>");
        message.setPlainTextBody("This is the Plain Text Body of my message.");

        message.setFrom(new EmailAddress("from@example.com"));
        message.setReplyTo(new EmailAddress("replyto@example.com"));

        message.getTo().add(new BulkRecipient("recipient1@example.com"));
        message.getTo().add(new BulkRecipient("recipient2@example.com", "Recipient #2"));
        message.getTo().add(new BulkRecipient("recipient3@example.com"));
        message.getTo().add(new BulkRecipient("recipient4@example.com", "Recipient #4"));

        SocketLabsClient client = new SocketLabsClient(19742, "Ng5x6HEc4f7CFk92Kpn3");
        SendResponse response =  client.send(message);

        return response;
    }
}
