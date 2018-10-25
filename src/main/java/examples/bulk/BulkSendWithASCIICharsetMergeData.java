package examples.bulk;

import com.socketLabs.injectionApi.SendResponse;
import com.socketLabs.injectionApi.SocketLabsClient;
import com.socketLabs.injectionApi.message.BulkMessage;
import com.socketLabs.injectionApi.message.BulkRecipient;
import com.socketLabs.injectionApi.message.EmailAddress;
import examples.Example;
import examples.ExampleConfig;

public class BulkSendWithCharsetMergeData implements Example {
    @Override
    public SendResponse RunExample() throws Exception {

        BulkMessage message = new BulkMessage();

        // Set the Subject and From address
        message.setSubject("Sending ");
        message.setFrom(new EmailAddress("from@example.com"));

        // Set HTML and plain text body, both of which use UTF16 characters
        message.setHtmlBody("<html><body><h1>Sending A ASCII Charset Email</h1><p>This is the html Body of my message.</p><p>Complete? - %%Complete%%</p></body></html>");
        message.setPlainTextBody("Merge Data" + "   Complete - %%Complete%%");

        // Set the charset
        message.setCharSet("ASCII");

        // Add recipients with merge data that contains UTF16 characters
        BulkRecipient recipient1 = new BulkRecipient("ross.brazuk@socketlabs.com");
        recipient1.addMergeData("Complete", "✘");
        message.getTo().add(recipient1);

        BulkRecipient recipient2 = new BulkRecipient("ross.brazuk@gekodesign.net");
        recipient2.addMergeData("Complete", "✔");
        message.getTo().add(recipient2);

        // Instantiate the client and send
        SocketLabsClient client = new SocketLabsClient(ExampleConfig.ServerId, ExampleConfig.ApiKey);
        SendResponse response = client.send(message);

        return response;
    }
}
