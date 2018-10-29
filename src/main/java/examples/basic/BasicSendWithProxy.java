package examples.basic;

import com.socketLabs.injectionApi.*;
import com.socketLabs.injectionApi.message.*;
import examples.*;

import java.net.InetSocketAddress;
import java.net.Proxy;

public class BasicSendWithProxy implements Example {

    public SendResponse RunExample ()  throws Exception {

        BasicMessage message = new BasicMessage();

        message.setSubject("Sending An Email Through A Proxy");
        message.setHtmlBody("<html><body><h1>Sending An Email Through A Proxy</h1><p>This is the Html Body of my message.</p></body></html>");
        message.setPlainTextBody("This is the Plain Text Body of my message.");

        message.setFrom(new EmailAddress("from@example.com"));
        message.addToEmailAddress("ross.brazuk@socketlabs.com");

        // create the proxy
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost.", 8888));

        // create the client
        SocketLabsClient client = new SocketLabsClient(ExampleConfig.ServerId, ExampleConfig.ApiKey, proxy);

        // send the message
        SendResponse response =  client.send(message);

        return response;

    }
}
