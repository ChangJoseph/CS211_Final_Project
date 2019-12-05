package integration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JavaSwingGUI{

    IntegrationBase brain;

    FlowLayout flowLayout = new FlowLayout();

    JFrame loginMenu = new JFrame("Login");
    JButton loginB = new JButton("Login");

    JFrame mainMenu = new JFrame("Menu");
    JButton viewGradeB = new JButton("View Grades");
    JButton addGradesB = new JButton("Add Grades");
    JButton clearGradesB = new JButton("Clear Grades");

    JFrame gradeView = new JFrame("GradeView");

    JFrame gradeInput = new JFrame("GradeInput");

    public JavaSwingGUI() {
        brain = new IntegrationBase();
        setupLoginMenu();
        setupMainMenu();
        setupGradeView();
        setupAddGrades();
        setupListeners();
        run();
    }

    public void setupLoginMenu() {
        loginMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginMenu.setSize(800,500);
        loginMenu.getContentPane().add(loginB);
        loginMenu.setVisible(true);
    }
    public void setupMainMenu() {
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenu.setSize(800,500);
        mainMenu.setLayout(flowLayout);
        Container mainMenuContainer = mainMenu.getContentPane();
        mainMenuContainer.add(viewGradeB);
        mainMenuContainer.add(addGradesB);
        mainMenuContainer.add(clearGradesB);
        mainMenu.setVisible(false);
    }
    public void setupGradeView() {
        gradeView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gradeView.setSize(800,500);
        gradeView.setVisible(false);
    }
    public void setupAddGrades() {
        gradeInput.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gradeInput.setSize(800,500);
        gradeInput.setVisible(false);
    }

    public void setupListeners() {
        loginB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Login Action here

                loginMenu.setVisible(false);
                mainMenu.setVisible(true);
            }
        });

        viewGradeB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainMenu.setVisible(false);
                gradeView.setVisible(true);
            }
        });
        addGradesB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainMenu.setVisible(false);
                gradeInput.setVisible(true);
            }
        });
        clearGradesB.addActionListener(new ActionListener() {
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