package com.example.vetdawexam;

import Demo.Mascota;
import Demo.Propietario;
import Demo.SQLAccess;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.awt.event.ActionEvent;

public class HelloController {
    @FXML
    private ObservableList<Mascota> mascotas = FXCollections.observableArrayList();

    @FXML
    private ObservableList<Propietario> propietarios = FXCollections.observableArrayList();

    @FXML
    private TableView<Mascota> tableView;

    @FXML
    private VBox registrarMascota;

    @FXML
    private Label pasaporte;

    @FXML
    private Label nombre;

    @FXML
    private Label fNacimiento;

    @FXML
    private Label peso;

    @FXML
    private Label tipo;

    @FXML
    private Label nPropietario;

    @FXML
    private TextField labelPasaporte;

    @FXML
    private TextField labelNombre;

    @FXML
    private TextField labelFnacimiento;

    @FXML
    private TextField labelPeso;

    @FXML
    private TextField labelTipo;

    @FXML
    private TextField labelNpropietario;

    @FXML
    private Button btnVolver;

    @FXML
    private Button btnSave;

    @FXML
    private AnchorPane registrarMascotaPane;

    @FXML
    private AnchorPane menuPrincipalPane;

    @FXML
    private Label nombreP;

    @FXML
    private Label apellidos;

    @FXML
    private Label dni;

    @FXML
    private Label telefono;

    @FXML
    private Label direccion;

    @FXML
    private Label email;

    @FXML
    private TextField FieldNombre;

    @FXML
    private TextField FieldApellidos;

    @FXML
    private TextField FieldDNI;

    @FXML
    private TextField FieldTelefono;

    @FXML
    private TextField FieldDireccion;

    @FXML
    private TextField FieldEmail;


    @FXML
    private void añadirMascota() {
        String pasaporte = labelPasaporte.getText();
        String nombre = labelNombre.getText();
        String fecha = labelFnacimiento.getText();
        String peso = labelPeso.getText();
        String tipo = labelTipo.getText();

        if (labelPasaporte.getText().isEmpty() || labelNombre.getText().isEmpty() || labelFnacimiento.getText().isEmpty()
        || labelPeso.getText().isEmpty() || labelTipo.getText() == null) {
            showAlert("Error, todos los campos son obligatorios");
        }else{
            Mascota m = new Mascota(pasaporte, nombre, fecha, peso, tipo);
            mascotas.add(m);
            SQLAccess.añadirMascota(m);
            showAlert("Exito, Mascota añadida correctamente");
            clearFields();
        }
    }

    @FXML
    private void añadirPropietario() {
        String nombre = FieldNombre.getText();
        String apellidos = FieldApellidos.getText();
        String dni = FieldDNI.getText();
        String telefono = FieldTelefono.getText();
        String direccion = FieldDireccion.getText();
        String email = FieldEmail.getText();

        if(FieldNombre.getText().isEmpty() || FieldApellidos.getText().isEmpty() || FieldDNI.getText().isEmpty()  || FieldTelefono.getText().isEmpty() || FieldDireccion.getText().isEmpty() || FieldEmail.getText() == null) {
            showAlert("Error, todos los campos son obligatorios");
        }else{
            Propietario p = new Propietario(nombre, apellidos, dni, telefono, direccion, email);
            propietarios.add(p);
            SQLAccess.añadirPropietario(p);
            showAlert("Exito, Propietario añadido correctamente");
            clearFields();
        }
    }

    private void clearFields(){
        labelPasaporte.setText("");
        labelNombre.setText("");
        labelFnacimiento.setText("");
        labelPeso.setText("");
        labelTipo.setText("");
        labelNpropietario.setText("");
        FieldNombre.setText("");
        FieldApellidos.setText("");
        FieldDNI.setText("");
        FieldTelefono.setText("");
        FieldDireccion.setText("");
        FieldEmail.setText("");
    }

    private void showAlert(String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }

    @FXML
    protected void OnBtnExitApp(ActionEvent event){
        Platform.exit();
    }

}