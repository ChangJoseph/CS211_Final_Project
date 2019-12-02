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

    /**
     * Parses the json file into 
     * @return
     */
    public List<List<Double>> parse() {
        List<List<Double>> output = new ArrayList<List<Double>>();
        Scanner scan = jsonManager.getFileScanner();

        while (scan.hasNextLine()) {
            String currLine = scan.nextLine();
            String manipulatedLine;
            if (currLine.contains("{")) {
                // manipulatedLine = currLine.substring(currLine.indexOf("{"));

                while (!currLine.contains("}")) { // Run through whole json file

                    if (currLine.contains("[")) {
                        currLine = currLine.substring(1);
                        while (!currLine.contains("]")) { // Run through each list in the object
                            StringBuilder listContents = new StringBuilder();
                            listContents.append("");
                            if (scan.hasNextLine()) currLine = scan.nextLine();
                            else break;
                        }
                    }
                    if (!scan.hasNextLine()) break;
                }
            }
            // else {
            //     manipulatedLine = currLine;
            // }
            // String[] lineTokens = manipulatedLine.split(", ");
            // if (lineTokens.length > 1) {
                
            // }
            
        }
        // TODO implement parse code
        return output;
    }
}