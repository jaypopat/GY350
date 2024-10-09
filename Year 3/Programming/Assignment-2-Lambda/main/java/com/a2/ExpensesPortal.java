package com.a2;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * A portal for managing and printing its expenses.
 */
public class ExpensesPortal {
    private static final BigDecimal CONVERSION_RATE = new BigDecimal("0.85");
    private final List<Expense> expenses;

    /**
     * Constructs a new ExpensesPortal with an empty list of expenses.
     */
    public ExpensesPortal() {
        this.expenses = new ArrayList<>();
    }

    /**
     * Prints the list of expenses using the specified printer (any class object which implements the ExpensePrinter interface .
     *
     * @param printer the printer to use for printing the expenses
     */
    public void printExpenses(ExpensePrinter printer) {
        printer.print(expenses);
    }

    /**
     * Gets the list of expenses.
     *
     * @return the list of expenses
     */
    public List<Expense> getExpenses() {
        return expenses;
    }

    /**
     * Submits a new expense to the portal.
     *
     * @param expense the expense to be submitted
     */
    public void submitExpense(Expense expense) {
        expenses.add(expense);
    }

    /**
     * Sums the total amount of all expenses in EUR.
     *
     * @return the total amount of all expenses in EUR
     */
    public Money sumExpenses() {
        Money total = Money.zero(CurrencyUnit.EUR); // Start with zero amount in EUR

        for (Expense expense : expenses) {
            total = total.plus(convertToEur(expense.getAmount()));
        }

        return total;
    }

    /**
     * Converts the given amount to EUR.
     *
     * @param amount the amount to be converted
     * @return the converted amount in EUR
     * @throws IllegalArgumentException if the currency is unsupported
     */
    private Money convertToEur(Money amount) {
        String currencyCode = amount.getCurrencyUnit().getCode();
        return switch (currencyCode) { // can add more currencies down the line
            case "EUR" -> amount;
            case "USD" -> amount.convertedTo(CurrencyUnit.EUR, ExpensesPortal.CONVERSION_RATE, RoundingMode.HALF_UP);
            default -> throw new IllegalArgumentException("Unsupported currency: " + amount.getCurrencyUnit());
        };
    }
}