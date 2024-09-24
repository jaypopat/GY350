// Jay Popat 22346566

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DoctorBookingTest {

    private HealthPractice practice;
    private LocalDateTime validDateTime;

    // Set up method that initializes common test data before each test
    @BeforeEach
    void setUp() {
        // Initialize a HealthPractice instance and a valid future date/time for testing
        practice = new HealthPractice("Health Center", "123 Main St");
        validDateTime = LocalDateTime.of(2024, 9, 29, 10, 30);
    }

    // Test creating a booking with a specific date/time
    @Test
    void testCreateBookingWithDateTime() throws PastDateException {
        Booking booking = new Booking("WH1659741", practice, validDateTime);
        // Verify that the booking has the correct patient number, practice, and date/time
        assertEquals("WH1659741", booking.getPatientNumber());
        assertEquals(practice, booking.getHealthPractice());
        assertEquals(validDateTime, booking.getDateTime());
    }

    // Test retrieving the patient number from a booking
    @Test
    void testGetPatientNumber() throws PastDateException {
        Booking booking = new Booking("WH1659741", practice, validDateTime);
        assertEquals("WH1659741", booking.getPatientNumber());
    }

    // Test updating the patient number using the setter method
    @Test
    void testSetPatientNumber() throws PastDateException {
        Booking booking = new Booking("WH1659741", practice, validDateTime);
        booking.setPatientNumber("WH1659742");
        assertEquals("WH1659742", booking.getPatientNumber());
    }

    // Test retrieving the health practice from a booking
    @Test
    void testGetHealthPractice() throws PastDateException {
        Booking booking = new Booking("WH1659741", practice, validDateTime);
        assertEquals(practice, booking.getHealthPractice());
    }

    // Test creating a booking without specifying a date/time (automatically set via web service)
    @Test
    void testCreateBookingWithoutDateTime() {
        Booking booking = new Booking("WH1659741", practice);
        // Verify that the patient number and health practice are correct
        assertEquals("WH1659741", booking.getPatientNumber());
        assertEquals(practice, booking.getHealthPractice());
        // Ensure the booking date/time is not null and is in the future
        assertNotNull(booking.getDateTime());
        assertTrue(booking.getDateTime().isAfter(LocalDateTime.now()));
    }

    // Test creating a booking with a date/time in the past, which should throw a PastDateException
    @Test
    void testCreateBookingWithDateTimeInThePast() {
        LocalDateTime pastDateTime = LocalDateTime.of(2020, 9, 21, 10, 30);
        // Ensure that the exception is thrown with the correct message
        PastDateException exception = assertThrows(PastDateException.class, () -> {
            new Booking("WH1659746", practice, pastDateTime);
        });

        assertEquals("Booking date and time must be in the future", exception.getMessage());
    }

    // Test that booking IDs are unique across multiple bookings
    @Test
    void testUniqueBookingIds() {
        Booking booking1 = new Booking("WH1659741", practice);
        Booking booking2 = new Booking("WH1659742", practice);
        Booking booking3 = new Booking("WH1659743", practice);
        Booking booking4 = new Booking("WH1659744", practice);
        // Verify that each booking has a unique ID
        assertNotEquals(booking1.getBookingID(), booking2.getBookingID());
        assertNotEquals(booking1.getBookingID(), booking3.getBookingID());
        assertNotEquals(booking1.getBookingID(), booking4.getBookingID());
        assertNotEquals(booking2.getBookingID(), booking3.getBookingID());
        assertNotEquals(booking2.getBookingID(), booking4.getBookingID());
        assertNotEquals(booking3.getBookingID(), booking4.getBookingID());
    }

    // Test the toString() method to ensure it correctly formats the booking information
    @Test
    void testToString() throws PastDateException {
        Booking booking = new Booking("WH1659741", practice, validDateTime);

        assertEquals("""
                        Booking ID Number: 4
                        Patient Number: WH1659741
                        Health Practice: Health Center
                        Address: 123 Main St
                        Date & Time: On Sunday, 29 September 2024 at 10:30""",
                booking.toString());
    }

    // Test that creating a HealthPractice with a null or empty name throws an IllegalArgumentException
    @Test
    void testHealthPracticeInvalidName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new HealthPractice(null, "123 Main St");
        });
        assertEquals("Name cannot be null or empty.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            new HealthPractice("", "123 Main St");
        });
        assertEquals("Name cannot be null or empty.", exception.getMessage());
    }

    // Test that creating a HealthPractice with a null or empty address throws an IllegalArgumentException
    @Test
    void testHealthPracticeInvalidAddress() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new HealthPractice("Health Center", null);
        });
        assertEquals("Address cannot be null or empty.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            new HealthPractice("Health Center", "");
        });
        assertEquals("Address cannot be null or empty.", exception.getMessage());
    }

    // Test that setting an invalid patient number (null or empty) throws an IllegalArgumentException
    @Test
    void testSetPatientNumberInvalid() throws PastDateException {
        Booking booking = new Booking("WH1659741", practice, validDateTime);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            booking.setPatientNumber("");
        });
        assertEquals("Patient number cannot be null or empty", exception.getMessage());
    }
}
