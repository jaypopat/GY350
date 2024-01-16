/**
 * Write a description of class Continuous_Assessment here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Exam_Based_Assessment extends Assessment {
    // instance variables - replace the example below with your own
    String date;
    String location;
    int durationHours;

    public Exam_Based_Assessment(String date, String location, int durationHours, String assessmentName, String assessmentDescription, String lecturerName) {
        super(assessmentName, assessmentDescription, lecturerName);
        this.date = date;
        this.location = location;
        this.durationHours = durationHours;
    }

    @Override
    public String toString() {
        return "Exam_Based_Assessment{" + "date='" + date + '\'' + ", location='" + location + '\'' + ", durationHours=" + durationHours + ", assessmentName='" + assessmentName + '\'' + ", assessmentDescription='" + assessmentDescription + '\'' + ", lecturerName='" + lecturerName + '\'' + ", percentage=" + percentage + '}';
    }
}
