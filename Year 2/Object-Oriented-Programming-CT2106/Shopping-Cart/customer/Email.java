package customer;

import order.Order;
import order.Payment;


// This class represents an Email.
// It contains fields for the customer, order, billing address, delivery address, and payment status.
// It also contains a method to print the email.


public class Email {

    private Customer customer; // Represents the customer who placed the order
    private Order order; // Represents the order placed by the customer

    private Address billingaddress; // Represents the billing address for the order
    private Address delivaddress; // Represents the delivery address for the order
    private boolean isSuccess; // Represents whether the payment for the order was successful


    //The constructor initializes the customer, order, billing address, delivery address, and payment status fields with the provided values.
    public Email(Customer customer, Payment payment, Order order, Address billingaddress, Address delivaddress) {
        this.customer = customer;
        this.order = order;
        this.billingaddress = billingaddress;
        this.delivaddress = delivaddress;
        isSuccess = payment.isSuccess(); // initialises with the boolean value of success field in the payment clss via a getter method
    }


    public void print() {
        // extracts information from the customer,order,address classes and uses getter methods on those to display the relevant information
        System.out.println("\nAn email has been sent out to " + customer.getEmail());

        if (isSuccess) {
            System.out.println("Hi " + customer.getName() + " | Customer ID - " + customer.getCustomerID() + "\nYour order ID is " + order.getOrderId() + "\nOrdered on " + order.getDate());
            System.out.println("\nItems ordered");
            order.getItems();
            System.out.println("Total cost " + order.getTotalCost() + " EUR" + "\n");
            System.out.println("Billing address\n" + billingaddress.AddressPrint() + "\n");
            System.out.println("Delivery Address\n\n" + delivaddress.AddressPrint());
        } else {
            System.out.println("Payment wasn't successful. Place order again");
        }
    }
}