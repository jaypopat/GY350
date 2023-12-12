/**
 * Write a description of class Quiz here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Quiz extends Continuous_Assessment {
    // instance variables - replace the example below with your own
    private final String type;
    private final int durationMins;


    /**
     * Constructor for objects of class Quiz
     */
    public Quiz(String type, int durationMins, String dueDate, String assessmentName, String assessmentDescription, String lecturerName) {
        super(dueDate, assessmentName, assessmentDescription, lecturerName);
        this.type = type;
        this.durationMins = durationMins;

    }

    @Override
    public String toString() {
        return "Quiz{" + "type='" + type + '\'' + ", durationMins=" + durationMins + ", dueDate='" + dueDate + '\'' + ", assessmentName='" + assessmentName + '\'' + ", assessmentDescription='" + assessmentDescription + '\'' + ", lecturerName='" + lecturerName + '\'' + ", percentage=" + percentage + '}';
    }
}
