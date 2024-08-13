module org.example.countryholiday {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.net.http;


    opens org.example.countryholiday to javafx.fxml, com.google.gson;
    exports org.example.countryholiday;
}