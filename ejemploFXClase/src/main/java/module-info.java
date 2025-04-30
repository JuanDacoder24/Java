module com.example.ejemplofxclase {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ejemplofxclase to javafx.fxml;
    exports com.example.ejemplofxclase;
}