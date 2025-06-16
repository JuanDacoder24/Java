module org.example.proyecto_practica {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.proyecto_practica to javafx.fxml;
    exports org.example.proyecto_practica;
}