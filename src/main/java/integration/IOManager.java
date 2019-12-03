package integration;

import core.Student;
//import exception.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class IOManager {
    private Scanner userInput;

    public IOManager() {
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
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String name = br.readLine();
            System.out.println(name);
        }
        catch (IOException ioe) {
            System.out.println("IO Exception raised!");
        }
    }
}