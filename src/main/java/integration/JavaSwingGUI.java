package integration;

import core.CumulativeGPACalculator;
import core.GMUClass;
import core.Grades;
import core.SemesterGPACalculator;
import exception.InvalidIDException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
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
    JTextField userIDInput = new JTextField("JDoe");
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
    JPanel gradeViewP = new JPanel(); // Main menu
    JPanel gradeViewButtonsP = new JPanel(); // Button Panel
    JPanel gradeViewScaledP = new JPanel(); // Scaled Grades
    JLabel gradeViewScaledLabel = new JLabel();
    JPanel gradeViewSemesterGPAP = new JPanel(); // Semester GPA
    JLabel gradeViewSemesterGPALabel = new JLabel();
    JPanel gradeViewCumulativeP = new JPanel(); // Cumulative GPA
    JLabel gradeViewCumulativeLabel = new JLabel();
    JLabel gradesViewPrompt = new JLabel("        Here are your recorded grades. If you would like, " +
            "please select an alternative viewing option for your grades.");
    JLabel gradesViewLabel = new JLabel();
    JButton gradesViewMainB = new JButton("General Grades");
    JButton gradesViewClassTotalB = new JButton("Scaled Grades");
    JButton gradesViewGPACalculatorB = new JButton("Estimated Semester GPA");
    JButton gradesViewCumulativeGPACalculatorB = new JButton("Estimated Cumulative GPA");

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
    JLabel gradeInputGradeScalePrompt = new JLabel("Grade Scale Percentage (Ex: \"0.15\" for 15%)");
    JLabel gradeInputGradeValuePrompt = new JLabel("Grade Value (Ex: \"95/110, 0.86\")");
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
        gradeViewP.setLayout(new BorderLayout());
        gradeViewP.add(gradesViewLabel, "Center");
        gradeViewContainer.add(gradeViewP, "Center");
        gradeViewButtonsP.setLayout(new FlowLayout());
        gradeViewButtonsP.add(gradesViewMainB); // Button
        gradeViewButtonsP.add(gradesViewClassTotalB); // Button
        gradeViewButtonsP.add(gradesViewGPACalculatorB); // Button
        gradeViewButtonsP.add(gradesViewCumulativeGPACalculatorB); // Button
        gradeViewP.add(gradeViewButtonsP, "South");

        // Scaled View
        gradeViewScaledP.setLayout(new BorderLayout());
        gradeViewScaledP.add(gradeViewScaledLabel, "Center");

        // Semester GPA View
        gradeViewSemesterGPAP.setLayout(new BorderLayout());
        gradeViewSemesterGPAP.add(gradeViewSemesterGPALabel, "Center");

        // Cumulative GPA View
        gradeViewCumulativeP.setLayout(new BorderLayout());
        gradeViewCumulativeP.add(gradeViewCumulativeLabel, "Center");

        gradeView.setVisible(false);
    }
    public String calculateScaledFormatted() {
        Map<String, Double> map = brain.scaledGradeAll();
        StringBuilder output = new StringBuilder();
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            output.append(entry.getKey());
            output.append(": ");
            output.append(entry.getValue()*100);
            output.append("%\n");
        }
        return "<html>"+output.toString().replaceAll("\n","<br/>")+"</html>";
    }
    public float calculateSemesterGPA() {
        Map<String, Double> map = brain.scaledWithGPAAll();
        SemesterGPACalculator calc = new SemesterGPACalculator(map.values(), brain.getTotalCredits());
        return calc.calculate();
    }
    public String calculateCumulativeFormatted(double prevGPA, int prevCredits) {
        float semesterGPA = calculateSemesterGPA();
        CumulativeGPACalculator calc = new CumulativeGPACalculator(semesterGPA, brain.getTotalCredits(), prevGPA, prevCredits);
        return "Estimated Cumulative GPA: " + calc.calculate();
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
                gradeView.getContentPane().add(gradeViewP, "Center");
                gradeViewP.add(gradeViewButtonsP, "South");
                gradeViewP.setVisible(true);
                gradeView.setVisible(true);
            }
        });
        gradesViewMainB.addActionListener(new ActionListener() { //On "General Grades" click
            public void actionPerformed(ActionEvent e) {
                clearGradeViewFrame();
                gradeView.getContentPane().add(gradeViewP, "Center");
                gradeViewScaledLabel.setText(calculateScaledFormatted());
                gradeViewP.add(gradeViewButtonsP, "South");
                gradeViewP.setVisible(true);

            }
        });
        gradesViewClassTotalB.addActionListener(new ActionListener() { //On "Scaled Grades" click
            public void actionPerformed(ActionEvent e) {
                clearGradeViewFrame();
                gradeView.getContentPane().add(gradeViewScaledP, "Center");
                gradeViewScaledLabel.setText(calculateScaledFormatted());
                gradeViewScaledP.add(gradeViewButtonsP, "South");
                gradeViewScaledP.setVisible(true);

            }
        });
        gradesViewGPACalculatorB.addActionListener(new ActionListener() { //On "Estimated Semester GPA" click
            public void actionPerformed(ActionEvent e) {
                clearGradeViewFrame();
                gradeView.getContentPane().add(gradeViewSemesterGPAP, "Center");
                gradeViewSemesterGPALabel.setText("Calculated Semester GPA: " + calculateSemesterGPA());
                gradeViewSemesterGPAP.add(gradeViewButtonsP, "South");
                gradeViewSemesterGPAP.setVisible(true);

            }
        });
        gradesViewCumulativeGPACalculatorB.addActionListener(new ActionListener() { //On "Estimated Cumulative GPA" click
            public void actionPerformed(ActionEvent e) {
                clearGradeViewFrame();
                gradeView.getContentPane().add(gradeViewCumulativeP, "Center");
                boolean validInput = false;
                String prevCumulativeGPA = "";
                String prevCumulativeCredit = "";
                while (!validInput) { // TODO Cleanup interface for user friendliness
                    prevCumulativeGPA = JOptionPane.showInputDialog("What is your previous cumulative GPA?");
                    prevCumulativeCredit = JOptionPane.showInputDialog("What is your previous credit attempts?");
                    try {
                        if (Pattern.matches("[0-9]+(\\.[0-9]+)?", prevCumulativeGPA) &&
                                Pattern.matches("[0-9]+", prevCumulativeCredit)) {
                            validInput = true;
                        } else {
                            JOptionPane.showMessageDialog(mainMenu, "Invalid Input!");
                        }
                    }
                    catch (NullPointerException exception) {
                        clearGradeViewFrame();
                        gradeView.getContentPane().add(gradeViewP, "Center");
                        gradeViewScaledLabel.setText(calculateScaledFormatted());
                        gradeViewP.add(gradeViewButtonsP, "South");
                        gradeViewP.setVisible(true);
                        return;
                    }
                }
                double gpa = Double.parseDouble(prevCumulativeGPA);
                int cred = Integer.parseInt(prevCumulativeCredit);
                gradeViewCumulativeLabel.setText(calculateCumulativeFormatted(gpa, cred));
                gradeViewCumulativeP.add(gradeViewButtonsP, "South");
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
        // CLASS FRAME
        gradeInputClassButton.addActionListener(new ActionListener() { // On "Class Add" click
            public void actionPerformed(ActionEvent e) {
                gradeInput.add(gradeInputClassP, "Center");
                gradeInput.add(gradeInputClassAddButton, "South");

                clearGradeInputFrame();
                gradeInputClassAddButton.setVisible(true);
                gradeInputClassP.setVisible(true);
            }
        });
        // GRADE FRAME
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
                String gradesFormatted = gradeInputGradeValueField.getText().replaceAll("[^0-9,/\\.]","");
                String[] gradesList = gradesFormatted.split(",");
                ArrayList<Double> gradeValues = new ArrayList<Double>();
                for (String elem : gradesList) {
                    if (!Pattern.matches("[0-9]+(/|\\.)[0-9]+",elem)) {
                        JOptionPane.showMessageDialog(mainMenu,"Invalid Input! Please use fraction or decimal!");
                        return;
                    }
                    else if (Pattern.matches("[0-9]+/[0-9]+", elem)) { // if it uses division rather than double
                        String[] fraction = elem.split("/");
                        if (fraction.length < 2) {
                            JOptionPane.showMessageDialog(mainMenu,"Invalid Fraction!");
                            return;
                        }
                        gradeValues.add((double)Integer.parseInt(fraction[0]) / (double)Integer.parseInt(fraction[1]));
                    }
                    else {
                        gradeValues.add(Double.parseDouble(elem));
                    }
                }

                if (!brain.addGrades(gradeInputPreviousGradeClassIDField,new Grades(gradeScale, gradeValues))) {
                    JOptionPane.showMessageDialog(mainMenu,"Add the class first please!");
                    return;
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