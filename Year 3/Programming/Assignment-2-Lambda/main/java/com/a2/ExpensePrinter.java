package com.a2;

import java.util.List;

/**
 * Interface for printing a list of expenses.
 */
public interface ExpensePrinter {

    /**
     * Prints the list of expenses.
     *
     * @param expenses the list of expenses to be printed
     */
    void print(List<Expense> expenses);
}