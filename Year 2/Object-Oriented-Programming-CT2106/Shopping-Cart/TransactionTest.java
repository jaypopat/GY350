import customer.Address;
import customer.Customer;
import customer.Email;
import order.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.math.BigDecimal;

public class TransactionTest {
    public static void main(String[] args) {

        TransactionTest test = new TransactionTest(); // creating an object of TransactionTest class
        test.transaction1(); // calling transaction1 method on the object
        test.transaction2(); // calling transaction2 method on the object
    }

    public void transaction1() {
        Address delivery = new Address("Street 1", "Galway", "H66R88", "Ireland");
        Address billing = new Address("Street 9", "Dublin", "H66R88", "Ireland");
        // Instantiates 2 Address objects - delivery and billing by passing in address details

        Customer customer = new Customer("Johnny Depp", "john@gmail.com", delivery, billing);
        // Instantiates customer object by passing in customer details

        LocalDateTime currentDateAndTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateAndTime.format(formatter);
        // used to extract the live/current time/date when the program was run/order was made


        ShoppingCart cart = new ShoppingCart(customer);
        // instantiates cart object by passing in the customer

        Item item1 = new Item("Item 1", BigDecimal.valueOf(10.0), 345);
        Item item2 = new Item("Item 2", BigDecimal.valueOf(20.0), 346);
        Item item3 = new Item("Item 3", BigDecimal.valueOf(20.5), 347);
        // instantiating 3 item objects with arguments of name,price and itemID

        cart.addItem(item1);
        cart.addItem(item2);
        cart.addItem(item3);
        // uses the addItem method in cart to add the 3 item objects to the cart


        Order order = new Order(cart, customer, formattedDateTime);
        // order object is instanced by passing in the cart customer and the date/time initialised above

        cart.close();
        // calls the close method which sets a boolean to true - which essentially locks the cart ie operations such as add and remove cannot be performed now


        // payment object has been instanced by passing in payment details - these are validated within the payment object itself
        Payment payment = new Payment("1111111111111111", "23.02", "MASTERCARD", "AIB");
        order.setPayment(payment);

        // if the payment has been validated from above
        // an email object is instantiated passing in all the details - customer,payment,order and the addresses so details can be printed in the email
        Email email = new Email(customer, payment, order, delivery, billing);
        // if the payment has been validated from above (payment) the email displayed shows all the details and confirms the order
        // else it displays a failed payment email by the print method below
        email.print();
        System.out.println("-----------------------------------------------");
    }

    public void transaction2() {
        Address delivery = new Address("Street 1", "Galway", "H66R88", "Ireland");
        Address billing = new Address("Street 9", "Dublin", "H66R88", "Ireland");
        // Instantiates 2 Address objects - delivery and billing by passing in address details


        Customer customer = new Customer("Johnny Depp", "john@gmail.com", delivery, billing);
        // Instantiates customer object by passing in customer details

        LocalDateTime currentDateAndTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateAndTime.format(formatter);
        // used to extract the live/current time/date when the program was run/order was made


        ShoppingCart cart = new ShoppingCart(customer);
        // instantiates cart object by passing in the customer


        Item item1 = new Item("Item 1", BigDecimal.valueOf(10.0), 345);
        Item item2 = new Item("Item 2", BigDecimal.valueOf(20.0), 346);
        Item item3 = new Item("Item 3", BigDecimal.valueOf(20.5), 347);
        // instanciating 3 item objects with arguments of name,price and itemID


        cart.addItem(item1);
        cart.addItem(item2);
        cart.addItem(item3);
        // uses the addItem method in cart to add the 3 item objects to the cart


        cart.removeItem(item1);
        // removes item1 from the cart

        Order order = new Order(cart, customer, formattedDateTime);
        // order object is instanced by passing in the cart customer and the date/time initialised above

        cart.close();
        // calls the close method which sets a boolean to true - which essentially locks the cart ie operations such as add and remove cannot be performed now

        Payment payment = new Payment("1", "23.02", "MASTERCARDD", "AIBB");
        // payment object has been instanced by passing in payment details - these are validated within the payment object itself
        order.setPayment(payment);

        // an email object is instantiated passing in all the details - customer,payment,order and the addresses so details can be printed in the email
        Email email = new Email(customer, payment, order, delivery, billing);
        // in transaction2 as the payment method passed is invalid an error message will be shown in the email
        email.print();
    }
}