package examples.basic;

import com.socketLabs.injectionApi.SendResponse;
import com.socketLabs.injectionApi.SocketLabsClient;
import com.socketLabs.injectionApi.message.BasicMessage;
import examples.Example;
import examples.ExampleConfig;

import java.net.InetSocketAddress;
import java.net.Proxy;

public class BasicSendWithProxy implements Example {

    public SendResponse RunExample ()  throws Exception {

        BasicMessage message = new BasicMessage();

        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("http://localhost.", 8888));
        SocketLabsClient client = new SocketLabsClient(ExampleConfig.ServerId, ExampleConfig.ApiKey, proxy);

        SendResponse response =  client.send(message);

        return response;

    }
}
