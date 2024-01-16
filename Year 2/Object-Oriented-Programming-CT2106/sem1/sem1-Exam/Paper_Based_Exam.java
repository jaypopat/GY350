/**
 * Write a description of class Paper_Based_Exam here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Paper_Based_Exam extends Exam_Based_Assessment implements Gradable {
    // instance variables - replace the example below with your own

    private final int numInvigilators;
    private int percentage;

    public Paper_Based_Exam(int numInvigilators, String date, String location, int durationHours, String assessmentName, String assessmentDescription, String lecturerName) {
        super(date, location, durationHours, assessmentName, assessmentDescription, lecturerName);
        // initialise instance variables
        this.numInvigilators = numInvigilators;
        this.percentage = percentage;

    }

    public boolean insertPercentage(int percentage) {
        if (percentage < 0 || percentage > 100) {
            return false;
        }
        this.percentage = percentage;
        return true;
        // will implement logic later
    }

    public int retrievePercentage() {
        return this.percentage;
    }

    public int getNumInvigilators() {
        return this.numInvigilators;
    }

    public int getPercent() {
        return this.percentage;
    }

    @Override
    public String toString() {
        return "Paper_Based_Exam{" +
                "numInvigilators=" + numInvigilators +
                ", percentage=" + percentage +
                ", date='" + date + '\'' +
                ", location='" + location + '\'' +
                ", durationHours=" + durationHours +
                ", assessmentName='" + assessmentName + '\'' +
                ", assessmentDescription='" + assessmentDescription + '\'' +
                ", lecturerName='" + lecturerName + '\'' +
                ", percentage=" + percentage +
                '}';
    }
}
