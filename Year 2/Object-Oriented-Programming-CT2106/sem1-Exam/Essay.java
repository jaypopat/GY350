/**
 * Write a description of class Essay here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Essay extends Continuous_Assessment implements Gradable {
    // instance variables - replace the example below with your own

    private int numWords;

    /**
     * Constructor for objects of class Essay
     */
    public Essay(int numWords, String dueDate, String assessmentName, String assessmentDescription, String lecturerName) {
        super(dueDate, assessmentName, assessmentDescription, lecturerName);
        this.numWords = numWords;
        this.dueDate = dueDate;
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

    private int retrievenumWords() {
        return this.numWords;
    }

    @Override
    public String toString() {
        return "Essay{" + "numWords=" + numWords + ", dueDate='" + dueDate + '\'' + ", assessmentName='" + assessmentName + '\'' + ", assessmentDescription='" + assessmentDescription + '\'' + ", lecturerName='" + lecturerName + '\'' + ", percentage=" + percentage + '}';
    }
}
