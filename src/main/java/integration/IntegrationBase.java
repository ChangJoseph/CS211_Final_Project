package integration;

import core.Grades;
import core.Student;
import core.StudentMagic;
import util.JsonGradeParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the brain of the integration package and processes requests based on whichever UI is used
 */
public class IntegrationBase {
    private Student student;
    private int hash;
    private StudentMagic magic;

    public IntegrationBase(String name, String id, String dob) {
        this.student = new Student(name, id, dob);
        this.hash = student.hashCode();
    }

    /**
     * gets a list of grades of instance's student
     * @return read above
     */
    public List<Grades> readGrades() {
        return null;
    }

    /**
     * Adds grades & scale to the instance's student's file
     * Appends list of grades if scale already exists in student's file
     * @param classID the classID (ex: "MATH125") wanted to add grades to
     * @param grades grades wanted to add to the class of specified classID
     */
    public void addGrades(String classID, Grades grades) {
        magic.getClass(classID).addGrades(grades);
    }

    public Student getStudent() {
        return this.student;
    }
    public int getHash() {
        return this.hash;
    }
    public File getFile() {
        return new File(System.getProperty("user.dir") + "/database/" + this.hash + ".json/");
    }

    /**
     * Makes sure file exists then returns the parsed json file of the current instance's class
     * @return toString representation of the class
     */
    @Override
    public String toString() {
        if (!getFile().exists()) {
            return "N/A";
        }
        JsonGradeParser parse = new JsonGradeParser(getFile());
        return parse.parse().toString();
    }

    public static void main(String[] args) {
        IntegrationBase base = new IntegrationBase("John","jdoe","01/01/2000");
        List<Integer> grades = new ArrayList<Integer>();
        grades.add(95);
        grades.add(89);
        grades.add(49);
        Grades gradeClass = new Grades(1.0, grades);
        base.addGrades(gradeClass);
        System.out.println(base.toString());
    }
}