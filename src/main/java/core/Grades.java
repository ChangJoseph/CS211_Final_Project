package core;

import java.util.ArrayList;
import java.util.List;

/**
 * Simply a scale and the grades that go along with that scale
 */
public class Grades {
    private double scale;
    private List<Double> gradesList;

    public Grades(double scale, List<Double> gradesList) {
        this.scale = scale;
        this.gradesList = gradesList;
    }
    public Grades(double scale) {
        this.scale = scale;
        this.gradesList = new ArrayList<Double>();
    }

    public double getScale() {
        return this.scale;
    }
    public List<Double> getGrades() {
        return this.gradesList;
    }
    public boolean removeGrade(double grade) {
        return gradesList.remove(new Double(grade));
    }
    public Grades addGrade(Double value) {
        gradesList.add(value);
        return this;
    }
    public Grades addGrades(List<Double> listOfGrades) {
        gradesList.addAll(listOfGrades);
        return this;
    }
}
