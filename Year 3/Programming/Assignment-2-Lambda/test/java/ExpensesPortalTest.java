import com.a2.*;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpensesPortalTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private ExpensesPortal portal;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        portal = new ExpensesPortal();
        originalOut = System.out; // Save the original System.out
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut); // Restore original System.out
    }

    private void submitSampleExpenses() {
        portal.submitExpense(new Expense(LocalDate.of(2022, 9, 23),
                "Flight to Glasgow",
                ExpenseCategory.TRAVEL_AND_SUBSISTENCE,
                Money.of(CurrencyUnit.EUR, 270.59)));
        portal.submitExpense(new Expense(LocalDate.of(2022, 9, 20),
                "Dell 17-inch monitor",
                ExpenseCategory.EQUIPMENT,
                Money.of(CurrencyUnit.USD, 540.00)));
        portal.submitExpense(new Expense(LocalDate.of(2022, 9, 21),
                "Java for Dummies",
                ExpenseCategory.OTHER,
                Money.of(CurrencyUnit.EUR, 17.99)));
    }

    @Test
    void testSubmitExpense() {
        Expense expense = new Expense(LocalDate.of(2022, 9, 20),
                "Dell 17-inch monitor",
                ExpenseCategory.EQUIPMENT,
                Money.of(CurrencyUnit.USD, 540.00));
        portal.submitExpense(expense);

        // Verify that the expense was added
        List<Expense> expenses = portal.getExpenses();
        assertEquals(1, expenses.size());
        assertEquals(expense, expenses.getFirst());
    }

    // not examinable just added for sake of completion
    @Test
    void testPrintExpensesWithClassImplementation() {
        submitSampleExpenses();
        ExpensePrinter printer = new DummyExpensePrinter();
        portal.printExpenses(printer);

        String expectedOutput = """
                2022-09-23: Flight to Glasgow - TRAVEL_AND_SUBSISTENCE - EUR 270.59
                2022-09-20: Dell 17-inch monitor - EQUIPMENT - USD 540.00
                2022-09-21: Java for Dummies - OTHER - EUR 17.99
                """;
        assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    void testPrintExpensesWithLambda() {
        submitSampleExpenses();
        // Using a lambda expression to implement ExpensePrinter
        portal.printExpenses(expenses -> {
            for (Expense expense : expenses) {
                System.out.println(expense);
            }
        });

        String expectedOutput = """
                2022-09-23: Flight to Glasgow - TRAVEL_AND_SUBSISTENCE - EUR 270.59
                2022-09-20: Dell 17-inch monitor - EQUIPMENT - USD 540.00
                2022-09-21: Java for Dummies - OTHER - EUR 17.99
                """;
        assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());

    }

    @Test
    void testPrintExpensesWithAnonymousClass() {
        submitSampleExpenses();

        portal.printExpenses(new ExpensePrinter() {
            @Override
            public void print(List<Expense> expenses) {
                Money total = portal.sumExpenses();
                System.out.println("There are " + expenses.size() + " expenses in the system totaling to a value of " + total);
            }
        });

        String expectedOutput = "There are 3 expenses in the system totaling to a value of " + portal.sumExpenses();
        assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    void testSumExpenses() {
        submitSampleExpenses();

        // Calculate expected total after conversion
        Money expectedTotal = Money.of(CurrencyUnit.EUR,
                270.59).plus(Money.of(CurrencyUnit.EUR,
                459.00)).plus(Money.of(CurrencyUnit.EUR,
                17.99)); // Sum of all expenses in EUR

        Money actualTotal = portal.sumExpenses();

        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    void getExpensesByLabel() {
        submitSampleExpenses();

        ExpensePrinter printer = new PrinterByLabel();
        printer.print(portal.getExpenses());


        String expectedOutput = """
                TRAVEL_AND_SUBSISTENCE
                2022-09-23: Flight to Glasgow - TRAVEL_AND_SUBSISTENCE - EUR 270.59

                SUPPLIES

                ENTERTAINMENT

                EQUIPMENT
                2022-09-20: Dell 17-inch monitor - EQUIPMENT - USD 540.00

                OTHER
                2022-09-21: Java for Dummies - OTHER - EUR 17.99""";
        assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
    }
}