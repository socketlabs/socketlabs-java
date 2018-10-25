package examples.basic.invalid;

import com.socketLabs.injectionApi.SendResponse;
import com.socketLabs.injectionApi.SocketLabsClient;
import com.socketLabs.injectionApi.message.BasicMessage;
import examples.Example;
import examples.ExampleConfig;

public class BasicSendWithInvalidAttachment implements Example {

    public SendResponse RunExample ()  throws Exception {

        BasicMessage message = new BasicMessage();



        SocketLabsClient client = new SocketLabsClient(ExampleConfig.ServerId, ExampleConfig.ApiKey);
        SendResponse response =  client.send(message);

        return response;

    }
}
