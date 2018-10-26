package examples.basic;

import com.socketLabs.injectionApi.*;
import com.socketLabs.injectionApi.message.*;
import examples.*;

public class BasicSendWithCustomHeaders implements Example {

    public SendResponse RunExample ()  throws Exception {

        BasicMessage message = new BasicMessage();

        message.setSubject("Sending An Email With Custom Headers");
        message.setHtmlBody("<html><body><h1>Sending An Email With Custom Headers</h1><p>This is the Html Body of my message.</p></body></html>");
        message.setPlainTextBody("This is the Plain Text Body of my message.");

        message.setFrom(new EmailAddress("from@example.com"));
        message.addToEmailAddress("recipient1@example.com");

        // Add CustomHeader using the array
        message.getCustomHeaders().add(new CustomHeader("My-Header", "1...2...3..."));

        // Add CustomHeader using the addCustomHeader function
        message.addCustomHeader( "Message-Type", "BasicMessage");
        message.addCustomHeader(new CustomHeader("Example-Type", "BasicSendWithCustomHeaders"));

        // create the client
        SocketLabsClient client = new SocketLabsClient(ExampleConfig.ServerId, ExampleConfig.ApiKey);

        // send the message
        SendResponse response =  client.send(message);

        return response;

    }
}