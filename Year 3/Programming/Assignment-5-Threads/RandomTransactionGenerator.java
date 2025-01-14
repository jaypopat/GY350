/**
 * RandomTransactionGenerator class for CT326 Assignment 5 (24/25)
 * @author Jay Popat (22346566)
 */
package com.a4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * The RandomTransactionGenerator class generates random deposit and withdrawal transactions
 * for random accounts in the bank and submits them to the bank queue for processing.
 */

public class RandomTransactionGenerator implements Runnable {
    private final Bank bank;
    private final Random random;
    private boolean running = true;

    /**
     * Creates a new RandomTransactionGenerator for the given bank.
     *
     * @param bank the bank for which transactions are to be generated
     */
    public RandomTransactionGenerator(Bank bank) {
        this.bank = bank;
        this.random = new Random();
    }

    /**
     * Generates a random transaction for a random account in the bank.
     *
     * @return a randomly generated transaction
     */
    private Transaction generateRandomTransaction() {
        Collection<Integer> accountNumbers = bank.getCollectionOfAccountNums();
        if (accountNumbers.isEmpty()) {
            throw new IllegalStateException("No accounts available in the bank");
        }
        List<Integer> accountList = new ArrayList<>(accountNumbers);
        int idx = random.nextInt(accountList.size());
        int selectedAccountNumber = accountList.get(idx);


        float amount = (random.nextFloat() * 10000); // Random amount between 0 and 10000

        // Randomly decide if it's a deposit or withdrawal
        if (random.nextBoolean()) {
            amount = -amount; // Make it a withdrawal
        }

        return new Transaction(selectedAccountNumber, amount);
    }

    /**
     * Generates random transactions with random delays in between and submits them to the bank queue for processing.
     * A poison pill transaction is submitted to the queue when the generator is shut down.
     */
    @Override
    public void run() {
        while (running) {
            try {
                Transaction transaction = generateRandomTransaction();
                Thread.sleep(random.nextInt(1000));
                //System.out.println("Generated " + transaction);
                bank.submitTransaction(transaction);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        bank.submitTransaction(Transaction.POISON_PILL);
    }
    /**
     * Shuts down the transaction generator ie stops the thread loop.
     */
    void shutDown() {
        this.running = false;
    }
}
