public abstract class Assessment {
    // instance variables - replace the example below with your own
    String assessmentName;
    String assessmentDescription;
    String lecturerName;
    int percentage;

    /**
     * Constructor for objects of class Assessment
     */
    public Assessment(String assessmentName, String assessmentDescription, String lecturerName) {
        this.assessmentName = assessmentName;
        this.assessmentDescription = assessmentDescription;
        this.lecturerName = lecturerName;
    }

    @Override
    public String toString() {
        return "Assessment{" + "assessmentName='" + assessmentName + '\'' + ", assessmentDescription='" + assessmentDescription + '\'' + ", lecturerName='" + lecturerName + '\'' + ", percentage=" + percentage + '}';
    }
}
