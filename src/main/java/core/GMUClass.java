package core;

import java.util.ArrayList;
import java.util.List;

public class GMUClass {
    private String classID; // Ex: MATH125
    private int credit;
    private List<Grades> grades;

    public GMUClass(String classID, int credit) {
        this(classID, credit, new ArrayList<Grades>());
    }
    public GMUClass(String classID, int credit, List<Grades> grades) {
        this.classID = classID;
        this.credit = credit;
        this.grades = grades;
    }

    public String getClassID() {
        return classID;
    }
    public int getCredit() {
        return credit;
    }
    public List<Grades> getGradesList() {
        return grades;
    }
    public void addGrades() {

    }
    public void removeGrades(double scale) {

    }
}
