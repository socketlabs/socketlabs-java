package examples.bulk;

import com.socketLabs.injectionApi.SendResponse;
import com.socketLabs.injectionApi.SocketLabsClient;
import com.socketLabs.injectionApi.message.BulkMessage;
import com.socketLabs.injectionApi.message.BulkRecipient;
import com.socketLabs.injectionApi.message.EmailAddress;
import examples.*;

public class BulkSendWithMergeData implements Example {
    @Override
    public SendResponse RunExample() throws Exception {

        // Build the message
        BulkMessage message = new BulkMessage();

        // Set other properties as needed
        message.setSubject("Sending A Test Message With Merge Data");

        // Build the Content (Note the %% symbols used to denote the data to be merged)
        String html =  "<html>"
                + "   <head><title>Sending A Test Message With Merge Data</title></head>"
                + "   <body>"
                + "       <h1>Sending A Complex Test Message</h1>"
                + "       <h2>Global Merge Data</h2>"
                + "       <p>Motto = <b>%%Motto%%</b></p>"
                + "       <h2>Per Recipient Merge Data</h2>"
                + "       <p>"
                + "       EyeColor = %%EyeColor%%<br/>"
                + "       HairColor = %%HairColor%%"
                + "       </p>"
                + "   </body>"
                + "</html>";
        message.setHtmlBody(html);

        String text = "Sending A Test Message With Merge Data"
                + "       Merged Data"
                + "           Motto = %%Motto%%"
                + "       "
                + "       Example of Merge Usage"
                + "           EyeColor = %%EyeColor%%"
                + "           HairColor = %%HairColor%%";
        message.setPlainTextBody(text);

        message.setFrom(new EmailAddress("from@example.com"));

        // Add some global merge-data (These will be applied to all Recipients unless specifically overridden by Recipient level merge-data)
        message.addGlobalMergeData("Motto", "When hitting the Inbox matters!");

        // Add recipients with merge data
        BulkRecipient recipient1 = new BulkRecipient("recipient1@example.com");
        recipient1.addMergeData("EyeColor", "Blue");
        recipient1.addMergeData("HairColor", "Blond");
        message.getTo().add(recipient1);

        BulkRecipient recipient2 = new BulkRecipient("recipient2@example.com", "Recipient 2");
        recipient2.addMergeData("EyeColor", "Green");
        recipient2.addMergeData("HairColor", "Brown");
        message.getTo().add(recipient2);

        BulkRecipient recipient3 = new BulkRecipient("recipient3@example.com"); // The merge-data for this Recipient will be populated with Global Merge-Data
        recipient3.setFriendlyName("Recipient 3");
        recipient2.addMergeData("EyeColor", "Hazel");
        recipient2.addMergeData("HairColor", "Black");
        message.getTo().add(recipient3);

        // Add recipients using the addToRecipient function
        // The merge data for this Recipient will be populated with Global merge data
        message.addToRecipient(new BulkRecipient("recipient4@example.com", "Recipient #5"));

        // create the client
        SocketLabsClient client = new SocketLabsClient(ExampleConfig.ServerId, ExampleConfig.ApiKey);

        // send the message
        SendResponse response =  client.send(message);

        return response;

    }
}
