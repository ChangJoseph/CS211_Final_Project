package integration.gui;

import core.CumulativeGPACalculator;
import core.GMUClass;
import core.Grades;
import core.SemesterGPACalculator;
import integration.IntegrationBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ViewGradeController {

    private IntegrationBase brain;
    private GradeViewState state;
    @FXML ComboBox<String> viewGradeClassesComboBox;
    @FXML ListView<Double> viewGradeListView;
    @FXML ListView<String> viewGradeMetadataListView;
    @FXML Label viewGradeLabelClassData;
//    @FXML ScrollBar viewGradeScrollBar;
    @FXML TextField scaleText;
    @FXML TextField gradeText;
    @FXML TextField targetGPA;

    public ViewGradeController(IntegrationBase brain) {
        this.brain = brain;
        this.state = GradeViewState.OVERVIEW;
    }

    /**
     * Initializes some needed files
     */
    @FXML
    public void initialize() {
        Map<String, GMUClass> classes = brain.getClassesMap();
        viewGradeClassesComboBox.getItems().addAll(classes.keySet());

        // I just added the below because the view seemed empty and confusing at first glance
        viewGradeMetadataListView.getItems().add("\tSelect a class");
        viewGradeMetadataListView.getItems().add("\tabove to");
        viewGradeMetadataListView.getItems().add("\tget started");
    }

    /**
     * Listens for change in the "Classes" combobox and runs code to update the grade view list
     * @param event
     */
    @FXML
    public void comboBoxChange(ActionEvent event) {
        switch (state) {
            case OVERVIEW:
                overview(event);
                break;
            case TARGET:
                targetGPA(event);
                break;
            case ESTIMATE:
                estimateGPA(event);
                break;
            case CUMULATIVE:
                cumulativeGPA(event);
                break;
        }
    }

    /**
     * The "Remove Class" button next to the combo box that removes the class from memory
     * @param event
     */
    @FXML
    public void removeClasses(ActionEvent event) {
        String classID = viewGradeClassesComboBox.getValue();
        if (classID.isEmpty()) { // Edge case
            return;
        }
        // Updates the combo box
        viewGradeClassesComboBox.getItems().clear();
        brain.removeClass(classID);
        Map<String, GMUClass> classes = brain.getClassesMap();
        viewGradeClassesComboBox.getItems().addAll(classes.keySet());

        // Updates list view
        comboBoxChange(event);

        // Output making sure user knows to save to database
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Removed Class");
        alert.setContentText("Class has been removed. To make permanent, press \"Write to Database\".");
        alert.showAndWait();
    }

    /**
     * The main view for view Grades panel
     * shows overview of grades per each class
     * @param event
     */
    @FXML
    public void overview(ActionEvent event) {
        event.consume();
        state = GradeViewState.OVERVIEW;
        if (viewGradeClassesComboBox.getValue() == null) { // Edge case
            return;
        }
        String classID = viewGradeClassesComboBox.getValue();

        // Label Information
        StringBuilder data = new StringBuilder();
        data.append("Class: ");
        data.append(classID);
        data.append(" \tCredit: ");
        data.append(brain.getClass(classID).getCredit());
        viewGradeLabelClassData.setText(data.toString());

        // Meta Data
        viewGradeMetadataListView.getItems().clear();
        for (Grades grades : brain.getClass(classID).getGradesList()) {
            StringBuilder meta = new StringBuilder();
            meta.append("Scale: ");
            meta.append(grades.getScale());
            viewGradeMetadataListView.getItems().add(meta.toString());

            // Add empty spaces for every grade in the scale so the metadata view will be cleaner
            for (int count = 0; count < grades.getGrades().size()-1; count++) {
                viewGradeMetadataListView.getItems().add("");
            }
        }
        // Grades List
        viewGradeListView.getItems().clear();
        for (Grades gradesClass : brain.getGrades(viewGradeClassesComboBox.getValue())) {
            for (double grade : gradesClass.getGrades()) {
                viewGradeListView.getItems().add(grade);
            }
        }
    }

    // These methods are empty because no core classes have been made
    // Different viewing styles of your grades as well as calculations

    /**
     * Given a target gpa, finds the grades you need in each class
     * @param event
     */
    @FXML
    public void targetGPA(ActionEvent event) {
        event.consume();
        state = GradeViewState.TARGET;
        if (viewGradeClassesComboBox.getValue() == null) { return; }
        String classID = viewGradeClassesComboBox.getValue();
        String targetGPAString = targetGPA.getText();
        if (targetGPAString.isEmpty()) {
            return;
        }

        // This is where I would have used a targetGPA calculator in core package but there is no code
//        brain.targetGPA();
    }

    /**
     * Estimates your semester gpa given your current grades
     * @param event
     */
    @FXML
    public void estimateGPA(ActionEvent event) {
        event.consume();
        state = GradeViewState.OVERVIEW; // Since this is one time calculation, return to overview
        if (viewGradeClassesComboBox.getValue() == null) { return; }
        String classID = viewGradeClassesComboBox.getValue();

        double sum = 0.0;
        for (Grades grades : brain.getClass(classID).getGradesList()) {
            sum += brain.scaledGrade(grades);
        }
        sum *= 4;
        if (sum > 4) {
            sum = 4.0;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Estimate GPA");
        alert.setContentText("Estimated GPA: " + Math.round(sum *1000.0) / 1000.0);
        alert.showAndWait();
    }

    /**
     * Estimates cumulative gpa given previous grades
     * @param event
     */
    @FXML
    public void cumulativeGPA(ActionEvent event) {
        event.consume();
        state = GradeViewState.CUMULATIVE;
        if (viewGradeClassesComboBox.getValue() == null) { return; }
        String classID = viewGradeClassesComboBox.getValue();

        // User inputs here
        String prevCumulativeGPA = JOptionPane.showInputDialog("What is your previous cumulative GPA?");;
        String prevCumulativeCredit = JOptionPane.showInputDialog("What is your previous cumulative Credits?");

        if (prevCumulativeCredit.isEmpty() || prevCumulativeGPA.isEmpty()) { // Edge case
            return;
        }

        try {
            // Here, I gather up everything then send it to SemesterGPACalculator which gives me a result
            double gpa = Double.parseDouble(prevCumulativeGPA);
            int cred = Integer.parseInt(prevCumulativeCredit);
            Map<String, Double> map = brain.scaledWithGPAAll();
            SemesterGPACalculator calc = new SemesterGPACalculator(map.values(), brain.getTotalCredits());
            float semesterGPA = calc.calculate();
            CumulativeGPACalculator cumulativeCalc = new CumulativeGPACalculator(semesterGPA, brain.getTotalCredits(), gpa, cred);

            // Output the resulted cumulative gpa
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cumulative GPA");
            alert.setContentText("Estimated Cumulative GPA: "+Math.round(cumulativeCalc.calculate() *1000.0) / 1000.0);
            alert.showAndWait();
        }
        catch (NumberFormatException e) { // Parse error means user input is incorrect
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Invalid input");
            alert.showAndWait();
            return;
        }


    }

    // These are the bottom two add and delete buttons

    /**
     * The add grade button in the flowpane
     * @param event
     */
    @FXML
    public void addGradeFlow(ActionEvent event) {
        event.consume();

        // Here we record user input from the text fields next to the button
        String scale = scaleText.getText();
        String grade = gradeText.getText();
        if (scale == null || grade == null) { // Edge case
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Please type in scale and grade first");
            alert.showAndWait();
            return;
        }
        try {
            // parse the recorded scale and grade
            Double scaleD = Double.parseDouble(scale);
            Double gradeD = Double.parseDouble(grade);

            if (scaleD <= 0 || gradeD < 0) { // Edge Case
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setContentText("Please type in proper scale and grade");
                alert.showAndWait();
                return;
            }
            else if (scaleD > 1 || gradeD > 1) { // 1.0 scale Edge Case
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setContentText("Please type in scale/grade in 1.0 scale");
                alert.showAndWait();
                return;
            }
            else if (!brain.safeToAddScale(viewGradeClassesComboBox.getValue(), scaleD)) { // If sum of scaled in class exceed 1.0
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setContentText("Scale exceeds 1.0 in class");
                alert.showAndWait();
                return;
            }

            String gmuClass = viewGradeClassesComboBox.getValue();
            brain.addGrades(gmuClass, new Grades(scaleD).addGrade(gradeD)); // Add the actual grade into the database
        }
        catch (NumberFormatException e) { // Parsing error means user input incorrectly
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Please type scale/grade in double format (ex: 0.5)");
            alert.showAndWait();
            return;
        }
        comboBoxChange(event); // Update grade view
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success!");
        alert.setContentText("Grade has been added");
        alert.showAndWait();
    }

    /**
     * The remove grade button in the flowpane
     * @param event
     */
    @FXML
    public void removeGradeFlow(ActionEvent event) {
        event.consume();
        String gmuClass = viewGradeClassesComboBox.getValue(); // Gets the currently selected class

        // This seems inefficient, but it is the only way to get selected items in the list
        // And I limited the selection to only one item so this should be O(1)
        for (Object o : viewGradeListView.getSelectionModel().getSelectedIndices()) {
            for (Object p : viewGradeMetadataListView.getSelectionModel().getSelectedIndices()) {
                try {
                    brain.clearGrade(gmuClass, Double.parseDouble(p.toString().substring(8)), Double.parseDouble(o.toString()));
                }
                catch (NumberFormatException e) { // Makes sure there are no parsing errors
                    return;
                }
                catch (IndexOutOfBoundsException e) { // If the list item is empty, then do nothing
                }
            }
        }
    }


    /**
     * saves all the grades to the database
     * @param event
     */
    @FXML
    public void writeGradeFlow(ActionEvent event) {
        event.consume();
        brain.writeToJson();
    }

    // Menu methods
    /**
     * Closes the menu
     * @param event
     */
    @FXML
    public void onMenuClosePress(ActionEvent event) {
        event.consume();
        Stage currentStage = (Stage) viewGradeLabelClassData.getScene().getWindow();
        currentStage.close();
    }

    /**
     * Opens a file explorer showing the database containing the user files
     * @param event
     */
    @FXML
    public void onMenuDevShowPress(ActionEvent event) {
        try {
            File file = new File(System.getProperty("user.dir")+"/database/");
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
        }
        catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Something went wrong");
            alert.setContentText("Cannot open database file location. IOException thrown.");
            alert.showAndWait();
            return;
        }
    }

    /**
     * Shows a help guide on what to do on this fxml panel
     * @param event
     */
    @FXML
    public void onMenuHelpQuickGuide(ActionEvent event) {
        event.consume();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Quick Help");
        alert.setContentText("Click a class on the top\nType scale/grade & press \"Add Grade\" to add to selected class\nClick a grade in right list & press \"Remove Grade\"to remove it\nClick \"Save to Database\" to save changes\nCalculations for your GPA are in the left column (WIP)");
        alert.showAndWait();
        return;
    }

    /**
     * Shows my contact information
     * @param event
     */
    @FXML
    public void onMenuHelpContactPress(ActionEvent event) {
        event.consume();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Contact");
        alert.setContentText("ID: JChang32@masonlive.gmu.edu");
        alert.showAndWait();
        return;
    }

    /**
     * The last calculation/view-style button that was pressed
     */
    public enum GradeViewState {
        OVERVIEW, TARGET, ESTIMATE, CUMULATIVE;
    }
}
