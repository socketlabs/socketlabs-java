package examples.Datasource;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    //For our example we are using a mock repository class that returns hard-coded data.
    //Normally this class would access a database to retrieve this data.
    public List<Customer> GetCustomers()
    {
        List<Customer> customers = new ArrayList<>();

        customers.add(new Customer("Recipient", "One", "recipient1@example.com", "Green"));
        customers.add(new Customer("Recipient", "Two", "recipient2@example.com", "Red"));
        customers.add(new Customer("Recipient", "Three", "recipient3@example.com", "Blue"));
        customers.add(new Customer("Recipient", "Four", "recipient4@example.com", "Orange"));

        return customers;
    }


}
