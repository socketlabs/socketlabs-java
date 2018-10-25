package examples.basic;

import com.socketLabs.injectionApi.SendResponse;
import com.socketLabs.injectionApi.SocketLabsClient;
import com.socketLabs.injectionApi.core.HttpCallback;
import com.socketLabs.injectionApi.message.BasicMessage;
import com.socketLabs.injectionApi.message.EmailAddress;
import examples.Example;
import examples.ExampleConfig;

public class BasicAsync implements Example {

    @Override
    public SendResponse RunExample() throws Exception {

        BasicMessage message = new BasicMessage();

        message.setSubject("Sending A Test Message");
        message.setHtmlBody("<html>This is the html Body of my message.</html>");
        message.setPlainTextBody("This is the Plain Text Body of my message.");

        message.setFrom(new EmailAddress("from@example.com"));
        message.addToEmailAddress("recipient1@example.com");

        // create the client
        SocketLabsClient client = new SocketLabsClient(ExampleConfig.ServerId, ExampleConfig.ApiKey);

        // send the message
        client.sendAsync(message, new HttpCallback() {

            @Override
            public void onError(Exception ex)  {
                // Handle Exception here
                return;
            }

            @Override
            public void onResponse(SendResponse response)  {
                // Handle SendResponse here
                return;
            }
        });

        return null;

    }
}
