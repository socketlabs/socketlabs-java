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

        BasicMessage message = new BasicMessage();

        message.setSubject("Sending A Message");
        message.setHtmlBody("<html>This is the Html Body of my message.</html>");
        message.setPlainTextBody("This is the Plain Text Body of my message.");

        message.setFrom(new EmailAddress("from@example.com"));
        message.setReplyTo(new EmailAddress("replyto@example.com"));

        message.getTo().add(new EmailAddress("recipient1@example.com"));
        message.getTo().add(new EmailAddress("recipient2@example.com", "Recipient #2"));

        message.addToEmailAddress(new EmailAddress("recipient3@example.com"));
        message.addToEmailAddress(new EmailAddress("recipient4@example.com", "Recipient #4"));



    }
}