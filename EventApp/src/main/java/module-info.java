module com.example.eventapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.eventapp to javafx.fxml;
    exports com.example.eventapp;
}