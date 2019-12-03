package core;

import java.util.List;

public class StudentMagic {
    private String studentID;
    private List<GMUClass> classes;

    public StudentMagic(String studentID, List<GMUClass> classes) {
        this.studentID = studentID;
        this.classes = classes;
    }
}
