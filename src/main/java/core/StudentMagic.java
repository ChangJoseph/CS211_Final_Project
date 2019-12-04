package core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class holding metadata (student ID) as well as classes that the student has
 * Mainly used for parsing/deparsing student into json or vice versa
 */
public class StudentMagic {
    private String studentID;
    private Map<String, GMUClass> classes;

    public StudentMagic(String studentID, Map<String, GMUClass> classes) {
        this.studentID = studentID.toLowerCase();
        this.classes = classes;
    }
    public StudentMagic(String studentID) {
        this(studentID, new HashMap<String, GMUClass>());
    }

    public boolean classExists(String classID) {
        return classes.containsKey(classID);
    }
    public GMUClass getClass(String classID) {
        return classes.get(classID);
    }

    /**
     * sets value gmuClass in key classID
     * @param gmuClass value value gmu class
     * @return returns the old (if any) values associated with key
     */
    public GMUClass addClass(String classID, GMUClass gmuClass) {
        return classes.put(classID,gmuClass);
    }
    public GMUClass removeClass(String classID) {
        return classes.remove(classID);
    }

    @Override
    public String toString() {
        return studentID + " " + Arrays.toString(classes.values().toArray());
    }
}
