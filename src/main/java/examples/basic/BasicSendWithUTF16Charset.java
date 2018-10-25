package examples.basic;

import com.socketLabs.injectionApi.SendResponse;
import com.socketLabs.injectionApi.SocketLabsClient;
import com.socketLabs.injectionApi.message.BasicMessage;
import com.socketLabs.injectionApi.message.EmailAddress;
import examples.Example;
import examples.ExampleConfig;

public class BasicSendWithUTF16Charset implements Example {

    public SendResponse RunExample ()  throws Exception {

        // Build the message
        BasicMessage message = new BasicMessage();

        // Set the Subject
        message.setSubject("Sending A Test Message");

        // Set the HTML body, which includes placeholders for Merge Field data
        message.setHtmlBody("<html><body><h1>Sending A UTF16 Charset Email</h1><p>This is the html Body of my message.</p><h2>UTF16 Characters:</h2><p>例 (example)</p></body></html>");

        // Set the charset
        message.setCharSet("UTF16");

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