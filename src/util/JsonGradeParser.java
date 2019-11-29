package util;

import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class JsonGradeParser {
    private BasicJsonManager jsonManager;

    public JsonGradeParser(File file) {
        jsonManager = new BasicJsonManager(file);
    }

    public List<Double> parse() {
        List<Double> output = new ArrayList<Double>();
        Scanner scan = jsonManager.getFileScanner();
        while (scan.hasNextLine()) {
            String currLine = scan.nextLine();
        }
        // TODO implement parse code
        return output;
    }
}