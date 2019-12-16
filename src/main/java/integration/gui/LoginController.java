package integration.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.UITree;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;


public class LoginController {

    @FXML private Button loginButtonMainLogin;
//    @FXML private MenuItem loginOptionsClose;
    @FXML private TextField loginFieldUserID;
    @FXML private TextField loginFieldDOB;

    // Button listener for when the "Login" button is pressed
    @FXML
    public void onLoginButtonPress(ActionEvent event) {
        event.consume();

        // Store login data
        String userID = loginFieldUserID.getText();
        String userDOB = loginFieldDOB.getText();
        // Check if login data is valid
        if (userID.isEmpty() || userDOB.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid Input");
            alert.setContentText("Invalid Input! Please type in a response!");
            alert.showAndWait();
            return;
        }
        else if (!Pattern.matches("[A-Za-z]{2,}[0-9]*", userID)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid ID Input");
            alert.setContentText("Invalid ID! Please type in a valid ID!");
            alert.showAndWait();
            return;
        }
        else if (!Pattern.matches("[0-9]{2}[/-]?[0-9]{2}[/-]?[0-9]{4}", userDOB)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid DOB Input");
            alert.setContentText("Invalid DOB! Format: MMDDYYYY (ex: 01091999 for January 9th, 1999)");
            alert.showAndWait();
            return;
        }
        // Reformats login data
        userID = userID.toUpperCase().replaceAll("[^A-Z0-9]","");
        userDOB = userDOB.replaceAll("[^0-9]","");

        // Close current window
        Stage currentStage = (Stage) loginButtonMainLogin.getScene().getWindow();
        currentStage.close();

        // Prepare and open new window
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MainMenu.fxml"));
            fxmlLoader.setController(new MainMenuController(userID, userDOB));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the project
     * @param event
     */
    @FXML
    public void onMenuClosePress(ActionEvent event) {
        event.consume();
        Stage currentStage = (Stage) loginButtonMainLogin.getScene().getWindow();
        currentStage.close();
    }

    /**
     * Populates the text fields with generic use information
     * @param event
     */
    @FXML
    public void onMenuDevPopulatePress(ActionEvent event) {
        loginFieldUserID.setText("JDoe");
        loginFieldDOB.setText("01012000");
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

    public void initialize() {

    }

}
