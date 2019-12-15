package core;

import java.util.List;

public class Grades {
    private double scale;
    private List<Double> gradesList;

    public Grades(double scale, List<Double> gradesList) {
        this.scale = scale;
        this.gradesList = gradesList;
    }

    public double getScale() {
        return this.scale;
    }
    public List<Double> getGrades() {
        return this.gradesList;
    }
    public void addGrade(Double value) {
        gradesList.add(value);
    }
    public void addGrades(List<Double> listOfGrades) {
        gradesList.addAll(listOfGrades);
    }
}
