/**
 * Write a description of class Computer_Based_Exam here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Computer_Based_Exam extends Exam_Based_Assessment implements Gradable {
    // instance variables - replace the example below with your own
    private final String operatingSystem;

    /**
     * Constructor for objects of class Computer_Based_Exam
     */
    public Computer_Based_Exam(String operatingSystem, String date, String location, int durationHours, String assessmentName, String assessmentDescription, String lecturerName) {
        // initialise instance variables
        super(date, location, durationHours, assessmentName, assessmentDescription, lecturerName);
        this.operatingSystem = operatingSystem;
    }

    public boolean insertPercentage(int percentage) {
        if ((percentage < 0) || (percentage > 100)) {
            return false;
        }
        this.percentage = percentage;
        return true;
    }

    public int retrievePercentage() {
        return this.percentage;
    }

    private String returnOS() {
        return this.operatingSystem;
    }

    @Override
    public String toString() {
        return "Computer_Based_Exam{" + "operatingSystem='" + operatingSystem + '\'' + ", date='" + date + '\'' + ", location='" + location + '\'' + ", durationHours=" + durationHours + ", assessmentName='" + assessmentName + '\'' + ", assessmentDescription='" + assessmentDescription + '\'' + ", lecturerName='" + lecturerName + '\'' + ", percentage=" + percentage + '}';
    }
}
