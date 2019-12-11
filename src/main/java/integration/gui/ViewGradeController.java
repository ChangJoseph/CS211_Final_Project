package integration.gui;

import core.GMUClass;
import core.Grades;
import integration.IntegrationBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewGradeController {

    private IntegrationBase brain;
    @FXML ComboBox<String> viewGradeClassesComboBox;
    @FXML ListView<Double> viewGradeListView;
    @FXML ListView<String> viewGradeMetadataListView;
    @FXML ScrollBar viewGradeScrollBar;

    public ViewGradeController(IntegrationBase brain) {
        this.brain = brain;
    }

    @FXML
    public void initialize() {
        Map<String, GMUClass> classes = brain.getClassesMap();
        viewGradeClassesComboBox.getItems().addAll(classes.keySet());
    }

    @FXML
    public void comboBoxChange(ActionEvent event) {
        viewGradeMetadataListView.getItems().clear();
        for (Map.Entry<String,GMUClass> entry : brain.getClassesMap().entrySet()) {
            StringBuilder meta = new StringBuilder();
            meta.append("ClassID: ");
            meta.append(entry.getKey());
            meta.append(" \tCredit: ");
            meta.append(entry.getValue().getCredit());
            meta.append(" ");
            viewGradeMetadataListView.getItems().add(meta.toString());
            for (int count = 0; count < brain.countTotalGrades(viewGradeClassesComboBox.getValue())-1; count++) {
                viewGradeMetadataListView.getItems().add("");
            }
        }

        viewGradeListView.getItems().clear();
        for (Grades gradesClass : brain.getGrades(viewGradeClassesComboBox.getValue())) {
            for (double grade : gradesClass.getGrades()) {
                viewGradeListView.getItems().add(grade);
            }
        }
    }
}
