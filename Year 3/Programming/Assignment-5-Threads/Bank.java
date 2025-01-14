/**
 * Bank class for CT326 Assignment 5 (24/25)
 * @author Jay Popat (22346566)
 */
package com.a4;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;



/**
 * The Bank class is responsible for managing a collection of customer bank accounts
 * and processing transactions for the accounts.
 */

public class Bank {

    private final Map<Integer, Account> accounts; // account number -> account
    private final LinkedBlockingQueue<Transaction> transactions;
    private ExecutorService executorService;
    private final List<AutomatedBankClerk> clerks = new ArrayList<>();

    /**
     * Creates a new Bank with an empty collection of accounts and an empty queue of transactions.
     */
    public Bank() {
        this.accounts = new HashMap<>();
        transactions = new LinkedBlockingQueue<>();
    }
    /**
     * Adds an account to the bank.
     *
     * @param account the account to be added
     */
    void addAccountToBank(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    /**
     * Retrieves an account given its account number.
     *
     * @param accountNumber the account number
     * @return the account associated with the given account number
     */
    private Account getAccount(int accountNumber) {
        return accounts.get(accountNumber);
    }

    /**
     * Submits a transaction to the linked blocking queue for processing.
     *
     * @param transaction the transaction to be submitted
     * @throws RuntimeException if interrupted while waiting to put the transaction in the queue
     */
    public void submitTransaction(Transaction transaction) {
//        System.out.println("Submitting transaction " + transaction);
        try {
            transactions.put(transaction);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        System.out.println(transactions);
    }

    /**
     * Prints the details of all accounts (account number and balance (included in toString implementation)).
     */
    void printAccounts() {
        for (Account account : accounts.values()) {
            System.out.println(account); // tostring implementation prints account number and balance
        }
    }
    /**
     * Gets a collection of account numbers.
     *
     * @return a collection of account numbers (keys in the accounts map)
     */
    Collection<Integer> getCollectionOfAccountNums() {
        return accounts.keySet();
    }

    /**
     * Opens the bank by starting two AutomatedBankClerk threads using a thread pool.
     */
    void openBank() {
        executorService = Executors.newFixedThreadPool(2);
        AutomatedBankClerk c1 = new AutomatedBankClerk("TPT1");
        AutomatedBankClerk c2 = new AutomatedBankClerk("TPT2");
        clerks.add(c1);
        clerks.add(c2);
        executorService.submit(c1);
        executorService.submit(c2);
    }

    /**
     * Closes the bank by stopping the two AutomatedBankClerk threads using the thread pool.
     * This method waits for the threads to finish terminating or for 10 seconds before returning.
     */
    void closeBank() {

        for (AutomatedBankClerk clerk : clerks) {
            clerk.stopRunningClerk();
        }
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                System.out.println("Not all tasks completed. Forcing shutdown.");
                executorService.shutdownNow();
            }
        } catch (InterruptedException ie) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
    /**
     * The AutomatedBankClerk class represents a thread (inner class) that processes transactions
     * by depositing/withdrawing (methods specified in account class) from the appropriate account.
     */
    private class AutomatedBankClerk implements Runnable {
        private final String name;
        private final Random random;
        private int withdrawalCount = 0;
        private int depositCount = 0;
        private boolean running = true;

        AutomatedBankClerk(String name) {
            this.name = name;
            random = new Random();
        }

        /**
         * Runs the AutomatedBankClerk thread. The thread processes transactions by depositing/withdrawing
         * from the appropriate account, also containing logic for when to stop the thread from running ie
         * poison pill/timeout.
         */
        @Override
        public void run() {
            while (running) {
                try {
                    Transaction transaction = transactions.poll(5, TimeUnit.SECONDS);
                    if (transaction == null || transaction == Transaction.POISON_PILL) {
                        break;
                    }

                    processTransaction(transaction);
                } catch (InterruptedException e) {
                    System.out.println(name + " was interrupted. Exiting.");
                    break;
                }
            }
            System.out.println(name + " finished. Processed: " + withdrawalCount + " withdrawals, " + depositCount + " deposits.");
        }
        /**
         * Processes a transaction by depositing/withdrawing from the appropriate account.
         *
         * @param transaction the transaction to be processed
         * @throws RuntimeException if interrupted while waiting to put the transaction in the queue
         */
        private void processTransaction(Transaction transaction) {
            Account account = getAccount(transaction.getAccountNumber());

            if (account == null) {
                System.out.println("Account " + transaction.getAccountNumber() + " not found.");
                return;
            }
//            System.out.println("Processing transaction " + transaction);
            if (transaction.getAmount() > 0) {
                BigDecimal amount = BigDecimal.valueOf(transaction.getAmount()).setScale(2, RoundingMode.HALF_UP);


                System.out.printf("%s is processing a deposit of EUR %.2f to %d.%n",
                        name,
                        amount,
                        account.getAccountNumber());


                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                account.makeDeposit(Money.of(CurrencyUnit.EUR, amount));
//                System.out.println("balance after deposit: " + account.getBalance());
                depositCount++;
            } else {
                try {
                    BigDecimal amount = BigDecimal.valueOf(-transaction.getAmount()).setScale(2, RoundingMode.HALF_UP);
                    System.out.printf("%s is processing a withdrawal of EUR %.2f from %d.%n",
                            name,
                            amount,
                            account.getAccountNumber());
                    try {
                        Thread.sleep(random.nextInt(1000));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
//                    System.out.println("process fn => here for withdrawal");
                    account.makeWithdrawal(Money.of(CurrencyUnit.EUR, amount));
//                    System.out.println("balance after withdrawal: " + account.getBalance());
                    withdrawalCount++;
                } catch (InsufficientFundsException e) {
//                    System.out.println("Insufficient funds for withdrawal from account " + transaction.getAccountNumber());
                }
            }
        }
        /**
         * Stops the AutomatedBankClerk thread from running.
         */
        void stopRunningClerk() {
            this.running = false;
        }
    }
}
