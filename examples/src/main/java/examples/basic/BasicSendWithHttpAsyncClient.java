package examples.basic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.socketLabs.injectionApi.SendResponse;
import com.socketLabs.injectionApi.SocketLabsClient;
import com.socketLabs.injectionApi.core.SendAsyncCallback;
import com.socketLabs.injectionApi.message.BasicMessage;
import com.socketLabs.injectionApi.message.EmailAddress;
import examples.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.nio.reactor.ConnectingIOReactor;

import java.io.IOException;

public class BasicSendWithHttpAsyncClient implements Example {

    @Override
    public SendResponse RunExample() throws Exception {

        BasicMessage message = new BasicMessage();

        message.setSubject("Sending A Test Message (Basic Send Async)");
        message.setHtmlBody("<html><body><h1>Sending A Test Message</h1><p>This is the Html Body of my message.</p></body></html>");
        message.setPlainTextBody("This is the Plain Text Body of my message.");

        message.setFrom(new EmailAddress("from@example.com"));
        message.addToEmailAddress("recipient1@example.com");

        // create the HttpClient
        final ConnectingIOReactor ioReactor = new DefaultConnectingIOReactor();
        PoolingNHttpClientConnectionManager cm = new PoolingNHttpClientConnectionManager(ioReactor);
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(20);

        RequestConfig requestConfig =RequestConfig.custom()
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(5000)
                .build();

        CloseableHttpAsyncClient httpClient = HttpAsyncClients.custom()
                .setConnectionManager(cm)
                .setDefaultRequestConfig(requestConfig)
                .build();

        // create the client
        SocketLabsClient client = new SocketLabsClient(ExampleConfig.ServerId, ExampleConfig.ApiKey);

        // send the message
        client.sendAsync(message, httpClient, new SendAsyncCallback() {

            @Override
            public void onError(Exception ex)  {
                // Handle Exception here
                ex.printStackTrace();
                return;
            }

            @Override
            public void onResponse(SendResponse response) throws IOException {
                // Handle SendResponse here

                ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

                System.out.println("Response body : ");
                System.out.println(mapper.writeValueAsString(response));
                System.out.println();
                System.out.println();
                System.out.println("Enter a number (or QUIT to exit):");

                return;

            }
        });

        System.out.println("Waiting for response...");
        return null;
    }
}
