package integration.gui;

import integration.IntegrationBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainMenuController {

    private IntegrationBase brain;
    @FXML Label menuLabelWelcome;

    public MainMenuController(String userID, String dob) {
        brain = new IntegrationBase(userID, dob);
    }
    public void initialize() {
        menuLabelWelcome.setText("Welcome " + brain.getStudent().getID());
    }

    /**
     * This is button that triggers view grade panel to open
     */
    @FXML
    public void onViewButtonPress() {
        if (brain.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Empty Dataset");
            alert.setContentText("Add classes before viewing them!");
            alert.showAndWait();
            return;
        }
        // Prepare and open new window
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ViewGrade.fxml"));
            fxmlLoader.setController(new ViewGradeController(brain));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This is button that triggers add grade panel to open
     */
    @FXML
    public void onAddButtonPress() {
        // Prepare and open new window
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AddGrade.fxml"));
            fxmlLoader.setController(new AddGradeController(brain));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // Menu
    @FXML
    public void onMenuClosePress(ActionEvent event) {
        event.consume();
        Stage currentStage = (Stage) menuLabelWelcome.getScene().getWindow();
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
