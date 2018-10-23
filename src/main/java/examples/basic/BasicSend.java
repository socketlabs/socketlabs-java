package examples.basic;

import com.socketLabs.SendResponse;
import com.socketLabs.SocketLabsClient;
import com.socketLabs.models.BasicMessage;
import com.socketLabs.models.EmailAddress;

public class BasicSend {
    public static void main(String[] args) throws Exception {


        SocketLabsClient client = new SocketLabsClient(100000, "your-api-key");

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

        SendResponse response =  client.send(message);


    }
}
