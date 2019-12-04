package integration;

import core.Grades;
import core.Student;
import core.StudentMagic;
import util.JsonGradeParser;

import java.io.File;
import java.util.List;

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
     * Adds grades to the instance's student's file
     */
    public void addGrades(Grades grades) {

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
        base.
        System.out.println(base.toString());
    }
}