import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.socketLabs.models.Attachment;
import com.socketLabs.models.BasicMessage;
import com.socketLabs.models.CustomHeader;
import com.socketLabs.models.EmailAddress;
import com.socketLabs.core.serialization.InjectionRequestFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        InjectionRequestFactory factory = new InjectionRequestFactory(12345, "abcdefghijklmnop");

        BasicMessage message = new BasicMessage();

        message.setSubject("Test Subject");
        message.setPlainTextBody("This is an email");
        message.setFrom(new EmailAddress("ross.brazuk@socketlabs.com", "Ross Brazuk"));

        List<EmailAddress> to =  new ArrayList<>();
        to.add(new EmailAddress("example@example.com"));
        to.add(new EmailAddress("anotherexample@example.com"));
        message.setTo(to);

        // attachments
        List<Attachment> attachments = new ArrayList<>();
        attachments.add(new Attachment("/Users/ross.brazuk/Desktop/nic.jpg"));
        attachments.add(new Attachment("/Users/ross.brazuk/Desktop/text.txt"));
        message.setAttachments(attachments);


        // custom headers
        List<CustomHeader> customHeaders = new ArrayList<>();

        customHeaders.add(new CustomHeader("custom-header-1", "number 1"));
        customHeaders.add(new CustomHeader("custom-header-2", "number 2"));
        customHeaders.add(new CustomHeader("custom-header-3", "number 3"));
        customHeaders.add(new CustomHeader("custom-header-4", "number 4"));

        message.setCustomHeaders(customHeaders);

        //message.setApiTemplate(5);


        System.out.println(factory.GenerateRequest(message));

    }
}