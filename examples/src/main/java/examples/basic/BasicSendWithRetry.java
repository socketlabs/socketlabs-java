package examples.basic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.socketLabs.injectionApi.SendResponse;
import com.socketLabs.injectionApi.SocketLabsClient;
import com.socketLabs.injectionApi.core.SendAsyncCallback;
import com.socketLabs.injectionApi.message.BasicMessage;
import com.socketLabs.injectionApi.message.EmailAddress;
import examples.Example;
import examples.ExampleConfig;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;


public class BasicSendWithRetry implements Example {
    public SendResponse RunExample () throws Exception {

        BasicMessage message = new BasicMessage();

        message.setSubject("Sending An Email Through A Proxy");
        message.setHtmlBody("<html><body><h1>Sending An Email Through A Proxy</h1><p>This is the Html Body of my message.</p></body></html>");
        message.setPlainTextBody("This is the Plain Text Body of my message.");

        message.setFrom(new EmailAddress("from@example.com"));
        message.addToEmailAddress("recipient1@example.com");

        // create the proxy
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 4433));

        // create the client
        SocketLabsClient client = new SocketLabsClient(ExampleConfig.ServerId, ExampleConfig.ApiKey, proxy);
        client.setRequestTimeout(5);
        client.setNumberOfRetries(2);

        // send the message
        SendResponse response = client.send(message);

        return response;
    }
}
