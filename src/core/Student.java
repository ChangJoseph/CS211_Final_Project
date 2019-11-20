package core;

import java.util.regex.Pattern;
import java.lang.IllegalArgumentException;

public class Student {
    private String fullName, id;
    private String dob; // Formatted MMDDYYYY

    /**
     * Default constructor for test purposes only
     */
    public Student() {
        this("John Smith", "JSmith1", "01/01/2019");
    }
    /**
     * Main constructor for setting fullName and date of birth
     * @param fullName fullName of student
     * @param dob date of birth of student
     */
    public Student(String fullName, String id, String dob) {
        this.fullName = fullName.toUpperCase().replaceAll("[^A-Z]",""); // Remove any non-letter
        this.dob = dob.replaceAll("[^0-9]",""); // Remove any non-number

        // Checks if ID is valid using regex
        if (Pattern.matches("[A-Za-z]{2,}[0-9]*", id)) { // Pattern: 2 or more letters then 0 or more numbers
            this.id = id.toUpperCase().replaceAll("[^A-Z]","");
        }
        else {
            throw new IllegalArgumentException("Invalid ID Input"); // TODO make an InvalidIDError
        }
    }

    public String getName() {
        return this.fullName;
    }
    public String getID() {
        return this.id;
    }
    public String getDOB() {
        return this.dob;
    }

    /**
     * Returns precise arbitrary hash of Student class based on Student id and date of birth
     */
    public int hashCode() {
        return ( id.hashCode() + dob.hashCode() ) << 2;
    }
    @Override
    public String toString() {
        return "" + this.hashCode();
    }
}