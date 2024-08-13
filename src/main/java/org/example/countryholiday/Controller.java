package org.example.countryholiday;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Controller {

    private HolidayApp mainApp; // Reference to the main application
    private String currentFxml; // Current FXML file name

    @FXML
    private TextField countryInput; // Text field for country input
    @FXML
    private TextField yearInput; // Text field for year input
    @FXML
    private ComboBox<String> typeComboBox; // ComboBox for holiday type selection
    @FXML
    private Text holidayText; // Text element for displaying holiday information
    @FXML
    private ListView<String> listView; // ListView for displaying holidays
    @FXML
    private Button searchButton; // Button to trigger search
    @FXML
    private Button showDetails; // Button to show details of selected holiday

    // Initialize data and set up event handlers
    public void initData(HolidayApp mainApp, String fxml) {
        this.mainApp = mainApp;
        this.currentFxml = fxml;
        initialize();
    }

    @FXML
    public void initialize() {
        // Set up event handlers if currentFxml is not null
        if (currentFxml != null) {
            searchButton.setOnAction(this::searchHolidays); // Set action for search button
            showDetails.setOnAction(event -> {
                // Handle show details button action
                String selectedItem = listView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    String[] parts = selectedItem.split(" - ");
                    String name = parts[0];
                    String date = parts[1];
                    showDetails(event, name, date); // Show details of selected holiday
                }
            });
        }
    }

    @FXML
    public void searchHolidays(ActionEvent event) {
        // Get inputs and build API request URL
        String country = countryInput.getText().toUpperCase();
        String year = yearInput.getText();
        String type = typeComboBox.getValue();
        String url = "https://api.api-ninjas.com/v1/holidays?country=" + country + "&year=" + year + "&type=" + type;

        // Check if all inputs are provided
        if (country.isEmpty() || year.isEmpty() || type.isEmpty()) {
            listView.getItems().clear();
            listView.getItems().add("Please enter a country, year, and type.");
            return;
        }

        try {
            // Create HTTP client and request
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("X-Api-Key", "6DPfdm7YGF1K2GtTYQMomw==CTYlQRvWtWsyupDd")
                    .build();

            // Send request and get response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Process response
            if (response.statusCode() == 200) {
                String jsonResponse = response.body();
                Holiday[] holidays = parseHolidayResponse(jsonResponse);

                listView.getItems().clear();
                for (Holiday holiday : holidays) {
                    String name = holiday.getName();
                    String date = holiday.getDate();
                    listView.getItems().add(name + " - " + date); // Add holiday to listView
                }

            } else {
                listView.getItems().clear();
                listView.getItems().add("Holidays not found. Please check the inputs.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Parse JSON response into Holiday objects
    private Holiday[] parseHolidayResponse(String jsonResponse) {
        Gson gson = new Gson();
        return gson.fromJson(jsonResponse, Holiday[].class);
    }

    @FXML
    public void showDetails(ActionEvent event, String name, String date) {
        try {
            // Load details FXML and set up controller
            FXMLLoader loader = new FXMLLoader(getClass().getResource("details.fxml"));
            Parent root = loader.load();

            DetailsController detailsController = loader.getController();
            detailsController.initData(mainApp, name, date, countryInput.getText().toUpperCase());

            // Switch to details scene
            mainApp.getPrimaryStage().getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
