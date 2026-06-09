package com.example.examen_javafx_servellon_rejas;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    private final SQLAccess sqlAcces = new SQLAccess();

    // Contenedores de paneles
    @FXML private VBox PanelBotones;
    @FXML private VBox PanelMascota;
    @FXML private VBox PanelPropietario;
    @FXML private VBox PanelBuscarMascotas;
    @FXML private VBox PanelConsulta;

    private List<VBox> todosLosPaneles;

    // mascotas

    @FXML private TextField PasaporteMascota;
    @FXML private TextField NombreMascota;
    @FXML private TextField PesoMascota;
    @FXML private TextField DNIMascota;
    @FXML private DatePicker FechaNMascota;
    @FXML private ComboBox<Tipo> TipoMascota;

    // consultas

    @FXML private TextField DuracionConsulta;
    @FXML private TextField ObservacionesConsulta;
    @FXML private TextField PasaporteConsulta;
    @FXML private DatePicker FechaConsulta;
    @FXML private TextField HoraConsulta;

    // propietarios

    @FXML private TextField DNIPropietario;
    @FXML private TextField NombrePropietario;
    @FXML private TextField ApellidoPropietario;
    @FXML private TextField TelefonoPropietario;
    @FXML private TextField DireccionPropietario;
    @FXML private TextField EmailPropietario;

    // buscar y actualizar

    @FXML private TextField PasaporteBuscar;
    @FXML private TextField BuscarNombreMascota;
    @FXML private TextField PesoBuscarMascota;
    @FXML private ListView<Mascota> ListaMascotas;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        todosLosPaneles = List.of(PanelBotones, PanelMascota, PanelPropietario, PanelConsulta, PanelBuscarMascotas);
        selectPanelVisible(PanelBotones);

        try {
            TipoMascota.setItems(sqlAcces.cargarTiposConsulta());
        } catch (SQLException e) {
            showAlert("Error", "No se pudieron cargar los tipos de mascota.");
            e.printStackTrace();
        }

        ListaMascotas.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                PasaporteBuscar.setText(newVal.getPasaporte());
                BuscarNombreMascota.setText(newVal.getNombre());
                PesoBuscarMascota.setText(String.valueOf(newVal.getPeso()));
            }
        });

        ListaMascotas.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Mascota m, boolean empty) {
                super.updateItem(m, empty);
                if (empty || m == null) {
                    setText(null);
                } else {
                    setText(m.getPasaporte() + " - " + m.getNombre() +  " - " + m.getPeso());
                }
            }
        });

        cargarListaMascotas();
    }

    // navegacion entre paneles

    private void selectPanelVisible(VBox panelAMostrar) {
        for (VBox panel : todosLosPaneles) {
            panel.setVisible(panel == panelAMostrar);
        }
    }

    @FXML private void BotonRegistrarMascota() {
        selectPanelVisible(PanelMascota);
    }

    @FXML private void BotonVolver() {
        selectPanelVisible(PanelBotones);
    }

    @FXML private void BotonRegistrarPropietario() {
        selectPanelVisible(PanelPropietario);
    }

    @FXML private void BotonRegistrarConsulta() {
        selectPanelVisible(PanelConsulta);
    }

    @FXML private void BotonListaMascotas() {
        selectPanelVisible(PanelBuscarMascotas);
        cargarListaMascotas();
    }

    // alertas

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

    @FXML
    private void RegistrarMascota() {
        if (PasaporteMascota.getText().isBlank() || NombreMascota.getText().isBlank() ||
                PesoMascota.getText().isBlank() || DNIMascota.getText().isBlank() ||
                FechaNMascota.getValue() == null || TipoMascota.getValue() == null) {
            showError("Campos incompletos", "Por favor, rellene todos los campos del formulario.");
            return;
        }

        try {
            String pasaporte = PasaporteMascota.getText();
            String nombre = NombreMascota.getText();
            double peso = Double.parseDouble(PesoMascota.getText());
            LocalDateTime fechaNacimiento = FechaNMascota.getValue().atStartOfDay();
            Tipo tipo = TipoMascota.getValue();

            Propietario propietario = sqlAcces.buscarPropietarioPorDNI(DNIMascota.getText());
            if (propietario == null) {
                showError("Error de Dueño", "No existe ningún propietario registrado con el DNI especificado.");
                return;
            }

            Mascota m = new Mascota(pasaporte, nombre,fechaNacimiento, peso,tipo,propietario);
            sqlAcces.añadirMascota(m);

            showAlert("Éxito", "Mascota registrada correctamente.");
            limpiarCamposMascota();

        } catch (NumberFormatException e) {
            showError("Formato incorrecto", "El formato del campo 'Peso' no es válido.");
        } catch (SQLException e) {
            showError("Error de Base de Datos", "No se pudo guardar la mascota en el sistema.");
            e.printStackTrace();
        }
    }

    private void limpiarCamposMascota() {
        PasaporteMascota.clear();
        NombreMascota.clear();
        PesoMascota.clear();
        DNIMascota.clear();
        FechaNMascota.setValue(null);
        TipoMascota.setValue(null);
    }


    @FXML
    private void RegistrarPropietario() {
        if (DNIPropietario.getText().isBlank() || NombrePropietario.getText().isBlank() ||
                ApellidoPropietario.getText().isBlank() || TelefonoPropietario.getText().isBlank() ||
                DireccionPropietario.getText().isBlank() || EmailPropietario.getText().isBlank()) {
            showError("Campos incompletos", "Por favor, rellene todos los campos.");
            return;
        }

        Propietario p = new Propietario(
                DNIPropietario.getText(), NombrePropietario.getText(), ApellidoPropietario.getText(),
                TelefonoPropietario.getText(), DireccionPropietario.getText(), EmailPropietario.getText()
        );

        sqlAcces.añadirPropietario(p);
        showAlert("Éxito", "Propietario registrado con éxito.");
        limpiarCamposPropietario();

    }

    private void limpiarCamposPropietario() {
        DNIPropietario.clear(); NombrePropietario.clear(); ApellidoPropietario.clear();
        TelefonoPropietario.clear(); DireccionPropietario.clear(); EmailPropietario.clear();
    }



    @FXML
    private void RegistrarConsulta() {
        if (PasaporteConsulta.getText().isBlank() || FechaConsulta.getValue() == null ||
                HoraConsulta.getText().isBlank() || DuracionConsulta.getText().isBlank()) {
            showError("Campos incompletos", "Por favor rellene los campos obligatorios.");
            return;
        }

        try {
            String pasaporte = PasaporteConsulta.getText();
            LocalDate fecha = FechaConsulta.getValue();
            LocalTime hora = LocalTime.parse(HoraConsulta.getText());
            LocalDateTime fechaHora = LocalDateTime.of(fecha, hora);
            int duracion = Integer.parseInt(DuracionConsulta.getText());
            String observaciones = ObservacionesConsulta.getText();

            Mascota mascota = sqlAcces.buscarMascotaPorPasaporte(pasaporte);
            if (mascota == null) {
                showError("No encontrado", "No existe ninguna mascota con el pasaporte: " + pasaporte);
                return;
            }

            Consulta c = new Consulta(0, fechaHora, duracion, observaciones, mascota);
            sqlAcces.añadirConsulta(c);

            showAlert("Éxito", "Consulta registrada correctamente.");

        } catch (DateTimeParseException e) {
            showError("Formato de Hora", "La hora debe seguir el formato HH:mm (ej. 16:45).");
        } catch (NumberFormatException e) {
            showError("Formato de Duración", "La duración debe ser un número entero de minutos.");
        } catch (SQLException e) {
            showError("Error del sistema", "No se pudo agendar la consulta.");
            e.printStackTrace();
        }
    }


    @FXML
    private void BuscarMascota() {
        String pasaporte = PasaporteBuscar.getText().trim();

        if (pasaporte.isEmpty()) {
            cargarListaMascotas();
        } else {
            try {
                Mascota m = sqlAcces.buscarMascotaPorPasaporte(pasaporte);
                if (m == null) {
                    showError("No encontrado", "No se encontró ninguna mascota con ese pasaporte.");
                    cargarListaMascotas();
                } else {
                    BuscarNombreMascota.setText(m.getNombre());
                    PesoBuscarMascota.setText(String.valueOf(m.getPeso()));
                    ListaMascotas.getSelectionModel().select(m);
                }
            } catch (SQLException e) {
                showError("Error", "Error al conectar con la base de datos.");
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void ActualizarCampos() {
        String pasaporte = PasaporteBuscar.getText().trim();

        if (pasaporte.isEmpty() || BuscarNombreMascota.getText().isBlank() || PesoBuscarMascota.getText().isBlank()) {
            showError("Campos incompletos", "Debe buscar una mascota y rellenar los datos de nombre y peso.");
            return;
        }

        try {
            Mascota m = sqlAcces.buscarMascotaPorPasaporte(pasaporte);
            if (m == null) {
                showError("Error", "La mascota que intenta actualizar ya no existe.");
                return;
            }

            m.setNombre(BuscarNombreMascota.getText());
            m.setPeso(Double.parseDouble(PesoBuscarMascota.getText()));

            sqlAcces.actualizarMascota(m);
            showAlert("Éxito", "Mascota actualizada correctamente.");
            cargarListaMascotas();

        } catch (NumberFormatException e) {
            showError("Formato incorrecto", "El peso debe ser un número decimal válido.");
        } catch (SQLException e) {
            showError("Error", "No se pudieron guardar los cambios.");
            e.printStackTrace();
        }
    }

    private void cargarListaMascotas() {
        try {
            ListaMascotas.setItems(sqlAcces.cargarMascotas());
        } catch (SQLException e) {
            showError("Error de carga", "No se pudo actualizar la lista de mascotas.");
            e.printStackTrace();
        }
    }

    @FXML
    private void ExportarDatos() {
        try {
            ObservableList<Mascota> mascotas = sqlAcces.cargarMascotas();
            if (mascotas.isEmpty()) {
                showAlert("Aviso", "No hay datos de mascotas para exportar.");
                return;
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("mascotas.dat"))) {
                oos.writeObject(new ArrayList<>(mascotas));
            }
            showAlert("Éxito", "Datos exportados correctamente a 'mascotas.dat'.");

        } catch (SQLException e) {
            showError("Error", "Error de base de datos al obtener mascotas para exportar.");
            e.printStackTrace();
        } catch (IOException e) {
            showError("Error de archivo", "No se pudo escribir el archivo de exportación.");
            e.printStackTrace();
        }
    }


    public void BotonSalir(MouseEvent mouseEvent) {
    }
}