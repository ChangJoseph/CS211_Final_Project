package integration;

import java.util.Scanner;

public class IOManager {
    private Scanner userInput;

    public IOManager() {
        this.userInput = Scanner(System.in);
    }

    public Student start() {
        String nameHolder;
        String idHolder;
        String dobHolder;

        try {
            System.out.println("What is your Full Name?: ");
            nameHolder = userInput.nextLine;
            System.out.println("What is you ID?: ");
            infoHolder = userInput.nextLine();
            System.out.println("What is your D.O.B?: ");
            dobHolder = userInput.nextLine();
            return new Student(nameHolder, idHolder, dobHolder);
        }
        catch (IOException ioe) {
            System.out.println("IO Exception raised!");
        }
        return new Student();
    }

    public void close() {
        br.close();
    }

    public static void main(String[] args) {
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String  = br.readline();
            System.out.println(name);
        }
        catch (IOException ioe) {
            System.out.println("IO Exception raised!");
        }
    }
}