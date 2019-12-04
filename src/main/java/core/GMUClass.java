package core;

import java.util.ArrayList;
import java.util.List;

public class GMUClass {
    private String classID; // Ex: MATH125
    private int credit;
    private List<Grades> gradesList;

    public GMUClass(String classID, int credit) {
        this(classID, credit, new ArrayList<Grades>());
    }
    public GMUClass(String classID, int credit, List<Grades> gradesList) {
        this.classID = classID.toLowerCase();
        this.credit = credit;
        this.gradesList = gradesList;
    }

    public String getClassID() {
        return classID;
    }
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

    /**
     * Returns sum of all scales in gradesList
     * @return read above
     */
    private double getTotalScales() {
        double sum = 0.0;
        for (Grades x : gradesList) {
            sum += x.getScale();
        }
        return sum;
    }

}
