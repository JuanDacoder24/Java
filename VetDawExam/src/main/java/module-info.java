module com.example.vetdawexam {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.vetdawexam to javafx.fxml;
    exports com.example.vetdawexam;
}