package examples.basic.invalid;

import com.socketLabs.injectionApi.*;
import com.socketLabs.injectionApi.message.*;
import examples.*;

public class BasicSendWithInvalidRecipients implements Example {

    public SendResponse RunExample ()  throws Exception {

        BasicMessage message = new BasicMessage();

        message.setSubject("Sending A Test Message");
        message.setHtmlBody("<html>This is the Html Body of my message.</html>");
        message.setPlainTextBody("This is the Plain Text Body of my message.");

        message.setFrom(new EmailAddress("from@example.com"));
        message.getTo().add(new EmailAddress("!@#$!@#$!@#$@#!$"));
        message.getTo().add(new EmailAddress("failure.com"));
        message.getTo().add(new EmailAddress("ImMissingSomething"));
        message.getTo().add(new EmailAddress("Fail@@!.Me"));

        // create the client
        SocketLabsClient client = new SocketLabsClient(ExampleConfig.ServerId, ExampleConfig.ApiKey);

        // send the message
        SendResponse response =  client.send(message);

        return response;

    }
}
