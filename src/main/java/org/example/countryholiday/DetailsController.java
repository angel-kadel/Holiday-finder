package org.example.countryholiday;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;

public class DetailsController {

    private HolidayApp mainApp; // Reference to the main application

    @FXML
    private Text holidayNameText; // Text element for displaying holiday name
    @FXML
    private Text holidayDateText; // Text element for displaying holiday date
    @FXML
    private Text holidayCountryText; // Text element for displaying holiday country
    @FXML
    private Text holidayDescriptionText; // Text element for displaying holiday description
    @FXML
    private Button goBack; // Button to return to the main scene

    // Initialize data and set holiday details
    public void initData(HolidayApp mainApp, String holidayName, String holidayDate, String holidayCountry) {
        this.mainApp = mainApp;
        holidayNameText.setText(holidayName); // Set holiday name
        holidayDateText.setText(holidayDate); // Set holiday date
        holidayCountryText.setText(holidayCountry); // Set holiday country
        holidayDescriptionText.setText(holidayName + " is celebrated in " + holidayCountry + " on " + holidayDate + ". This holiday is observed annually and holds significant cultural or historical importance. People in " + holidayCountry + " engage in various traditional activities and come together to make it a day of remembrance."); // Set holiday description
        initialize();
    }

    @FXML
    public void initialize() {
        goBack.setText("Go Back"); // Set button text
        goBack.setOnAction(this::goBack); // Set action for goBack button
    }

    @FXML
    public void goBack(ActionEvent event) {
        try {
            mainApp.showScene("main.fxml"); // Switch to the main scene
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
