package util;

import java.io.File;
// import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BasicJsonManager {

    private File jsonFile; // The file name should be the Student hashCode() output

    /**
     * Default constructor used for logging and testing purposes
     */
    public BasicJsonManager() {
        this(new File("./test.json"));
    }
    /**
     * Main constructor that takes in and instantiates a file
     * @param file json file to be managed
     */
    public BasicJsonManager(File file) {
        // If the file extension is not .json
        if (!this.getExtensionString(file.getName()).equals("json")) {
            throw new IllegalArgumentException(); // TODO make an exception for wrong extension type
        } 
        this.jsonFile = file;
    }

    // TODO check for bugs
    /**
     * Gets a string format of content in the json file
     * @return content of the file
     */
    public String getFileContent() {
        try {
            FileReader reader = new FileReader(jsonFile);
            try {
                StringBuilder output = new StringBuilder();
                int character = reader.read();
                for(; character != -1;character = reader.read()) {
                    output.append((char)character);
                }
                return output.toString();
            }
            finally {
                reader.close();
            }
        }
        catch (IOException e) {
            System.err.println("getFileContent() IOException");
            e.printStackTrace();
        }
        return "***Error***";
        
    }
    /**
     * Writes to the json file whether it be append or overwrite
     * @param content message to write to the file
     * @param append set true for append and false for overwrite
     */
    public void addFileContent(String content, boolean append) {
        try {
            FileWriter writer = new FileWriter(jsonFile, append);
            try {
                writer.write(content);
            }
            finally {
                writer.close();
            }
        }
        catch (IOException e) {
            System.err.println("addFileContent() IOException");
            e.printStackTrace();
        }
    }

    public String fileName() {
        return jsonFile.getName();
    }
    public String filePath() {
        return jsonFile.getPath();
    }


    /**
     * Helper method to get the extension of a fileName in String format
     * @param fileName the String format of the fileName
     * @return the extension name in String format
     */
    private String getExtensionString(String fileName) {
        String[] splitName = fileName.split("."); // Splits fileName by '.' and makes an array
        return splitName[splitName.length-1]; // Returns last element of split fileName
    }

}