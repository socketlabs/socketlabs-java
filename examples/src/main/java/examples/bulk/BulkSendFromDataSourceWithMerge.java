package examples.bulk;

import com.socketLabs.injectionApi.SendResponse;
import com.socketLabs.injectionApi.SocketLabsClient;
import com.socketLabs.injectionApi.message.BulkMessage;
import com.socketLabs.injectionApi.message.BulkRecipient;
import com.socketLabs.injectionApi.message.EmailAddress;
import examples.*;
import examples.datasource.*;


import java.util.*;

public class BulkSendFromDataSourceWithMerge implements Example {
    @Override
    public SendResponse RunExample() throws Exception {

        // Build the message
        BulkMessage message = new BulkMessage();

        message.setSubject("Hello %%FirstName%%");

        // Build the Content (Note the %% symbols used to denote the data to be merged)
        String html = "<html>"
                + "   <body>"
                + "       <h1>Sending A Test Message With Merge Data From Datasource</h1>"
                + "       <h2>Hello %%FirstName%% %%LastName%%.</h2>"
                + "       <p>Is your favorite color still %%FavoriteColor%%?</p>"
                + "   </body>"
                + "</html>";
        message.setHtmlBody(html);

        String text = "Sending A Test Message With Merge Data From Datasource"
                + "       Hello %%FirstName%% %%LastName%%. Is your favorite color still %%FavoriteColor%%?";
        message.setPlainTextBody(text);

        message.setFrom(new EmailAddress("from@example.com"));

        // Merge in the customers from the datasource
        List<BulkRecipient> recipients = new ArrayList<>();

        // Retrieve data from the datasource
        CustomerRepository repository = new CustomerRepository();
        List<Customer> customers = repository.getCustomers();

        // Merge in the customers from the datasource
        for (Customer customer : customers) {
            BulkRecipient recipient = new BulkRecipient(customer.getEmailAddress(), customer.getFirstName());
            TreeMap<String, String> mergeData = new TreeMap<>();
            mergeData.put("FirstName", customer.getFirstName());
            mergeData.put("LastName", customer.getLastName());
            mergeData.put("FavoriteColor", customer.getFavoriteColor());
            recipient.setMergeData(mergeData);

            recipients.add(recipient);
        }

        message.setTo(recipients);

        // create the client
        SocketLabsClient client = new SocketLabsClient(ExampleConfig.ServerId, ExampleConfig.ApiKey);

        // send the message
        SendResponse response =  client.send(message);

        return response;
    }
}
