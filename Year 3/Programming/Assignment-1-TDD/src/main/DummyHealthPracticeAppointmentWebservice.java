// // Jay Popat 22346566
import java.time.LocalDateTime;

public class DummyHealthPracticeAppointmentWebservice implements HealthPracticeAppointmentWebservice {
    @Override
    public LocalDateTime getBookingDateTime(HealthPractice practice) {
    // this method implementation can be changed to return a time from api response from a real webservice
        return LocalDateTime.now().plusMonths(1);
    }
}
