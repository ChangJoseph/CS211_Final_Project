package core;

public class GPACalculator<E extends String> extends Calculator<E> {

    private double currGPA;
    private StudentMagic classes;

    public GPACalculator(double currGPA) {
        this.currGPA = currGPA;
    }

//    @Override
//    public float calculate() {
//        this.letterGrade = calculate(percentage);
//    }


    /**
     * calculates the gpa given all classes student is taking
     * @return
     */
    @Override
    public float calculate() {
//        (currGPA + classesGPA) / 2;
        return 0f;
    }

    public static String floatToLetterGrade(double percentage) {
        if (percentage <= 4.00) {
            return "A";
        }
        else if (4.00 > percentage && percentage >= 3.67) {

            return "A-";
        }
        else if (3.67 > percentage && percentage >= 3.33) {
            return "B+";
        }
        else if (3.33 > percentage && percentage >= 3.00) {
            return "B";
        }
        else if (3.00 > percentage && percentage >= 2.67) {
            return "B-";
        }
        else if (2.67 > percentage && percentage >= 2.33) {
            return "C+";
        }
        else if (2.33 > percentage && percentage >= 2.00) {
            return "C";
        }
        else if (2.00 > percentage && percentage >= 1.67) {
            return "C-";
        }
        else if (1.67 > percentage && percentage >= 1.00) {
            return "D";
        }
        else {
            return "F";
        }
    }

}
