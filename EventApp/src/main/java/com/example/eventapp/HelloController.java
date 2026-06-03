package com.example.eventapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    private final SQLAccess sql = new SQLAccess();

    // contenedores de paneles

    @FXML private VBox PanelEvento;
    @FXML private VBox PanelBotones;
    @FXML private VBox PanelEntradas;
    @FXML private VBox PanelRegistrarAsistente;
    @FXML private VBox PanelBuscarEvento;

    private List<VBox> todosLosPaneles;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        // inicializamos los paneles
        todosLosPaneles = List.of(PanelBotones, PanelEvento,  PanelEntradas, PanelRegistrarAsistente, PanelBuscarEvento);
        selectPanelVisible(PanelBotones);

        //configuramos el combobox de tipos


    }

    private void selectPanelVisible(VBox panelAMostrar) {
        for (VBox panel : todosLosPaneles) {
            panel.setVisible(panel == panelAMostrar);
        }
    }

    @FXML private void BotonRegistrarEvento(){selectPanelVisible(PanelEvento);};
    @FXML private void BotonRegistrarEntradas(){selectPanelVisible(PanelEntradas);};

    @FXML private void BotonRegistrarAsistente(){selectPanelVisible(PanelRegistrarAsistente);};

    @FXML private void BotonListaEventos(){
        selectPanelVisible(PanelBuscarEvento);
        cargarListaEventos();
    };

    private void cargarListaEventos() {

    }



    // --- ALERTAS REUTILIZABLES ---

    private void showAlert(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void showError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void BotonVolver(MouseEvent mouseEvent) {
    }

    public void RegistrarEvento(MouseEvent mouseEvent) {
    }


}
