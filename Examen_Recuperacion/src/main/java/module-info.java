module com.example.examen_recuperacion {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.examen_recuperacion to javafx.fxml;
    exports com.example.examen_recuperacion;
}