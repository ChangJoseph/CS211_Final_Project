package integration.gui;

import core.GMUClass;
import core.Grades;
import integration.IntegrationBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewGradeController {

    private IntegrationBase brain;
    private GradeViewState state;
    @FXML ComboBox<String> viewGradeClassesComboBox;
    @FXML ListView<Double> viewGradeListView;
    @FXML ListView<String> viewGradeMetadataListView;
    @FXML Label viewGradeLabelClassData;
    @FXML ScrollBar viewGradeScrollBar;

    public ViewGradeController(IntegrationBase brain) {
        this.brain = brain;
        this.state = GradeViewState.OVERVIEW;
    }

    @FXML
    public void initialize() {
        Map<String, GMUClass> classes = brain.getClassesMap();
        viewGradeClassesComboBox.getItems().addAll(classes.keySet());
    }

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

    @FXML
    public void overview(ActionEvent event) {
        event.consume();
        state = GradeViewState.OVERVIEW;
        if (viewGradeClassesComboBox.getValue() == null) {
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
    @FXML
    public void targetGPA(ActionEvent event) {
        event.consume();
        state = GradeViewState.TARGET;
        if (viewGradeClassesComboBox.getValue() == null) { return; }
        String classID = viewGradeClassesComboBox.getValue();

    }
    @FXML
    public void estimateGPA(ActionEvent event) {
        event.consume();
        state = GradeViewState.ESTIMATE;
        if (viewGradeClassesComboBox.getValue() == null) { return; }
        String classID = viewGradeClassesComboBox.getValue();

        double sum = 0.0;
        for (Grades grades : brain.getClass(classID).getGradesList()) {
            sum += brain.scaledGrade(grades);
        }
        if (sum > 1) {
            sum = 1.0;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Estimate GPA");
        alert.setContentText("Estimated GPA: " + sum * 100);
        alert.showAndWait();
    }
    @FXML
    public void cumulativeGPA(ActionEvent event) {
        event.consume();
        state = GradeViewState.CUMULATIVE;
        if (viewGradeClassesComboBox.getValue() == null) { return; }
        String classID = viewGradeClassesComboBox.getValue();

    }

    public enum GradeViewState {
        OVERVIEW, TARGET, ESTIMATE, CUMULATIVE;
    }
}
