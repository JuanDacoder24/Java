module com.example.ejemplofxclase {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.ejemplofxclase to javafx.fxml;
    exports com.example.ejemplofxclase;
}