package examples.basic;

import com.socketLabs.injectionApi.SendResponse;
import com.socketLabs.injectionApi.SocketLabsClient;
import com.socketLabs.injectionApi.message.BasicMessage;
import examples.Example;
import examples.ExampleConfig;

import java.net.InetSocketAddress;
import java.net.Proxy;


public class BasicSendWithRetry implements Example {
    public SendResponse RunExample () throws Exception {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 4433));
        SocketLabsClient client = new SocketLabsClient(ExampleConfig.ServerId, ExampleConfig.ApiKey, proxy);
        BasicMessage message = new BasicMessage();
        SendResponse response = client.send(message);
        return response;
    }
}
