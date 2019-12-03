package core;

import java.util.List;

public class Grades {
    private double scale;
    private List<Integer> grades;

    public Grades(double scale, List<Integer> grades) {
        this.scale = scale;
        this.grades = grades;
    }
}
