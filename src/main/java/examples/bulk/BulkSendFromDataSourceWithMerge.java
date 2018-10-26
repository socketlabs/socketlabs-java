package examples.bulk;

import com.socketLabs.injectionApi.SendResponse;
import com.socketLabs.injectionApi.SocketLabsClient;
import com.socketLabs.injectionApi.message.BulkMessage;
import com.socketLabs.injectionApi.message.BulkRecipient;
import com.socketLabs.injectionApi.message.EmailAddress;
import examples.Example;
import examples.ExampleConfig;
import examples.datasource.Customer;
import examples.datasource.CustomerRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            Map<String, String> mergeData = new HashMap<>();
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
