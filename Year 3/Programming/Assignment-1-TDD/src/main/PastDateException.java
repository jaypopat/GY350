// Jay Popat 22346566

// Custom exception class to handle scenarios where a booking date is in the past
public class PastDateException extends Exception {

    // Constructor that accepts an error message and passes it to the Exception class
    public PastDateException(String message) {
        super(message);
    }
}
