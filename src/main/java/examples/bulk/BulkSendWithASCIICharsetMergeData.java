package examples.bulk;

import com.socketLabs.injectionApi.*;
import com.socketLabs.injectionApi.message.*;
import examples.*;

public class BulkSendWithASCIICharsetMergeData implements Example {
    @Override
    public SendResponse RunExample() throws Exception {

        BulkMessage message = new BulkMessage();

        // Set the Subject and From address
        message.setSubject("Sending A Test Message With ASCII Charset Merge Data");

        // Set HTML and plain text body, both of which use UTF8 characters
        // Build the Content (Note the %% symbols used to denote the data to be merged)
        String html = "<html>"
                + "   <body>"
                + "       <h1>Sending A Test Message With ASCII Charset Merge Data</h1>"
                + "       <h2>Merge Data</h2>"
                + "       <p>Complete? = %%Complete%%</p>"
                + "   </body>"
                + "</html>";
        message.setHtmlBody(html);

        String text = "Sending A Test Message With ASCII Charset Merge Data"
                + "       Merged Data"
                + "           Complete? = %%Complete%%";
        message.setPlainTextBody(text);

        message.setFrom(new EmailAddress("from@example.com"));

        // Set the charset
        message.setCharSet("ASCII");

        // Add recipients with merge data that contains UTF16 characters
        BulkRecipient recipient1 = new BulkRecipient("recipient1@example.com");
        recipient1.addMergeData("Complete", "✘");
        message.getTo().add(recipient1);

        BulkRecipient recipient2 = new BulkRecipient("recipient2@example.com", "Recipient #2");
        recipient2.addMergeData("Complete", "✔");
        message.getTo().add(recipient2);

        BulkRecipient recipient3 = new BulkRecipient("recipient3@example.com");
        recipient3.setFriendlyName("Recipient 3");
        recipient3.addMergeData("Complete", "✘");
        message.getTo().add(recipient3);

        // create the client
        SocketLabsClient client = new SocketLabsClient(ExampleConfig.ServerId, ExampleConfig.ApiKey);

        // send the message
        SendResponse response =  client.send(message);

        return response;
    }
}
