package org.example.countryholiday;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HolidayApp extends Application {

    private static Stage stage; // The primary stage for the application

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage; // Initialize the primary stage
        stage.setTitle("Holiday Finder"); // Set the title of the window
        stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.jpeg"))); // Set the application icon
        showScene("main.fxml"); // Show the main scene
    }

    // Method to switch scenes
    public void showScene(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml)); // Load the FXML file
        Parent root = fxmlLoader.load(); // Load the root element from the FXML file
        Controller controller = fxmlLoader.getController(); // Get the controller from the FXML file
        controller.initData(this, fxml); // Initialize the controller with data

        stage.setScene(new Scene(root)); // Set the new scene
        stage.show(); // Show the updated stage
    }

    // Method to get the primary stage
    public Stage getPrimaryStage() {
        return stage; // Return the primary stage
    }

    // Main method to launch the application
    public static void main(String[] args) {
        launch(args); // Launch the JavaFX application
    }
}
