package util;

import java.io.File;
import java.io.FileReader;

public class BasicJsonManager {

    private File jsonFile;

    /**
     * Default constructor used for logging and testing purposes
     */
    public BasicJsonManager() {
        this("./test.json");
    }
    /**
     * Main constructor that takes in and instantiates a file
     * @param file json file to be managed
     */
    public BasicJsonManager(File file) {
        // If the file extension is not .json
        if (!this.getExtensionString(file.getName()).equals("json")) {
            throw new Exception(); // TODO make an exception for wrong extension type
        } 
        this.jsonFile = file;
    }

    // TODO check for bugs
    /**
     * Gets a string format of content in the json file
     * @return content of the file
     */
    public String getFileContent() {
        FileReader reader;
        try {
            reader = new FileReader(jsonFile);
            StringBuilder output = new StringBuilder();
            int character = reader.read();
            for(; character != -1;character = reader.read()) {
                output.append((char)character);
            }
            return output.toString();
        }
        catch (OutOfMemoryError e) {
            System.err.println(e);
            // e.printStackTrace();
        }
        finally {
            reader.close();
        }
        
    }
    /**
     * Writes to the json file whether it be append or overwrite
     * @param content message to write to the file
     * @param append set true for append and false for overwrite
     */
    public void addFileContent(String content, boolean append) {
        FileWriter writer;
        try {
            writer = new FileWriter(file, append);
            writer.write(content);
        }
        catch (IOException e) {
            System.err.println(e);
            // e.printStackTrace();
        }
        finally {
            writer.close();
        }
    }

    public String fileName() {
        return file.getName();
    }
    public String filePath() {
        return file.getPath();
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