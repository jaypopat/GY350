/**
 * Main class for CT326 Assignment 5 (24/25)
 * @author Jay Popat (22346566)
 */
package com.a4;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

/**
 * The Main class demonstrates the functionality of the Bank class by creating accounts,
 * generating random transactions, and processing them, essentially testing all the functionality of the program.
 */
public class Main {
    public static void main(String[] args) throws NegativeBalanceException {

        Bank b = new Bank();

        Account a1 = new Account(1, Money.of(CurrencyUnit.EUR, 12347));
        Account a2 = new Account(2, Money.of(CurrencyUnit.EUR, 12345));
        Account a3 = new Account(3, Money.of(CurrencyUnit.EUR, 12340));

        b.addAccountToBank(a1);
        b.addAccountToBank(a2);
        b.addAccountToBank(a3);

        RandomTransactionGenerator rtg = new RandomTransactionGenerator(b);
        // opens the bank
        b.openBank();
        Thread t = new Thread(rtg);
        t.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        b.closeBank();
        rtg.shutDown();

        try {
            t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        b.printAccounts();
    }
}