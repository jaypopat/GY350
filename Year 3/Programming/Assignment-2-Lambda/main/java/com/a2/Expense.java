package com.a2;

import org.joda.money.Money;

import java.time.LocalDate;

/**
 * Represents an expense with a date, description, category, amount, and approval status.
 */
public class Expense {

    private final LocalDate date;
    private final String description;
    private final ExpenseCategory category;
    private final Money amount;
    private boolean isApproved;

    /**
     * Constructs a new Expense with a constructor with the fields below.
     *
     * @param date the date of the expense
     * @param description the description of the expense
     * @param category the category of the expense
     * @param amount the amount of the expense
     */
    public Expense(LocalDate date, String description, ExpenseCategory category, Money amount) {
        this.date = date;
        this.description = description;
        this.category = category;
        this.amount = amount;
        this.isApproved = false;
    }

    /**
     * Gets the date of the expense.
     *
     * @return the date of the expense in the format of yyyy-MM-dd
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the description of the expense.
     *
     * @return the description of the expense
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the category of the expense in the form of an enum value.
     *
     * @return the category of the expense
     */
    public ExpenseCategory getCategory() {
        return category;
    }

    /**
     * Gets the amount of the expense in the form of a Joda Money Object.
     *
     * @return the amount of the expense
     */
    public Money getAmount() {
        return amount;
    }

    /**
     * Checks if the expense is approved.
     *
     * @return true if the expense is approved, false otherwise
     */
    public boolean isApproved() {
        return isApproved;
    }

    /**
     * Approves the expense.
     */
    public void approve() {
        this.isApproved = true;
    }

    /**
     * Returns a string representation of the expense.
     *
     * @return a string representation of the expense
     */
    @Override
    public String toString() {
        return String.format("%s: %s - %s - %s", date, description, category, amount);
    }
}