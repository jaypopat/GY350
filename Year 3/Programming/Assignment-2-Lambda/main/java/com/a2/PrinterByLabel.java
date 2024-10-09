package com.a2;

import java.util.List;

/**
 * A printer that prints expenses grouped by their category labels.
 */
public class PrinterByLabel implements ExpensePrinter {

    /**
     * Prints the list of expenses grouped by their category labels.
     *
     * @param expenses the list of expenses to be printed
     */
    @Override
    public void print(List<Expense> expenses) {
        for (ExpenseCategory category : ExpenseCategory.values()) {
            System.out.println();
            System.out.println(category);
            for (Expense expense : expenses) {
                if (expense.getCategory().equals(category)) {
                    System.out.println(expense);
                }
            }
        }
    }
}