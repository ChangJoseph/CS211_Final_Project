package core;

import java.util.Collection;

public class SemesterGPACalculator extends Calculator {

    Collection<Double> finalGrades;
    int totalCredits;

    public SemesterGPACalculator(Collection<Double> finalGrades, int totalCredits) {
        this.finalGrades = finalGrades;
        this.totalCredits = totalCredits;
    }

    public float calculate() {
        double avg = 0;
        for (Double elem : finalGrades) {
            avg += elem;
        }
        avg /= totalCredits;
        return (float) avg;
    }
}
