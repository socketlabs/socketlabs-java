package examples.bulk;

import com.socketLabs.injectionApi.*;
import com.socketLabs.injectionApi.message.*;
import examples.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class BulkSendComplexExample implements Example {

    @Override
    public SendResponse RunExample() throws Exception {

        // Build the message
        BulkMessage message = new BulkMessage();

        message.setMessageId("ComplexExample");
        message.setMailingId("BulkSend");

        message.setCharSet("UTF-8");

        message.setSubject("Complex BulkSend Example");

        // Build the Content (Note the %% symbols used to denote the data to be merged)
        String html =  "<html>"
                + "   <head><title>Sending A Complex Test Message</title></head>"
                + "   <body>"
                + "       <h1>Sending A Complex Test Message</h1>"
                + "       <h2>Merge Data</h2>"
                + "       <p>"
                + "           Motto = <b>%%Motto%%</b> </br>"
                + "           Birthday = <b>%%Birthday%%</b> </br>"
                + "           Age = <b>%%Age%%</b> </br>"
                + "           UpSell = <b>%%UpSell%%</b>"
                + "       </p>"
                + "       <h2>Example of Merge Usage</h2>"
                + "       <p>"
                + "           Our company motto is '<b>%%Motto%%</b>'. </br>"
                + "           Your birthday is <b>%%Birthday%%</b> and you are <b>%%Age%%</b> years old."
                + "       </p>"
                + "       <h2>UTF-8 Characters:</h2>"
                + "       <p>✔ - Check</p>"
                + "       <h2>Embedded Image:</h2>"
                + "       <p><img src=\"cid:bus\" /></p>"
                + "   </body>"
                + "</html>";
        message.setHtmlBody(html);

        String text = "Sending A Complex Test Message"
                + "       Merged Data"
                + "           Motto = %%Motto%%"
                + "           Birthday = %%Birthday%%"
                + "           Age = %%Age%%"
                + "           UpSell = %%UpSell%%"
                + "       "
                + "       Example of Merge Usage"
                + "           Our company motto is '%%Motto%%'."
                + "           Your birthday is %%Birthday%% and you are %%Age%% years old.";
        message.setPlainTextBody(text);

        message.setFrom(new EmailAddress("from@example.com", "FromMe"));
        message.setReplyTo(new EmailAddress("replyto@example.com"));

        // Adding To global merge data
        // (These will be applied to all Recipients unless specifically overridden by Recipient level merge data)
        // =========================
        // Add global merge data using a map
        TreeMap<String, String> globalMergeData = new TreeMap<>();
        globalMergeData.put("Motto", "When hitting the inbox matters!");
        globalMergeData.put("Birthday", "unkown");
        message.setGlobalMergeData(globalMergeData);

        // Add global merge data directly to the Global Merge Data Map on he message
        message.getGlobalMergeData().put("Age", "an unknown number of");

        // Add global merge data  using the addGlobalMergeData function
        message.addGlobalMergeData("UpSell", "BTW:  You are eligible for discount pricing when you upgrade your service!");


        //Adding To Recipients
        // Including merge data on the recipient with the same name as the global merge data will override global merge data
        // =========================
        // Add recipients with merge data using a Map
        TreeMap<String, String> rec1MergeData = new TreeMap<>();
        rec1MergeData.put("Birthday", "08/05/1991");
        rec1MergeData.put("Age", "27");
        message.getTo().add(new BulkRecipient("recipient1@example.com", rec1MergeData));

        BulkRecipient recipient2 = new BulkRecipient("recipient2@example.com");
        recipient2.addMergeData("Birthday", "04/12/1984");
        recipient2.addMergeData("Age", "34");
        recipient2.addMergeData("UpSell", "");  // This will override the Global Merge-Data for this specific Recipient
        message.addToRecipient(recipient2);

        BulkRecipient recipient3 = new BulkRecipient("recipient3@example.com");
        recipient3.addMergeData("Birthday", "10/30/1978");
        recipient3.addMergeData("Age", "40");
        recipient3.addMergeData("UpSell", "");
        recipient3.setFriendlyName("Recipient 3");
        message.addToRecipient(recipient3);

        message.addToRecipient("recipient4@example.com", "Recipient #4");

        // Adding Attachments
        // =========================
        // Add Attachment directly to the List

        // Add an attachment with a custom header
        Attachment attachment1 = new Attachment(
                "bus.png",
                "image/png",
                "src/main/java/examples/img/bus.png"
        );
        attachment1.addCustomHeader("Attachment-Header", "I Am A Bus");
        message.getAttachments().add(attachment1);

        // Add Attachment using the addAttachments function
        Attachment attachment2 = new Attachment(
                "bus2.png",
                "image/png",
                "src/main/java/examples/img/bus.png"
        );
        attachment2.setContentId("bus");
        message.addAttachments(attachment2);

        // Add Attachment a filePath {string} to the array
        message.addAttachments(new Attachment("src/main/java/examples/html/SimpleEmail.html"));

        // using file stream
        File initialFile = new File("src/main/java/examples/img/bus.png");
        InputStream stream = new FileInputStream(initialFile);
        Attachment attachment4 = new Attachment("yellow-bus.png", "image/png", stream);

        // add custom headers to attachment
        attachment4.addCustomHeader("Color", "Yellow");
        attachment4.addCustomHeader("Place", "Beach");

        message.addAttachments(attachment4);

        // Adding Custom Headers
        // =========================
        // Add Custom Headers using an Array
        List<CustomHeader>  headers = new ArrayList<>();
        headers.add(new CustomHeader("example-type", "bulk-send-complex"));
        headers.add(new CustomHeader("message-contains", "attachments, headers"));
        message.setCustomHeaders(headers);

        // Add Custom Headers directly to the Array
        message.addCustomHeader(new CustomHeader("message-has-attachments", "true"));

        // Add Custom Headers using the addCustomHeaders function
        message.addCustomHeader("testMessageHeader", "I am a message header");


        // create the client
        SocketLabsClient client = new SocketLabsClient(ExampleConfig.ServerId, ExampleConfig.ApiKey);

        // send the message
        SendResponse response =  client.send(message);

        return response;
    }

}
