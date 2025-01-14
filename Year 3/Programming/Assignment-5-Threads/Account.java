/**
 * Account class for CT326 Assignment 5 (24/25)
 * @author Adrian Clear
 */
package com.a4;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.io.*;
import java.time.LocalDateTime;


public class Account implements Serializable {
    // Define serialVersionUID which is used during object deserialization to
    // verify that the sender and receiver of a serialized
    // object have loaded classes for that object that are compatible - will
    // throw InvalidClassException if not compatible
    // IDEs will often auto generate this value for you when creating new Java
    // classes

    @Serial
    private static final long serialVersionUID = 202010191519L;
    private int accNum;
    private Money balance;
    // Date and Time account object was first created or loaded from storage
    private LocalDateTime activated;

    /**
     * Creates an account with a given account number and balance.
     *
     * @param accNum  the account number
     * @param balance the initial balance of the account
     * @throws NegativeBalanceException if the initial balance is negative
     */
    public Account(int accNum, Money balance) throws NegativeBalanceException {
        setBalance(balance);
        this.accNum = accNum;
        activated = LocalDateTime.now();
    }

    /**
     * Make a deposit to the account of the given amount
     *
     * @param amount the amount to deposit
     */
    public synchronized void makeDeposit(Money amount) {
//        System.out.println("Making deposit of " + amount);
        if (amount.isGreaterThan(Money.of(CurrencyUnit.EUR, 0))) {
            try {
                setBalance(balance.plus(amount));
//                System.out.println("New balance after deposit: " + balance);
            } catch (NegativeBalanceException e) {
                // This should never happen for deposits, but handle it just in case
                System.out.println("Unexpected error during deposit: " + e.getMessage());
            }
        }
    }

    /**
     * Make a withdrawal from the account of the given amount
     *
     * @param amount the amount to withdraw
     * @throws InsufficientFundsException if the amount to withdraw is greater
     *                                    than the current balance
     */
    public synchronized void makeWithdrawal(Money amount) throws InsufficientFundsException {
//        System.out.println("Making withdrawal of " + amount);
//        System.out.println("Current balance: " + balance);
        if (balance.isLessThan(amount)) {
            throw new InsufficientFundsException();
        }
        try {
            setBalance(balance.minus(amount));
//            System.out.println("New balance after withdrawal: " + balance);
        } catch (NegativeBalanceException e) {
            // This should never happen due to the check above, but handle it just in case
            throw new InsufficientFundsException();
        }
    }

    /**
     * Get the balance of the account
     *
     * @return the balance of the account
     */
    public Money getBalance() {
        return balance;
    } // used for debugging

    /**
     * Set the balance of the account to the given amount.
     *
     * @param balance the new balance of the account
     * @throws NegativeBalanceException if the balance is negative
     */
    private void setBalance(Money balance) throws NegativeBalanceException {
//        System.out.println("Setting balance to " + balance);
        if (balance.isLessThan(Money.of(CurrencyUnit.EUR, 0))) throw new
                NegativeBalanceException("Negative Balance Not Allowed!");
        this.balance = balance;
    }

    /**
     * Get the account number of the account
     *
     * @return the account number of the account
     */
    public int getAccountNumber() {
        return accNum;
    }

    /**
     * Get the activation date of the account
     *
     * @return the activation date of the account
     */
    public LocalDateTime getActivated() {
        return activated;
    }

    // This method is called when we are deserializing an instance of an Account
//    object
    @Serial
    private void readObject(ObjectInputStream aInputStream) throws
            ClassNotFoundException, IOException {
        balance = (Money) aInputStream.readObject();
        accNum = aInputStream.readInt();
    // Reinitialise value for the LocalDateTime activated variable
        activated = LocalDateTime.now();
    }

    // This method is called when we serialize an instance of an Account object
    @Serial
    private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
        aOutputStream.writeObject(balance);
        aOutputStream.writeInt(accNum);
    //// We don't write value for the LocalDateTime activated variable as we
    //   will reinitialise this when reloading the object
    }

    @Override
    public String toString() {
        return String.format("Account %d has a balance of %s.", accNum, balance);
    }
}