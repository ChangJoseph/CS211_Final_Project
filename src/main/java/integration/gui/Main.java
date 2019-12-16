package integration.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * First thing that is opened is the Login.fxml file
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    /**
     * This is the main method to actually open the JavaFX User Interface
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
