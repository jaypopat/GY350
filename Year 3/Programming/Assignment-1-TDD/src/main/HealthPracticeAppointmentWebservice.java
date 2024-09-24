// Jay Popat 22346566

import java.time.LocalDateTime;

// Interface to represent a web service for retrieving the next available booking date and time for a given health practice
public interface HealthPracticeAppointmentWebservice {

    // Method to get the next available booking date and time for the specified health practice
    LocalDateTime getBookingDateTime(HealthPractice practice);
}
