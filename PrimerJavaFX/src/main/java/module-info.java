module com.example.primerjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.primerjavafx to javafx.fxml;
    exports com.example.primerjavafx;
}