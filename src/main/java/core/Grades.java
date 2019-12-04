package core;

import java.util.List;

public class Grades {
    private double scale;
    private List<Integer> gradesList;

    public Grades(double scale, List<Integer> gradesList) {
        this.scale = scale;
        this.gradesList = gradesList;
    }

    public double getScale() {
        return this.scale;
    }
    public List<Integer> getGrades() {
        return this.gradesList;
    }
    public void addGrade(Integer value) {
        gradesList.add(value);
    }
    public void addGrades(List<Integer> listOfGrades) {
        gradesList.addAll(listOfGrades);
    }
}
