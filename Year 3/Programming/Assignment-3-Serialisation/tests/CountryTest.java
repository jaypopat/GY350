import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountryTest {

    /**
     * Tests the creation of a Country object and verifies its attributes.
     */
    @Test
    public void testCountryCreation() {
        Country country = new Country("USA", "US");
        assertEquals("USA", country.name());
        assertEquals("US", country.code());
    }
}