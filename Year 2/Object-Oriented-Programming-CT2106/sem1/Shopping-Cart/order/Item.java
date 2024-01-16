package order;

import java.math.BigDecimal;
import java.util.Currency;


// This class represents an Item.
// It contains four fields: name, currency, price, and itemId.
// These fields are used to store the details of an item.

public class Item {
    private final String name; // Represents the name of the item
    private final Currency currency = Currency.getInstance("EUR"); // Represents the currency of the item, default is EUR
    private final BigDecimal price; // Represents the price of the item
    private final long itemId; // Represents the unique ID of the item

//     This is the constructor for the Item class.
//     It initializes the name, price, and itemId fields with the provided values.
//     The currency field is initialized with EUR.

    public Item(String name, BigDecimal price, long itemId) {
        this.name = name;
        this.price = price;
        this.itemId = itemId;
    }


    //     This method returns the unique ID of the item.

    public long getItemId() {
        return itemId;
    }

//     This method returns the name of the item.

    public String getName() {
        return name;
    }

    //     This method returns the currency of the item.

    public Currency getCurrency() {
        return currency;
    }

    //     This method returns the price of the item.

    public BigDecimal getPrice() {
        return price;
    }

//     This method returns a string representation of the item.

    @Override
    public String toString() {
        return "order.Item{" + "name='" + name + '\'' + ", currency=" + currency + ", price=" + price + '}';
    }
}
