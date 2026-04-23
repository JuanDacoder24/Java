module com.example.apppersonasfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.apppersonasfx to javafx.fxml;
    exports com.example.apppersonasfx;
}