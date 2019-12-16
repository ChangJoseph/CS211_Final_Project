package core;

import java.util.ArrayList;
import java.util.List;

/**
 * GMUClass is just a class that holds the credit a class is worth and its grades
 * the actual classID is not included because when in use with GSON, the json format becomes really ugly
 * as a fix to it, I made a class called StudentMagic that has a map that contains a class name as key and this as value
 */
public class GMUClass {
//    private String classID; // Ex: MATH125
    private int credit;
    private List<Grades> gradesList;

    public GMUClass(int credit) {
        this(credit, new ArrayList<Grades>());
    }
    public GMUClass(int credit, List<Grades> gradesList) {
//        this.classID = classID.toLowerCase();
        this.credit = credit;
        this.gradesList = gradesList;
    }

//    public String getClassID() {
//        return classID;
//    }
    public int getCredit() {
        return credit;
    }
    public List<Grades> getGradesList() {
        return gradesList;
    }
    public void addGrades(Grades newGrades) {
        for (Grades x : gradesList) {
            if (x.getScale() == newGrades.getScale()) { // Checks if the same scale in newGrades exists in any of gradesList
                x.addGrades(newGrades.getGrades());
                return;
            }
        }
        // Otherwise, add it to the gradesList
        gradesList.add(newGrades);
    }
    public void removeGrades(double scale) {

    }
    public boolean removeGrade(double scale, double grade) {
        for (Grades grades : gradesList ) {
            if (grades.getScale() == scale) {
                grades.removeGrade(grade);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns sum of all scales in gradesList
     * @return read above
     */
    public double getTotalScales() {
        double sum = 0.0;
        for (Grades x : gradesList) {
            sum += x.getScale();
        }
        return sum;
    }

}
