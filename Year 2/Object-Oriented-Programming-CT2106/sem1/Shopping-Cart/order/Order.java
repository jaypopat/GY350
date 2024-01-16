package order;

import customer.Customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// This class represents an Order.
// These fields are used to store the details of an order.

public class Order {
    //adding references to the the carts - will be defined in constructor
    private ShoppingCart cart; //  Represents the shopping cart associated with the order
    private Customer customer; // Represents the customer who placed the order
    private Payment payment; // Represents the payment method for the order

    private List<Item> items = new ArrayList<Item>();
    // initialised a array list which contains 'Item'
    private long orderId;
    // Stores randomly generated orderID
    private String date;
    // Represents the date when the order was placed - java.util packages were used in TransactionTest
    // to extract the current/live time

    private long makeOrderID() {
        Random rand = new Random();
        return Math.abs(rand.nextLong());
        // returns a random long utilising the Random class
    }

    //The constructor initializes the customer, cart, date, and orderId fields with the provided values as arguments.

    public Order(ShoppingCart cart, Customer customer, String date) {


        this.customer = customer; // initialises with the customer cart associated with order
        this.cart = cart; // initialises with the shopping cart associated with order
        this.date = date; // initialises with the date/time associated with order
        orderId = makeOrderID();
        // calling makeOrderID() returns a random long and assigns it to orderID (long)


        // This while loop continues as long as there are items in the cart.
        // It removes the first item from the cart and adds it to the items list in the order.
        // This effectively transfers all items from the cart to the order.
        while (!(cart.getItems().isEmpty())) {
            this.items.add(cart.getItems().remove(0));
        }
    }

    //     This method returns the unique ID of the order.
    public long getOrderId() {
        return orderId;
    }


    //     This method returns the items in the order.
    public List<Item> getShoppingCart() {
        return items;
    }

    //    This method calculates the total cost of the order.
//     It adds up the prices of all items in the order and returns the total cost
    public BigDecimal getTotalCost() {
        BigDecimal totalCost = new BigDecimal(0);
        for (Item item : items) {
            totalCost = totalCost.add(item.getPrice());
        }
        return totalCost;
    }

    public Customer getCustomer() {
        return customer;
    }
    //returns customer who placed order

    //    returns the shopping cart associated with the order.
    public ShoppingCart getCart() {
        return cart;
    }

    public Payment getPayment() {
        return payment;
    }

    public String getDate() {
        return date;
    }
    // returns date/time of the order

    public void getItems() {
        for (Item item : items) {
            System.out.println("Item ID: " + item.getItemId() + ", Item Name: " + item.getName());
        }
        // this method prints out the item details - id , name in a presentable,readable manner
    }

    // This method sets the payment method for the order.
    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
