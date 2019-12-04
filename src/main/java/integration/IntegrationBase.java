package integration;

import core.GMUClass;
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
    private StudentMagic magic;

    public IntegrationBase(String name, String id, String dob) {
        this.student = new Student(name, id, dob);
        magic = new StudentMagic(id);
    }

    /**
     * gets a list of grades of instance's student
     * @return read above
     */
    public List<Grades> readGrades() {
        return null;
    }

    /**
     * adds a class to StudentMagic's list of classes
     * @param gmuClass gmu class itself to add
     */
    public void addClass(GMUClass gmuClass) {
        magic.addClass(gmuClass);
    }
    /**
     * Adds grades & scale to the instance's student's file
     * Appends list of grades if scale already exists in student's file
     * @param classID the classID (ex: "MATH125") wanted to add grades to
     * @param grades grades wanted to add to the class of specified classID
     * @return returns true if successfully added; returns false if the classID does not exist in StudentMagic
     */
    public boolean addGrades(String classID, Grades grades) {
        if (!magic.classExists(classID)) {
            return false;
        }
        magic.getClass(classID).addGrades(grades);
        return true;
    }

    public Student getStudent() {
        return this.student;
    }

    /**
     * Gets the file associated with current instance;
     * @return project_folder/database/student_hash.json
     */
    public File getFile() {
        return new File(System.getProperty("user.dir") + "/database/" + student.hashCode() + ".json/");
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
        grades.add(49);
        Grades gradeClass = new Grades(1.0, grades);
        GMUClass gmuClass = new GMUClass("MATH125",4);
        base.addClass(gmuClass);
        base.addGrades("MATH125",gradeClass);
        System.out.println(base.toString());
    }
}