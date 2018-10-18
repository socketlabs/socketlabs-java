import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import models.BasicMessage;
import models.EmailAddress;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args)  {
        BasicMessage message = new BasicMessage();

        List<EmailAddress> addresses = new ArrayList<>();

        addresses.add(new EmailAddress("ross.brazuk@socketlabs.com", "Ross Brazuk"));

        message.setTo(addresses);

        message.setSubject("Java SDK Test");

        message.setPlainTextBody("Java test plain text body");

        message.setHtmlBody("<h1>Java Test</h1><p>SDK test</p>");

        message.setMailingId("java");

        message.setMessageId("javaTest1");

        message.setFrom(new EmailAddress("ross.brazuk@gekodesign.net", "Ross @ Socketlabs"));

        message.setReplyTo(new EmailAddress("ross.brazuk@gekodesign.net", "Ross @ Socketlabs"));



        ObjectMapper mapper = new ObjectMapper();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            String json = mapper.writeValueAsString(message);
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}