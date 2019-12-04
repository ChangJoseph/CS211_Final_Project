package integration;

import core.Student;
//import exception.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * This class is not used
 */
public class IOUI {
    private Scanner userInput;

    public IOUI() {
        this.userInput = new Scanner(System.in);
    }

    public Student start() {
        String nameHolder;
        String idHolder;
        String dobHolder;

        try {
            System.out.println("What is your Full Name?: ");
            nameHolder = userInput.nextLine();
            System.out.println("What is you ID?: ");
            idHolder = userInput.nextLine();
            System.out.println("What is your D.O.B?: ");
            dobHolder = userInput.nextLine();
            return new Student(nameHolder, idHolder, dobHolder);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new Student();
    }

    public void close() {
        userInput.close();
    }

    public static void main(String[] args) {
        // TODO add main console stuff here
    }
}