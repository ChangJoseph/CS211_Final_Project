package integration;

import exception.InvalidIDException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;

public class JavaSwingGUI{

    IntegrationBase brain;

    JFrame loginMenu = new JFrame("Login");
    JPanel promptPanel = new JPanel();
    JPanel inputPanel = new JPanel();
    JButton loginB = new JButton("Login");
    JLabel userNamePrompt = new JLabel("Name");
    JLabel userIDPrompt = new JLabel("Mason ID");
    JLabel userDOBPrompt = new JLabel("Date of Birth (MMDDYYYY)   ");
    JTextField userNameInput = new JTextField("John Doe");
    JPasswordField userIDInput = new JPasswordField("JDoe1");
    JPasswordField userDOBInput = new JPasswordField("01012000");

    JFrame mainMenu = new JFrame("Menu");
    JPanel mainMenuPromptP = new JPanel();
    JLabel mainMenuLabel = new JLabel("Select an Option");
    JPanel mainMenuButtons = new JPanel();
    JButton viewGradeB = new JButton("View Grades");
    JButton addGradesB = new JButton("Add Grades");
    JButton clearGradesB = new JButton("Clear Grades");

    JFrame gradeView = new JFrame("GradeView");

    JFrame gradeInput = new JFrame("GradeInput");

    JButton backToMainMenu = new JButton("X");
    JPanel marginPanel = new JPanel();

    public JavaSwingGUI() {
        marginPanel.setLayout(new BorderLayout());
        marginPanel.add(backToMainMenu, "West");

//        brain = new IntegrationBase();
        setupLoginMenu();
        setupMainMenu();
        setupGradeView();
        setupAddGrades();
        setupListeners();
        run();
    }

    public void setupLoginMenu() {
        loginMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginMenu.setSize(400,150);
        Container loginContainer = loginMenu.getContentPane();
        // Adding elements into loginMenu
        promptPanel.setLayout(new GridLayout(3,1));
        inputPanel.setLayout(new GridLayout(3,1));
        loginContainer.add(loginB);
        promptPanel.add(userNamePrompt);
        promptPanel.add(userIDPrompt);
        promptPanel.add(userDOBPrompt);
        inputPanel.add(userNameInput);
        inputPanel.add(userIDInput);
        inputPanel.add(userDOBInput);
        loginContainer.add(promptPanel,"West");
        loginContainer.add(inputPanel,"Center");
        loginContainer.add(loginB,"South");
        loginMenu.setVisible(true);
    }
    public void setupMainMenu() {
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenu.setSize(400,150);
        Container mainMenuContainer = mainMenu.getContentPane();
//        mainMenuPromptP.setLayout(new BorderLayout());
        mainMenuPromptP.add(mainMenuLabel);
        mainMenuContainer.add(mainMenuPromptP, "North");
        mainMenuButtons.setLayout(new FlowLayout());
        mainMenuButtons.add(viewGradeB);
        mainMenuButtons.add(addGradesB);
        mainMenuButtons.add(clearGradesB);
        mainMenuContainer.add(mainMenuButtons,"South");
        mainMenu.setVisible(false);
    }
    public void setupGradeView() {
        gradeView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gradeView.setSize(800,500);
        Container gradeViewContainer = gradeView.getContentPane();
        gradeViewContainer.add(marginPanel, "North");
        gradeView.setVisible(false);
    }
    public void setupAddGrades() {
        gradeInput.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gradeInput.setSize(800,500);
        Container gradeViewContainer = gradeView.getContentPane();
        gradeViewContainer.add(marginPanel, "North");
        gradeInput.setVisible(false);
    }

    public void setupListeners() {
        backToMainMenu.addActionListener(new ActionListener() { // On "X" click
            public void actionPerformed(ActionEvent e) {
                loginMenu.setVisible(false);
                gradeView.setVisible(false);
                gradeInput.setVisible(false);
                mainMenu.setVisible(true);
            }
        });

        loginB.addActionListener(new ActionListener() { // On "Login" click
            public void actionPerformed(ActionEvent e) {
                // Login Action here
                String name = userNameInput.getText().toLowerCase().replaceAll("[^a-z]","");
                String id = userIDInput.getText();
                String dob = userDOBInput.getText().replaceAll("[^0-9]",""); ;
                if (Pattern.matches("[A-Za-z]{2,}[0-9]*", id)) { // Pattern: 2 or more letters then 0 or more numbers
                    id = id.toLowerCase().replaceAll("[^a-z0-9]","");
                }
                else {
                    throw new InvalidIDException("Invalid ID Input");
                }
                brain = new IntegrationBase(name, id, dob);

                mainMenuLabel.setText("Hello " + userNameInput.getText() + ". Please select an option.");

                loginMenu.setVisible(false);
                mainMenu.setVisible(true);
            }
        });

        viewGradeB.addActionListener(new ActionListener() { // On "View Grades" click
            public void actionPerformed(ActionEvent e) {
                mainMenu.setVisible(false);
                gradeView.setVisible(true);
            }
        });
        addGradesB.addActionListener(new ActionListener() { // On "Add Grades" click
            public void actionPerformed(ActionEvent e) {
                mainMenu.setVisible(false);
                gradeInput.setVisible(true);
            }
        });
        clearGradesB.addActionListener(new ActionListener() { // On "Clear Grades" click
            public void actionPerformed(ActionEvent e) {
                // Add clearing action here
            }
        });
    }

    public void run() {
        while(true) {
            // This is constantly running
        }
    }

    public static void main(String args[]){
        new JavaSwingGUI();
    }
}