package examples.Basic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.socketLabs.injectionApi.SendResponse;
import com.socketLabs.injectionApi.SocketLabsClient;
import com.socketLabs.injectionApi.core.HttpCallback;
import com.socketLabs.injectionApi.models.BasicMessage;
import com.socketLabs.injectionApi.models.EmailAddress;
import examples.Example;

import java.io.IOException;

public class BasicAsync implements Example {

    @Override
    public SendResponse RunExample() throws Exception {


        SocketLabsClient client = new SocketLabsClient(19742, "Ng5x6HEc4f7CFk92Kpn3");

        BasicMessage message = new BasicMessage();

        message.setSubject("Sending A Test Message");
        message.setHtmlBody("<html>This is the Html Body of my message.</html>");
        message.setPlainTextBody("This is the Plain Text Body of my message.");

        message.setFrom(new EmailAddress("from@example.com"));
        message.setReplyTo(new EmailAddress("replyto@example.com"));

        message.addToEmailAddress("recipient1@example.com");
        message.addToEmailAddress("recipient2@example.com", "Recipient #1");
        message.addToEmailAddress(new EmailAddress("recipient3@example.com"));
        message.addToEmailAddress(new EmailAddress("recipient4@example.com", "Recipient #4"));

        client.sendAsync(message, new HttpCallback() {

            @Override
            public void onError(Exception ex) {

                // TODO: remove System.out.println(..) (used for debugging)
                System.out.println("Response body : ");
                try {
                    System.out.println(new ObjectMapper().writeValueAsString(ex));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onResponse(SendResponse response)  {

                // TODO: remove System.out.println(..) (used for debugging)
                System.out.println("Response body : ");
                try {
                    System.out.println(new ObjectMapper().writeValueAsString(response));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return;
            }
        });

        return null;



    }
}
