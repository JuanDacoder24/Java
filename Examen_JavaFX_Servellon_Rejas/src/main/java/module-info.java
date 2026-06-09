module com.example.examen_javafx_servellon_rejas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.examen_javafx_servellon_rejas to javafx.fxml;
    exports com.example.examen_javafx_servellon_rejas;
}