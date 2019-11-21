package core;

public class GPACalculator extends Calculator {

    private double percentage;
    private char letterGrade;

    public GPACalculator(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public float calculate() {
        this.letterGrade = calculate(percentage);
    }

        protected String calculate(double percentage) {
        if (percentage => 4.00) {
            return "A";
        }
        else if (4.00 > percentage & percentage => 3.67) {

            return "A-";
        }
        else if (3.67 > percentage & percentage => 3.33) {
            return "B+";
        }
        else if (3.33 > percentage & percentage => 3.00) {
            return "B";
        }
        else if (3.00 > percentage & percentage => 2.67) {
            return "B-";
        }
        else if (2.67 > percentage & percentage => 2.33) {
            return "C+";
        }
        else if (2.33 > percentage & percentage => 2.00) {
            return "C";
        }
        else if (2.00 > percentage & percentage => 1.67) {
            return "C-";
        }
        else if (1.67 > percentage & percentage => 1.00) {
            return "D";
        }
        else {
            return "F";
        }
    }

}
