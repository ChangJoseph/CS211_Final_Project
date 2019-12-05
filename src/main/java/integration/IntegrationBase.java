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
 * Steps for UI classes, Make instance of this class, use addClass, use addGrades, use writeToJson
 * toString will output a String representation of the json file made
 */
public class IntegrationBase {
    private Student student;
    private StudentMagic magic;
    private JsonGradeParser parse;
    private boolean fullySetup;

    /**
     * This method is the first call if the default constructor is used
     * Other methods cannot be used if this hasn't been called yet
     * @param name name of student
     * @param id gmu id of student
     * @param dob date of birth of student
     */
    public void setupInformation(String name, String id, String dob) {
        if (fullySetup) { // Only allows first time call
            return;
        }
        this.student = new Student(name, id, dob);
        fullySetup = true;
        parse = new JsonGradeParser(getFile());
        magic = parse.parse();
    }

    /**
     * Default constructor that is used only when information is not fully known
     */
    public IntegrationBase() {
        fullySetup = false;
    }
    public IntegrationBase(String name, String id, String dob) {
        this.student = new Student(name, id, dob);
        fullySetup = true;
        parse = new JsonGradeParser(getFile());
        magic = parse.parse();
    }

    /**
     * gets a list of grades of instance's student
     * @return read above
     */
    public List<Grades> readGrades() {
        if (!fullySetup) { return null; }
        return null;
    }

    /**
     * adds a class to StudentMagic's list of classes
     * @param gmuClass gmu class itself to add
     */
    public void addClass(String classID, GMUClass gmuClass) {
        if (!fullySetup) { return; }
        magic.addClass(classID, gmuClass);
    }
    /**
     * Adds grades & scale to the instance's student's file
     * Appends list of grades if scale already exists in student's file
     * @param classID the classID (ex: "MATH125") wanted to add grades to
     * @param grades grades wanted to add to the class of specified classID
     * @return returns true if successfully added; returns false if the classID does not exist in StudentMagic
     */
    public boolean addGrades(String classID, Grades grades) {
        if (!fullySetup) { return false; }
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
        if (!fullySetup) { return null; }
        return new File(System.getProperty("user.dir") + "/database/" + student.hashCode() + ".json/");
    }

    /**
     * Opposite of toString; writes current data to corresponding json file
     */
    public void writeToJson() {
        if (!fullySetup) { return; }
        parse.overwrite(magic);
    }

    /**
     * Makes sure file exists then returns the parsed json file of the current instance's class
     * @return toString representation of the class
     */
    @Override
    public String toString() {
        if (!fullySetup) { return ""; }
        if (!getFile().exists()) {
            return "N/A";
        }
        String output = parse.parse().toString();
        if (output != null || !output.isEmpty()) {
            return output;
        }
        return "";
    }

    /*
    public static void main(String[] args) {
        IntegrationBase base = new IntegrationBase("John","jdoe","01/01/2000");
        List<Integer> grades = new ArrayList<Integer>();
        grades.add(95);
        grades.add(49);
        Grades gradeClass = new Grades(1.0, grades);
        GMUClass gmuClass = new GMUClass(4);
        base.addClass("MATH125",gmuClass);
        base.addGrades("MATH125",gradeClass);
        base.writeToJson();
        System.out.println(base.toString());
    }

     */
}