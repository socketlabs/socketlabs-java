package examples.basic.invalid;

import com.socketLabs.injectionApi.SendResponse;
import com.socketLabs.injectionApi.SocketLabsClient;
import com.socketLabs.injectionApi.message.BasicMessage;
import com.socketLabs.injectionApi.message.EmailAddress;
import examples.Example;
import examples.ExampleConfig;

public class BasicSendWithInvalidFrom implements Example {

    public SendResponse RunExample ()  throws Exception {

        BasicMessage message = new BasicMessage();

        message.setSubject("Sending A Test Message");
        message.setHtmlBody("<html>This is the Html Body of my message.</html>");
        message.setPlainTextBody("This is the Plain Text Body of my message.");

        message.setFrom(new EmailAddress("!@#$!@#$!@#$@#!$"));
        message.addToEmailAddress("recipient1@example.com");

        // create the client
        SocketLabsClient client = new SocketLabsClient(ExampleConfig.ServerId, ExampleConfig.ApiKey);

        // send the message
        SendResponse response =  client.send(message);

        return response;

    }
}
