package order;

import customer.Customer;

public class Payment {

    // represents the payment method used to make the order
    // consists of fields such as card name,cardnumber,expiry date, bank name and the success booleam
    // which represents if payment has been validated - and the field is used by other classes to proceed with the process or decline the order etc
    private final String card;
    private final String cardNumber;
    private final String expiryDate;
    private final String bank;
    private Customer customer;
    private final boolean success;

    // the constructor initialises the class fields with the arguments provided
    public Payment(String cardNumber, String expiryDate, String card, String bank) {
        this.cardNumber = cardNumber;
        this.card = card;
        this.expiryDate = expiryDate;
        this.bank = bank;

        // setting the payment valid flag - card and bank both must be valid
        boolean isCardValid = isValidCard(card);
        boolean isBankValid = isValidBank(bank);
        this.success = cardNumber != null && cardNumber.trim().length() == 16 && isCardValid && isBankValid;
    }

    //checks if the value of the card field is equivalent to the card name - MASTERCARD or VISA
    private boolean isValidCard(String card) {
        return "MASTERCARD".equalsIgnoreCase(card) || "VISA".equalsIgnoreCase(card);
    }

    // checks of the value of the bank field is equivalent to BOI or AIB
    private boolean isValidBank(String bank) {
        return "AIB".equalsIgnoreCase(bank) || "BOI".equalsIgnoreCase(bank);
    }


    public String getCardNumber() {
        return cardNumber;
    } // returns cardNumber of the card provided in payment

    public boolean isSuccess() {
        return success;
    } // returns the value of the payment valid flag

    public String getCard() {
        return card;
    } // returns card name

    public String getExpiryDate() {
        return expiryDate;
    } // returns expiry date of card

    public String getBank() {
        return bank;
    } // returns bank name of the card provided
}
