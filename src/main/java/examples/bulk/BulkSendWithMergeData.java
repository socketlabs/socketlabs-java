package examples.bulk;

import com.socketLabs.injectionApi.*;
import com.socketLabs.injectionApi.message.*;
import examples.*;

public class BulkSendWithMergeData implements Example {
    @Override
    public SendResponse RunExample() throws Exception {

        SocketLabsClient client = new SocketLabsClient(19742, "Ng5x6HEc4f7CFk92Kpn3");

        // Build the message
        BulkMessage message = new BulkMessage();

        // Add some global merge-data (These will be applied to all Recipients unless specifically overridden by Recipient level merge-data)
        message.addGlobalMergeData("Motto", "When hitting the Inbox matters!");
        message.addGlobalMergeData("Birthday", "Unknown");
        message.addGlobalMergeData("birthday", "empty");
        message.addGlobalMergeData("Age", "an unknown number of");
        message.addGlobalMergeData("UpSell", "BTW:  You are eligible for discount pricing when you upgrade your service!");

        // Add recipients with merge data
        BulkRecipient recipient1 = new BulkRecipient("recipient1@example.com");
        recipient1.addMergeData("Birthday", "08/05/1991");
        recipient1.addMergeData("Age", "27");
        message.getTo().add(recipient1);

        BulkRecipient recipient2 = new BulkRecipient("recipient2@example.com");
        recipient2.addMergeData("Birthday", "04/12/1984");
        recipient2.addMergeData("Age", "34");
        recipient2.addMergeData("UpSell", ""); // This will override the Global Merge-Data for this specific Recipient
        message.getTo().add(recipient2);

        BulkRecipient recipient3 = new BulkRecipient("recipient3@example.com"); // The merge-data for this Recipient will be populated with Global Merge-Data
        recipient3.setFriendlyName("Recipient 3");
        message.getTo().add(recipient3);

        // Set other properties as needed
        message.setSubject("Complex BulkSend Example");
        message.setFrom(new EmailAddress("from@example.com", "FromMe"));
        message.setReplyTo(new EmailAddress("replyto@example.com"));

        message.addCustomHeader("testMessageHeader", "I am a message header");

        // Build the Content (Note the %% symbols used to denote the data to be merged)
        String html = "<html>"
                + "   <head><title>Complex</title></head>"
                + "   <body>"
                + "       <h1>Merged Data</h1>"
                + "       <p>"
                + "           Motto = <b>%%Motto%%</b> </br>"
                + "           Birthday = <b>%%Birthday%%</b> </br>"
                + "           Age = <b>%%Age%%</b> </br>"
                + "           UpSell = <b>%%UpSell%%</b> </br>"
                + "       </p>"
                + "       </br>"
                + "       <h1>Example of Merge Usage</h1>"
                + "       <p>"
                + "           Our company motto is '<b>%%Motto%%</b>'. </br>"
                + "           Your birthday is <b>%%Birthday%%</b> and you are <b>%%Age%%</b> years old. </br>"
                + "           </br>"
                + "           <b>%%UpSell%%<b>"
                + "       </p>"
                + "   </body>"
                + "</html>";

        message.setHtmlBody(html);

        // TODO -  RCB - Improve this
        message.setPlainTextBody(html.replaceAll("<.*?>", ""));

        // Add an Attachment with a custom header
        Attachment attachment = new Attachment("bus.png", "image/png", "src/main/java/examples/Img/bus.png");
        attachment.getCustomHeaders().add(new CustomHeader("Attachment-Header", "I Am A Bus"));
        message.getAttachments().add(attachment);

        return client.send(message);
    }
}
