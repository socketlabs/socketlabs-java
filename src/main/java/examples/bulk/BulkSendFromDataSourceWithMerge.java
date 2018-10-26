package examples.bulk;

import com.socketLabs.injectionApi.*;
import com.socketLabs.injectionApi.message.*;
import examples.*;
import examples.datasource.*;


import java.util.*;

public class BulkSendFromDataSourceWithMerge implements Example {
    @Override
    public SendResponse RunExample() throws Exception {

        SocketLabsClient client = new SocketLabsClient(ExampleConfig.ServerId, ExampleConfig.ApiKey);

        // Retrieve data from the datasource
        CustomerRepository repository = new CustomerRepository();
        List<Customer> customers = repository.getCustomers();

        // Build the message
        BulkMessage message = new BulkMessage();

        message.setSubject("Hello %%FirstName%%");
        message.setPlainTextBody("Hello %%FirstName%% %%LastName%%. Is your favorite color still %%FavoriteColor%%?");
        message.setFrom(new EmailAddress("from@example.com"));
        message.setReplyTo(new EmailAddress("replyto@example.com"));

        // Merge in the customers from the datasource
        List<BulkRecipient> recipients = new ArrayList<>();

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

        return client.send(message);
    }
}
