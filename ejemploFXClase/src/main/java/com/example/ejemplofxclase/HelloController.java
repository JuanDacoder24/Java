package com.example.ejemplofxclase;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Persona;

import java.net.URL;
import java.util.ResourceBundle;


public class HelloController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameTextField.setPromptText("Nombre");
        lastNameTextField.setPromptText("Apellido");
        ageTextField.setPromptText("Edad");
        emailTextField.setPromptText("Email");
        numberTextField.setPromptText("Telefono");
    }

    @FXML
    private VBox mainPanel;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField ageTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField numberTextField;

    private Persona persona;

    @FXML
    protected void onSaveButtonAction(ActionEvent event) {
        persona = new Persona();

        try {
            persona.setNombre(nameTextField.getText());
            persona.setApellido(lastNameTextField.getText());
            persona.setEdad(Integer.parseInt(ageTextField.getText()));
            persona.setEmail(emailTextField.getText());
            persona.setTelefono(numberTextField.getText());

            mainPanel.setVisible(false);

        } catch (NumberFormatException e) {
            ageTextField.setText("");
            ageTextField.setPromptText("Escriba un numero por favor");
        }

    }

    @FXML
    protected void onCloseButtonAction(ActionEvent event) {
        Platform.exit();
    }



}