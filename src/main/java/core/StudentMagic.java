package core;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class StudentMagic {
    private String studentID;
    private Map<String, GMUClass> classes;

    public StudentMagic(String studentID, Map<String, GMUClass> classes) {
        this.studentID = studentID;
        this.classes = classes;
    }

    public GMUClass getClass(String classID) {
        return classes.get(classID);
    }

    @Override
    public String toString() {
        return studentID + " " + Arrays.toString(classes.values().toArray());
    }
}
