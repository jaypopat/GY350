import java.util.ArrayList;

public class TestAssessment {
    public static void main(String[] args) {

        ArrayList<Assessment> assessments = new ArrayList<>();

        Assessment a1 = new Quiz("MCQ", 30, "12-12-2023", "Inheritance", "Super Keyword", "Frank Glavin");
        Assessment a2 = new Essay(2000, "05/12/2023", "Inheritance", "Super Keyword", "Frank Glavin");
        Essay e = (Essay) a2;
        e.insertPercentage(90);
        Assessment a3 = new Computer_Based_Exam("ArchLinux", "3/12/2021", "Alice-Perry Building", 3, "Inheritance", "Super Keyword", "Frank Glavin");
        Computer_Based_Exam cbe = (Computer_Based_Exam) a3;
        cbe.insertPercentage(90);
        Assessment a4 = new Paper_Based_Exam(20, "5/12/2021", "IT Building", 2, "Inheritance", "Super Keyword", "Frank Glavin");
        Paper_Based_Exam pbe = (Paper_Based_Exam) a4;
        pbe.insertPercentage(90);


        assessments.add(a1);
        assessments.add(a2);
        assessments.add(a3);
        assessments.add(a4);
        int totalPercentage = 0;

        for (Assessment a : assessments) {
            System.out.println(a);
            if (a instanceof Essay) {
                int percent = e.retrievePercentage();
                totalPercentage += percent;

            }
            if (a instanceof Paper_Based_Exam paperExam) {
                int percent = paperExam.retrievePercentage();
                totalPercentage += percent;

            }
            if (a instanceof Computer_Based_Exam computerExam) {
                int percent = computerExam.retrievePercentage();
                totalPercentage += percent;

            }

        }
        System.out.println("Total percentage on gradable assignments is " + totalPercentage);

        // updating value
        boolean insertionSuccess = e.insertPercentage(50);
        if (insertionSuccess) {
            // insertion successful - showing updated object
            System.out.println(e);
            System.out.println(e.retrievePercentage());

        } else {
            System.out.println("Invalid percentage entered");
        }

    }
}
