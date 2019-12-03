package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.GMUClass;
import core.Grades;
import core.StudentMagic;

import javax.swing.filechooser.FileSystemView;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class JsonGradeParser {
    private BasicJsonManager jsonManager;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public JsonGradeParser(File file) {
        jsonManager = new BasicJsonManager(file);
    }

    /**
     * Parses the json file into 
     * @return
     */
    public StudentMagic parse() {
        return gson.fromJson(jsonManager.getFileContent(), StudentMagic.class);
    }

    // TODO write this method lol
    public void overwrite(StudentMagic magic) {
        jsonManager.addFileContent(gson.toJson(magic), false);
    }

    /*
    public static void main(String[] args) {
        JsonGradeParser x = new JsonGradeParser(new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "/test.json"));
        List<GMUClass> classes = new ArrayList<GMUClass>();
        List<Integer> grades = new ArrayList<Integer>();
        grades.add(95);
        grades.add(87);
        Grades gradesClass = new Grades(.15,grades);
        classes.add(new GMUClass("Math","112",4,gradesClass));
        StudentMagic magic = new StudentMagic("JChang32", classes);
        x.overwrite(magic);
    }
    */
}