package customer;

import java.util.Random;

// This class represents a Customer.
// These fields are used to store the details of the customer.
public class Customer {
    private String name; // represents name of customer
    private String email;// represents email of customer
    private Address billingAddress; // holds a reference to the Address class - will store the billing address
    private Address deliveryAddress;  // holds a reference to the Address class - will store the delivery address
    private final long customerID; // represents the unique customerId (randomly generated)

    public Customer(String name, String email, Address billingAddress, Address deliveryAddress) {
        this.name = name;
        this.email = email;
        this.billingAddress = billingAddress;
        this.deliveryAddress = deliveryAddress;
        this.customerID = makeCustomerID();
        // calling makeCustomerID() returns a random long and assigns it to customerID (long)

    }

    private long makeCustomerID() {
        Random rand = new Random();
        return Math.abs(rand.nextLong());
        // returns a random long utilising the java,util,Random package
    }

    public String getName() {
        return name;
    }
    // returns name of customer

    public String getEmail() {
        return email;
    }
    // returns email of customer

    public long getCustomerID() {
        return customerID;
    }
    // returns the unique customer id of the customer

    // returns a string representation of the item.

    @Override
    public String toString() {
        return "Customer{" + "name='" + name + '\'' + ", email='" + email + '\'' + ", billingAddress=" + billingAddress + ", deliveryAddress=" + deliveryAddress + '}';
    }
}
