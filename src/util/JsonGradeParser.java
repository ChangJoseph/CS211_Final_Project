package util;

import java.util.List;
import java.io.File;
import java.util.ArrayList;

public class JsonGradeParser {
    private BasicJsonManager jsonManager;

    public JsonGradeParser(File file) {
        jsonManager = new BasicJsonManager(file);
    }

    public List<Double> parse() {
        List<Double> output = new ArrayList<Double>();
        // TODO implement parse code
        return output;
    }
}