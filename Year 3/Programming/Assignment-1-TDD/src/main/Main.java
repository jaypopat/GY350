// Jay Popat 22346566

import java.time.LocalDateTime;

public class Main {

    // the main function below emulates all the features outlined in the task document
    // such as creating a booking with a past date, creating a booking with a future date and creating a booking without a date
    public static void main(String[] args) throws PastDateException {

        Booking bookingWithoutTime = new Booking("wh1", new HealthPractice("Health Practice", "123 Fake St"));
//        System.out.println(bookingWithoutTime.getPatientNumber());
//        System.out.println(bookingWithoutTime.getHealthPractice());
//        System.out.println(bookingWithoutTime.getDateTime());
//        System.out.println(bookingWithoutTime.getBookingID());

        System.out.println("booking before overrwriting patient number");
        System.out.println(bookingWithoutTime);
        System.out.println();
        bookingWithoutTime.setPatientNumber("wh67");
//        System.out.println(bookingWithoutTime.getPatientNumber());
        System.out.println("booking after overrwriting patient number");
        System.out.println(bookingWithoutTime);
        System.out.println();


        System.out.println("booking with date and time in the past");
        try {
            Booking dateAndTimeInPast = new Booking("wh2", new HealthPractice("Health Practice", "123 Fake St"), LocalDateTime.of(2020, 9, 21, 10, 30));
        } catch (PastDateException e) {
            System.out.println("exception was thrown! " + e.getMessage());
        }
        System.out.println();

        System.out.println("booking with date and time in the future");
        Booking dateAndTimeInFuture = new Booking("wh3", new HealthPractice("Health Practice", "123 Fake St"), LocalDateTime.of(2024, 10, 29, 10, 30));
//        System.out.println(dateAndTimeInFuture.getPatientNumber());
//        System.out.println(dateAndTimeInFuture.getHealthPractice());
//        System.out.println(dateAndTimeInFuture.getDateTime());
//        System.out.println(dateAndTimeInFuture.getBookingID());

        System.out.println(dateAndTimeInFuture);
    }
}
