package core;

public class CumulativeGPACalculator<E extends String> extends Calculator<E> {

    private double currentAddedGPA, previousCumulativeGPA;
    private int currentAttemptedCreditHours, previousCumulativeCreditHours;

    public CumulativeGPACalculator(double currentAddedGPA, int currentAttemptedCreditHours, double previousCumulativeGPA, int previousCreditHours) {
        this.currentAddedGPA = currentAddedGPA;
        this.currentAttemptedCreditHours = currentAttemptedCreditHours;
        this.previousCumulativeGPA = previousCumulativeGPA;
        this.previousCumulativeCreditHours = previousCreditHours;
    }

//    @Override
//    public float calculate() {
//        this.letterGrade = calculate(percentage);
//    }


    @Override
    public float calculate() {
        double currentGPA;
        return 0f;
    }

    public String getLetterGrade(double gpa) {
        if (gpa <= 4.00) {
            return "A";
        }
        else if (4.00 > gpa && gpa >= 3.67) {

            return "A-";
        }
        else if (3.67 > gpa && gpa >= 3.33) {
            return "B+";
        }
        else if (3.33 > gpa && gpa >= 3.00) {
            return "B";
        }
        else if (3.00 > gpa && gpa >= 2.67) {
            return "B-";
        }
        else if (2.67 > gpa && gpa >= 2.33) {
            return "C+";
        }
        else if (2.33 > gpa && gpa >= 2.00) {
            return "C";
        }
        else if (2.00 > gpa && gpa >= 1.67) {
            return "C-";
        }
        else if (1.67 > gpa && gpa >= 1.00) {
            return "D";
        }
        else {
            return "F";
        }
    }

}
