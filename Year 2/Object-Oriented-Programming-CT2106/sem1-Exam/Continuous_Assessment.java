/**
 * Write a description of class Continuous_Assessment here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Continuous_Assessment extends Assessment {
    // instance variables - replace the example below with your own
    String dueDate;


    public Continuous_Assessment(String dueDate, String assessmentName, String assessmentDescription, String lecturerName) {
        super(assessmentName, assessmentDescription, lecturerName);
        this.dueDate = dueDate;

    }

    @Override
    public String toString() {
        return "Continuous_Assessment{" +
                "dueDate='" + dueDate + '\'' +
                ", assessmentName='" + assessmentName + '\'' +
                ", assessmentDescription='" + assessmentDescription + '\'' +
                ", lecturerName='" + lecturerName + '\'' +
                ", percentage=" + percentage +
                '}';
    }
}
