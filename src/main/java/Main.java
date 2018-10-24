import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.socketLabs.injectionApi.models.*;
import com.socketLabs.injectionApi.core.serialization.InjectionRequestFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        InjectionRequestFactory factory = new InjectionRequestFactory(12345, "abcdefghijklmnop");

//        BasicMessage message = new BasicMessage();
//
//        message.setSubject("Test Subject");
//        message.setPlainTextBody("This is an email");
//        message.setFrom(new EmailAddress("ross.brazuk@socketlabs.com", "Ross Brazuk"));
//
//        List<EmailAddress> to =  new ArrayList<>();
//        to.add(new EmailAddress("example@example.com"));
//        to.add(new EmailAddress("anotherexample@example.com"));
//        message.setTo(to);
//
//        // attachments
//        List<Attachment> attachments = new ArrayList<>();
//        attachments.add(new Attachment("/Users/ross.brazuk/Desktop/nic.jpg"));
//        attachments.add(new Attachment("/Users/ross.brazuk/Desktop/text.txt"));
//        message.setAttachments(attachments);
//
//
//        // custom headers
//        List<CustomHeader> customHeaders = new ArrayList<>();
//
//        customHeaders.add(new CustomHeader("custom-header-1", "number 1"));
//        customHeaders.add(new CustomHeader("custom-header-2", "number 2"));
//        customHeaders.add(new CustomHeader("custom-header-3", "number 3"));
//        customHeaders.add(new CustomHeader("custom-header-4", "number 4"));
//
//        message.setCustomHeaders(customHeaders);
//
//        message.setApiTemplate(5);
//
//
//        System.out.println(factory.GenerateRequest(message));

        BulkMessage bulkMessage = new BulkMessage();

        BulkRecipient recipient = new BulkRecipient("bulk@example.com", "Bulk Recipient");
        Map<String, String> merge1 = new HashMap<>();
        merge1.put("eyeColor", "blue");
        merge1.put("pet", "dog");
        recipient.setMergeData(merge1);

        BulkRecipient recipient2 = new BulkRecipient("bulk@example.com", "Bulk Recipient");
        Map<String, String> merge2 = new HashMap<>();
        merge2.put("eyeColor", "green");
        merge2.put("pet", "cat");
        recipient2.setMergeData(merge2);

        bulkMessage.setFrom(new EmailAddress("ross.brazuk@socketlabs.com", "Ross Brazuk"));
        bulkMessage.setSubject("Bulk Message");

        Map<String, String> globalMerge = new HashMap<>();
        globalMerge.put("greeting", "hello");
        globalMerge.put("date", "10/31/18");
        bulkMessage.setMergeData(globalMerge);

        List<BulkRecipient> recipients = new ArrayList<>();
        recipients.add(recipient);
        recipients.add(recipient2);

        bulkMessage.setTo(recipients);

        System.out.println(factory.GenerateRequest(bulkMessage));

    }
}