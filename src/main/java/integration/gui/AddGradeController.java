package integration.gui;

import core.GMUClass;
import integration.IntegrationBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class AddGradeController {

    private IntegrationBase brain;
    @FXML private Label mainPrompt;
    @FXML private TextArea pasteHere;

    public AddGradeController(IntegrationBase brain) {
        this.brain = brain;
    }

    /**
     * Button action for adding a class
     * refers to the text area for classID
     */
    public void addClass() {
        String classID = pasteHere.getText();
        if (classID.isEmpty() || classID == null) {
            return;
        }

        addClass(classID,4); // Defaults to 4 for now
    }
    public void addClass(String classID, int credit) {
        GMUClass gmuClass = new GMUClass(credit);
        brain.addClass(classID, gmuClass);
        brain.writeToJson();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success!");
        alert.setContentText("Added " + classID + " to the database");
        alert.showAndWait();
    }

    // Menu methods; same documentation as other controllers
    @FXML
    public void onMenuClosePress(ActionEvent event) {
        event.consume();
        Stage currentStage = (Stage) pasteHere.getScene().getWindow();
        currentStage.close();
    }
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
    @FXML
    public void onMenuHelpContactPress(ActionEvent event) {
        event.consume();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Contact");
        alert.setContentText("ID: JChang32@masonlive.gmu.edu");
        alert.showAndWait();
        return;
    }
}
