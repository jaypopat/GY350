import com.a2.Expense;
import com.a2.ExpenseCategory;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseTest {

    private Expense expense;

    @BeforeEach
    void setUp() {
        expense = new Expense(LocalDate.of(2022, 9, 20),
                "Dell 17-inch monitor",
                ExpenseCategory.EQUIPMENT,
                Money.of(CurrencyUnit.USD, 540.00));
    }

    @Test
    void testGetDate() {
        assertEquals(LocalDate.of(2022, 9, 20), expense.getDate());
    }

    @Test
    void testGetDescription() {
        assertEquals("Dell 17-inch monitor", expense.getDescription());
    }

    @Test
    void testGetCategory() {
        assertEquals(ExpenseCategory.EQUIPMENT, expense.getCategory());
    }

    @Test
    void testGetAmount() {
        assertEquals(Money.of(CurrencyUnit.USD, 540.00), expense.getAmount());
    }

    @Test
    void testIsApprovedByDefault() {
        assertFalse(expense.isApproved());
    }

    @Test
    void testApproveExpense() {
        expense.approve();
        assertTrue(expense.isApproved());
    }

    @Test
    void testToString() {
        String expected = "2022-09-20: Dell 17-inch monitor - EQUIPMENT - USD 540.00";
        assertEquals(expected, expense.toString());
    }
}
