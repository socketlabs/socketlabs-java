import client.SendResponse;
import client.SocketLabsClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import models.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        BulkMessage message = new BulkMessage();

        BulkRecipient recipient = new BulkRecipient(new EmailAddress("ross.brazuk@socketlabs.com"));
        HashMap<String, String> mergeData = new HashMap<>();
        mergeData.put("color", "blue");
        recipient.setMergeData(mergeData);

        BulkRecipient recipient2 = new BulkRecipient(new EmailAddress("ross.brazuk@socketlabs.com"));
        HashMap<String, String> mergeData2 = new HashMap<>();
        mergeData2.put("color", "red");
        recipient2.setMergeData(mergeData2);



        List<BulkRecipient> bulkRecipients = new ArrayList<>();

        bulkRecipients.add(recipient);
        bulkRecipients.add(recipient2);

        message.setTo(bulkRecipients);

        message.setFrom(new EmailAddress("from@example.com", "Mr. From"));

        message.setSubject("Bulk Message Example");

        message.setPlainTextBody("Hello this is a Bulk Message");
        message.setHtmlBody("<p>Hello this is a Bulk Message</p>");
        message.setMailingId("mailingId");
        message.setMessageId("messageId");

        message.setReplyTo(new EmailAddress("replyto@example.com", "Mr. Reply"));

        HashMap<String, String> globalMerge = new HashMap<>();
        globalMerge.put("greeting", "hello!");
        message.setGlobalMergeData(globalMerge);
        //message.setPerMessageMergeData();

        //System.out.println(mapper.writeValueAsString(mergeData2));

        System.out.println(mapper.writeValueAsString(message));
    }
}