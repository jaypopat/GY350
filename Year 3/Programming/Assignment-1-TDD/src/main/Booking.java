// Jay Popat 22346566

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Booking class to represent a patient's booking at a health practice
public class Booking {
    // Static counter to generate unique booking IDs for each new booking
    private static int idCounter = 0;
    // Final fields for Booking ID, health practice, and booking date/time (immutable after initialization)
    private final int bookingID;
    private final HealthPractice healthPractice;
    private final LocalDateTime dateTime;
    // Field for patient number, which can be updated later if needed
    private String patientNumber;

    // Constructor to create a booking with a specific date/time, validates the date/time and other parameters
    public Booking(String patientNumber, HealthPractice healthPractice, LocalDateTime dateTime) throws PastDateException {

        // Check if the date/time is in the past, and throw a custom exception if it is
        if (dateTime.isBefore(LocalDateTime.now())) {
            throw new PastDateException("Booking date and time must be in the future");
        }
        // Validate that the patient number is not null or empty
        if (patientNumber == null || patientNumber.isEmpty()) {
            throw new IllegalArgumentException("Patient number cannot be null or empty");
        }
        // Validate that the health practice is not null
        if (healthPractice == null) {
            throw new IllegalArgumentException("Health practice cannot be null");
        }

        // Assign the validated parameters to the instance fields
        this.patientNumber = patientNumber;
        this.healthPractice = healthPractice;
        this.dateTime = dateTime;
        // Increment the booking ID counter and assign it to this booking
        this.bookingID = ++idCounter;
    }

    // Constructor to create a booking without a specified date/time, date is fetched via a web service
    public Booking(String patientNumber, HealthPractice healthPractice) {

        // Validate that the patient number is not null or empty
        if (patientNumber == null || patientNumber.isEmpty()) {
            throw new IllegalArgumentException("Patient number cannot be null or empty");
        }
        // Validate that the health practice is not null
        if (healthPractice == null) {
            throw new IllegalArgumentException("Health practice cannot be null");
        }

        // Assign the validated parameters to the instance fields
        this.patientNumber = patientNumber;
        this.healthPractice = healthPractice;
        // Fetch the booking date/time from a dummy web service for now (API will be implemented later)
        HealthPracticeAppointmentWebservice healthPracticeAppointmentWebservice = new DummyHealthPracticeAppointmentWebservice();
        this.dateTime = healthPracticeAppointmentWebservice.getBookingDateTime(healthPractice);
        // Increment the booking ID counter and assign it to this booking
        this.bookingID = ++idCounter;

    }

    // Getter method to retrieve the unique booking ID
    public int getBookingID() {
        return bookingID;
    }

    // Getter method to retrieve the patient number
    public String getPatientNumber() {
        return patientNumber;
    }

    // Setter method to update the patient number, validates the new value before updating
    public void setPatientNumber(String patientNumber) {
        if (patientNumber == null || patientNumber.isEmpty()) {
            throw new IllegalArgumentException("Patient number cannot be null or empty");
        }
        this.patientNumber = patientNumber;
    }

    // Getter method to retrieve the health practice associated with the booking
    public HealthPractice getHealthPractice() {
        return healthPractice;
    }

    // Getter method to retrieve the booking date and time
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    // Override of the toString() method to provide a formatted string representation of the booking details
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy 'at' HH:mm");
        String formattedDateTime = dateTime.format(formatter);

        return String.format(
                """
                        Booking ID Number: %d
                        Patient Number: %s
                        %s
                        Date & Time: On %s""",
                bookingID,
                patientNumber,
                healthPractice,
                formattedDateTime
        );
    }
}