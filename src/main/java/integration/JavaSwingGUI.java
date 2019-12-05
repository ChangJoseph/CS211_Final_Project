package integration;

import core.GMUClass;
import core.Grades;
import exception.InvalidIDException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class JavaSwingGUI{

    IntegrationBase brain;

    // LOGIN MENU FRAME
    JFrame loginMenu = new JFrame("Login");
    JPanel promptPanel = new JPanel();
    JPanel inputPanel = new JPanel();
    JButton loginB = new JButton("Login");
    JLabel userNamePrompt = new JLabel("Name");
    JLabel userIDPrompt = new JLabel("Mason ID");
    JLabel userDOBPrompt = new JLabel("Date of Birth (MMDDYYYY)   ");
    JTextField userNameInput = new JTextField("John Doe");
    JTextField userIDInput = new JTextField("JDoe1");
    JTextField userDOBInput = new JTextField("01012000");

    // MAIN MENU FRAME
    JFrame mainMenu = new JFrame("Menu");
    JPanel mainMenuPromptP = new JPanel();
    JLabel mainMenuLabel = new JLabel("Select an Option");
    JPanel mainMenuButtons = new JPanel();
    JButton viewGradeB = new JButton("View Grades");
    JButton addGradesB = new JButton("Add Grades/Classes");
    JButton clearGradesB = new JButton("Clear All Records");

    // GRADE VIEW FRAME
    JFrame gradeView = new JFrame("GradeView");
    JPanel gradeViewP = new JPanel();
    JPanel gradeViewScaledP = new JPanel();
    JLabel gradeViewScaledLabel = new JLabel();
    JPanel gradeViewSemesterGPAP = new JPanel();
    JLabel gradeViewSemesterGPALabel = new JLabel();
    JPanel gradeViewCumulativeP = new JPanel();
    JLabel gradeViewCumulativeLabel = new JLabel();
    JLabel gradesViewPrompt = new JLabel("        Here are your recorded grades. If you would like, " +
            "please select an alternative viewing option for your grades.");
    JLabel gradesViewLabel = new JLabel();
    JButton gradesViewClassTotal = new JButton("Scaled Grades");
    JButton gradesViewGPACalculator = new JButton("Estimated Semester GPA");
    JButton gradesViewCumulativeGPACalculator = new JButton("Estimated Cumulative GPA");

    // GRADE INPUT FRAME
    JFrame gradeInput = new JFrame("GradeInput");
    JPanel gradeInputP = new JPanel();
    JLabel gradeInputPrompt = new JLabel("Do you want to add a class or grades?");
    JPanel gradeInputButtons = new JPanel();
    JButton gradeInputClassButton = new JButton("Class Add/Reset");
    JButton gradeInputGradeButton = new JButton("Grade Add");
    //Class
    JPanel gradeInputClassP = new JPanel();
    JLabel gradeInputClassAddIDPrompt = new JLabel("Class ID (Ex: \"CS211\")");
    JLabel gradeInputClassAddCreditPrompt = new JLabel("# of Credits (Ex: \"3\")");
    JTextField gradeInputClassIDField = new JTextField(); // Class ID Field
    JTextField gradeInputClassCreditField = new JTextField(); // Credit Field
    JButton gradeInputClassAddButton = new JButton("Add Class!");
    //Grade
    JPanel gradeInputGradeP = new JPanel();
    JLabel gradeInputGradeClassIDPrompt = new JLabel("Class ID (Ex: \"CS211\")");
    JLabel gradeInputGradeScalePrompt = new JLabel("Grade Scale Percentage (Ex: \".15\" for 15%)");
    JLabel gradeInputGradeValuePrompt = new JLabel("Grade Value (Ex: \"95, 89, 49, 78\")");
    JTextField gradeInputGradeClassIDField = new JTextField("CS211"); // Class ID Field
    String gradeInputPreviousGradeClassIDField;
    JTextField gradeInputGradeScaleField = new JTextField(); // Grade Scale Field
    JTextField gradeInputGradeValueField = new JTextField(); // Grade Value Field
    JButton gradeInputGradeAddButton = new JButton("Add Grade!");

    // MARGIN PANEL
    JButton backToMainMenuView = new JButton("X");
    JPanel marginPanelView = new JPanel();
    JButton backToMainMenuInput = new JButton("X");
    JPanel marginPanelInput = new JPanel();

    public JavaSwingGUI() {
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
        loginMenu.setSize(600,175);
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
        mainMenu.setSize(600,175);
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

        //  X button
        marginPanelView.setLayout(new BorderLayout());
        marginPanelView.add(backToMainMenuView, "West");
        marginPanelView.add(gradesViewPrompt, "Center");
        gradeViewContainer.add(marginPanelView, "North");

        // First Opening view
        gradeViewP.setLayout(new FlowLayout());
        gradeViewP.add(gradesViewLabel);
        gradeViewContainer.add(gradeViewP, "Center");
        // Scaled View
        gradeViewScaledLabel.setText(this.calculateScaled());
        gradeViewScaledP.add(gradeViewScaledLabel);
        // Semester GPA View
        gradeViewSemesterGPALabel.setText();
        gradeViewSemesterGPAP.add(gradeViewSemesterGPALabel);
        // Cumulative GPA View
        gradeViewCumulativeLabel.setText();
        gradeViewCumulativeP.add(gradeViewCumulativeLabel);

        gradeView.setVisible(false);
    }
    public String calculateScaledFormatted() {
        brain.scaledGradeAll();
    }
    public String calculateSemesterFormatted() {

    }
    public String calculateCumulativeFormatted() {

    }
    public void setupAddGrades() {
        gradeInput.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gradeInput.setSize(600,175);
        Container gradeInputContainer = gradeInput.getContentPane();

        // Margin
        marginPanelInput.setLayout(new BorderLayout());
        marginPanelInput.add(backToMainMenuInput, "West");
        gradeInputP.setLayout(new FlowLayout());
        gradeInputP.add(gradeInputPrompt);
        marginPanelInput.add(gradeInputP, "Center");
        gradeInputContainer.add(marginPanelInput, "North");
        // Buttons
        gradeInputButtons.setLayout(new FlowLayout());
        gradeInputButtons.add(gradeInputClassButton);
        gradeInputButtons.add(gradeInputGradeButton);
        gradeInputContainer.add(gradeInputButtons);
        // Class Add
        gradeInputClassP.setLayout(new GridLayout(2,2));
        gradeInputClassP.add(gradeInputClassAddIDPrompt);
        gradeInputClassP.add(gradeInputClassIDField);
        gradeInputClassP.add(gradeInputClassAddCreditPrompt);
        gradeInputClassP.add(gradeInputClassCreditField);
        //Grade Add
        gradeInputGradeP.setLayout(new GridLayout(3,2));
        gradeInputGradeP.add(gradeInputGradeClassIDPrompt); // "Class ID (Ex: \"CS211\""
        gradeInputGradeP.add(gradeInputGradeClassIDField);
        gradeInputGradeP.add(gradeInputGradeScalePrompt); // "Grade Scale Percentage (Ex: \".15\" for 15%)"
        gradeInputGradeP.add(gradeInputGradeScaleField);
        gradeInputGradeP.add(gradeInputGradeValuePrompt); // "Grade Value (Ex: \"95\" for 95.0)"
        gradeInputGradeP.add(gradeInputGradeValueField);

//        gradeInputContainer.add(gradeInputClassP,"West");
//        gradeInputContainer.add(gradeInputGradeP,"East");

        gradeInput.setVisible(false);
    }
    public void clearGradeViewFrame() {
        // Main Grade View Panel
        gradeViewP.setVisible(false); // Unformatted grades
        gradeViewScaledP.setVisible(false);
        gradeViewSemesterGPAP.setVisible(false);
        gradeViewCumulativeP.setVisible(false);
    }
    public void clearGradeInputFrame() {
        // Main Grade Input Panel
        gradeInputPrompt.setVisible(false);
        gradeInputButtons.setVisible(false);
//        gradeInputClassButton.setVisible(false);
//        gradeInputGradeButton.setVisible(false);
        // Panels
        gradeInputClassP.setVisible(false);
        gradeInputGradeP.setVisible(false);
        // Buttons
        gradeInputClassAddButton.setVisible(false);
        gradeInputGradeAddButton.setVisible(false);
    }

    public void setupListeners() {
        backToMainMenuView.addActionListener(new ActionListener() { // On "X" click
            public void actionPerformed(ActionEvent e) {
                gradeView.setVisible(false);
                mainMenu.setVisible(true);
            }
        });
        backToMainMenuInput.addActionListener(new ActionListener() { // On "X" click
            public void actionPerformed(ActionEvent e) {
                gradeInput.setVisible(false);
                mainMenu.setVisible(true);
            }
        });

        // Login -> MAIN MENU FRAME
        loginB.addActionListener(new ActionListener() { // On "Login" click
            public void actionPerformed(ActionEvent e) {
                // Login Action here
                String name = userNameInput.getText().toLowerCase().replaceAll("[^a-z]","");
                String id = userIDInput.getText();
                String dob = userDOBInput.getText().replaceAll("[^0-9]","");
                if (Pattern.matches("[A-Za-z]{2,}[0-9]*", id)) { // Pattern: 2 or more letters then 0 or more numbers
                    id = id.toLowerCase().replaceAll("[^a-z0-9]","");
                }
                else {
                    throw new InvalidIDException("Invalid ID Input");
                }
                brain = new IntegrationBase(name, id, dob);

                // TODO Make a check that makes sure the id matches the hash, otherwise dob is wrong

                mainMenuLabel.setText("Hello " + userNameInput.getText() + ". Please select an option.");

                loginMenu.setVisible(false);
                mainMenu.setVisible(true);
            }
        });
        // **** VIEW GRADE FRAME ****
        viewGradeB.addActionListener(new ActionListener() { // On "View Grades" click
            public void actionPerformed(ActionEvent e) {
                gradesViewLabel.setText("<html>" + brain.readAllGrades().replaceAll("\n","<br/>") + "</html>");
                mainMenu.setVisible(false);
                clearGradeViewFrame();
                gradeView.setVisible(true);
            }
        });
        gradesViewClassTotal.addActionListener(new ActionListener() { //On "Scaled Grades" click
            public void actionPerformed(ActionEvent e) {
                clearGradeViewFrame();
                gradeViewScaledP.setVisible(true);

            }
        });
        gradesViewGPACalculator.addActionListener(new ActionListener() { //On "Estimated Semester GPA" click
            public void actionPerformed(ActionEvent e) {
                clearGradeViewFrame();
                gradeViewSemesterGPAP.setVisible(true);

            }
        });
        gradesViewCumulativeGPACalculator.addActionListener(new ActionListener() { //On "Estimated Cumulative GPA" click
            public void actionPerformed(ActionEvent e) {
                clearGradeViewFrame();
                gradeViewCumulativeP.setVisible(true);

            }
        });
        // **** ADD GRADE FRAME ****
        addGradesB.addActionListener(new ActionListener() { // On "Add Grades" click
            public void actionPerformed(ActionEvent e) {
                mainMenu.setVisible(false);
                clearGradeInputFrame();
                gradeInput.setVisible(true);
                gradeInputPrompt.setVisible(true);
                gradeInputButtons.setVisible(true);
            }
        });
        clearGradesB.addActionListener(new ActionListener() { // On "Clear Grades" click
            public void actionPerformed(ActionEvent e) {
                brain.clearAllGrades();
                JOptionPane.showMessageDialog(mainMenu,"Grades/Classes Have been cleared!");
            }
        });

        // This is all in "ADD GRADE/CLASS FRAME"
        // CLASS
        gradeInputClassButton.addActionListener(new ActionListener() { // On "Class Add" click
            public void actionPerformed(ActionEvent e) {
                gradeInput.add(gradeInputClassP, "Center");
                gradeInput.add(gradeInputClassAddButton, "South");

                clearGradeInputFrame();
                gradeInputClassAddButton.setVisible(true);
                gradeInputClassP.setVisible(true);
            }
        });
        // GRADE
        gradeInputGradeButton.addActionListener(new ActionListener() { // On "Grade Add" click
            public void actionPerformed(ActionEvent e) {
                gradeInput.add(gradeInputGradeP, "Center");
                gradeInput.add(gradeInputGradeAddButton, "South");
                clearGradeInputFrame();
//                gradeInputGradeClassIDField.setText(gradeInputPreviousGradeClassIDField);
                gradeInputGradeAddButton.setVisible(true);
                gradeInputGradeP.setVisible(true);
            }
        });
        // CLASS BUTTON ACTION
        gradeInputClassAddButton.addActionListener(new ActionListener() { // On "Add Class" click
            public void actionPerformed(ActionEvent e) {
                String classID = gradeInputClassIDField.getText();
                int classCredit = Integer.parseInt(gradeInputClassCreditField.getText().replaceAll("[^0-9]",""));
                GMUClass gmuClass = new GMUClass(classCredit);
                if (!brain.addClass(classID,gmuClass)) {
                    JOptionPane.showMessageDialog(mainMenu,"Error Adding Class...");
                }
                else {
                    JOptionPane.showMessageDialog(mainMenu, "Class has been added!");
                }
                brain.writeToJson();
            }
        });
        // GRADE BUTTON ACTION
        gradeInputGradeAddButton.addActionListener(new ActionListener() { // On "Add Grade" click
            public void actionPerformed(ActionEvent e) {
                gradeInputPreviousGradeClassIDField = gradeInputGradeClassIDField.getText();
                // TODO Make check that makes sure gradeScale has leading 0 and at least 1 decimal ex: 0.1
                double gradeScale = Double.parseDouble(gradeInputGradeScaleField.getText().replaceAll(
                        "[^0-9\\.]",""));
                // TODO make a check that makes sure total scale is below 1
//                if (gradeScale + brain.totalScales() > 1) {

                // Removes unnecessary characters
                String gradesFormatted = gradeInputGradeValueField.getText().replaceAll("[^0-9,]","");
                String[] gradesList = gradesFormatted.split(","); // This turns input field into an int safely
                ArrayList<Integer> gradeValues = new ArrayList<Integer>();
                for (String elem : gradesList) {
                    gradeValues.add(Integer.parseInt(elem));
                }
                if (!brain.addGrades(gradeInputPreviousGradeClassIDField,new Grades(gradeScale, gradeValues))) {
                    JOptionPane.showMessageDialog(mainMenu,"Add the class first please!");
                }
                else {
                    JOptionPane.showMessageDialog(mainMenu, "Grades have been added!");
                }
                brain.writeToJson();
            }
        });
    }

    /**
     * This code is constantly running while the UI is up and running
     */
    public void run() {
//        while(true) {
//            // This is constantly running
//        }
    }

    /**
     * Run this to see the whole project in action!
     * @param args no arguments necessary
     */
    public static void main(String args[]){
        new JavaSwingGUI();
    }
}