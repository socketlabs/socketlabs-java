package examples.basic;

import com.socketLabs.injectionApi.*;
import com.socketLabs.injectionApi.message.*;
import examples.*;

public class BasicSendWithASCIICharset implements Example {

    public SendResponse RunExample ()  throws Exception {

        // Build the message
        BasicMessage message = new BasicMessage();

        // Set the Subject
        message.setSubject("Sending A ASCII Charset Email");


        // Set the HTML body, which includes placeholders for Merge Field data
        message.setHtmlBody("<html><body><h1>Sending A ASCII Charset Email</h1><p>This is the html Body of my message.</p><h2>UTF-8 Characters:</h2><p>✔ - Check</p></body></html>");

        // Set the charset
        message.setCharSet("ASCII");

        // Set a recipient
        message.setFrom(new EmailAddress("from@example.com"));
        message.addToEmailAddress("david.schrenker@socketlabs.com");

        // create the client
        SocketLabsClient client = new SocketLabsClient(ExampleConfig.ServerId, ExampleConfig.ApiKey);

        // send the message
        SendResponse response =  client.send(message);

        return response;
    }
}