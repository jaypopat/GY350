package com.a2;

import java.util.List;

/**
 * A dummy printer that prints each expense in the list by iterating over each expense.
 */
public class DummyExpensePrinter implements ExpensePrinter {

    /**
     * This method prints each expense in the list.
     *
     * @param expenses the list of expenses to be printed
     */
    @Override
    public void print(List<Expense> expenses) {
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }
}