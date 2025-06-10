package com.example.examen_recuperacion;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class VetAppController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}