package com.example.vetdawexam;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import Demo.SQLAccess;
import Demo.Mascota;
import Demo.Propietario;
import Demo.Consulta;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    // Paneles principales
    @FXML private VBox pantallaPrincipal;
    @FXML private VBox RegistroMascota;
    @FXML private VBox RegistroPropietario;
    @FXML private VBox RegistroConsulta;
    @FXML private VBox BusquedaMascota;

    // Campos del formulario de Mascota
    @FXML private TextField MascotaPasaporte;
    @FXML private TextField MascotaNombre;
    @FXML private TextField MascotaPeso;
    @FXML private TextField MascotaFNacimiento;
    @FXML private TextField MascotaTipo;
    @FXML private ListView<Propietario> MascotaPropietario;

    // Campos del formulario de Propietario
    @FXML private TextField PropietarioNombre;
    @FXML private TextField PropietarioApellido;
    @FXML private TextField PropietarioDni;
    @FXML private TextField PropietarioTelefono;
    @FXML private TextField PropietarioDireccion;
    @FXML private TextField PropietarioEmail;

    // Campos del formulario de Consulta
    @FXML private TextField ConsultaFecha;
    @FXML private TextField ConsultaDuracion;
    @FXML private TextField ConsultaObservaciones;
    @FXML private ListView<Mascota> ConsultaMascota;

    // Campos de búsqueda
    @FXML private TextField BuscarMascota;
    @FXML private ListView<String> ResultadoBusqueda;

    private SQLAccess sqlAccess;
    private ObservableList<Propietario> propietariosList;
    private ObservableList<Mascota> mascotasList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sqlAccess = new SQLAccess();

        // Inicializar listas observables
        propietariosList = FXCollections.observableArrayList();
        mascotasList = FXCollections.observableArrayList();

        // Cargar datos para los ListView
        cargarPropietarios();
        cargarMascotas();

        // Configurar ListViews
        setupListViews();

        // Mostrar pantalla principal al inicio
        mostrarPantallaPrincipal();
    }

    private void setupListViews() {
        // Configurar cómo se muestran los propietarios en el ListView
        MascotaPropietario.setCellFactory(lv -> new ListCell<Propietario>() {
            @Override
            protected void updateItem(Propietario propietario, boolean empty) {
                super.updateItem(propietario, empty);
                if (empty || propietario == null) {
                    setText(null);
                } else {
                    setText(propietario.getDni() + " - " + propietario.getNombre() + " " + propietario.getApellidos());
                }
            }
        });
        MascotaPropietario.setItems(propietariosList);

        // Configurar cómo se muestran las mascotas en el ListView
        ConsultaMascota.setCellFactory(lv -> new ListCell<Mascota>() {
            @Override
            protected void updateItem(Mascota mascota, boolean empty) {
                super.updateItem(mascota, empty);
                if (empty || mascota == null) {
                    setText(null);
                } else {
                    setText(mascota.getPasaporte() + " - " + mascota.getNombre());
                }
            }
        });
        ConsultaMascota.setItems(mascotasList);
    }

    private void cargarPropietarios() {
        List<Propietario> propietarios = SQLAccess.getPropietarios();
        propietariosList.clear();
        propietariosList.addAll(propietarios);
    }

    private void cargarMascotas() {
        List<Mascota> mascotas = SQLAccess.getMascotas();
        mascotasList.clear();
        mascotasList.addAll(mascotas);
    }

    // Métodos para navegar entre pantallas
    private void mostrarPantallaPrincipal() {
        pantallaPrincipal.setVisible(true);
        RegistroMascota.setVisible(false);
        RegistroPropietario.setVisible(false);
        RegistroConsulta.setVisible(false);
        BusquedaMascota.setVisible(false);
    }

    @FXML
    private void onAccederRegistrarMacota(ActionEvent event) {
        pantallaPrincipal.setVisible(false);
        RegistroMascota.setVisible(true);
        limpiarCamposMascota();
    }

    @FXML
    private void onAccederRegistrarPropietario(ActionEvent event) {
        pantallaPrincipal.setVisible(false);
        RegistroPropietario.setVisible(true);
        limpiarCamposPropietario();
    }

    @FXML
    private void onAccederRegistrarConsulta(ActionEvent event) {
        pantallaPrincipal.setVisible(false);
        RegistroConsulta.setVisible(true);
        cargarMascotas(); // Recargar mascotas para asegurar datos actualizados
        limpiarCamposConsulta();
    }

    @FXML
    private void onAccederBuscarMascota(ActionEvent event) {
        pantallaPrincipal.setVisible(false);
        BusquedaMascota.setVisible(true);
        ResultadoBusqueda.getItems().clear();
        BuscarMascota.clear();
    }

    @FXML
    private void onBotonVolverRegistroMascota(ActionEvent event) {
        mostrarPantallaPrincipal();
    }

    @FXML
    private void onBotonVolverRegistroPropietario(ActionEvent event) {
        mostrarPantallaPrincipal();
    }

    @FXML
    private void onBotonVolverRegistroConsulta(ActionEvent event) {
        mostrarPantallaPrincipal();
    }

    @FXML
    private void onVolverBuscar(ActionEvent event) {
        mostrarPantallaPrincipal();
    }

    // Métodos para guardar registros
    @FXML
    private void onBotonGuardarMascota(ActionEvent event) {
        // Validar campos
        if (MascotaPasaporte.getText().isEmpty() || MascotaNombre.getText().isEmpty() ||
                MascotaPeso.getText().isEmpty() || MascotaFNacimiento.getText().isEmpty() ||
                MascotaTipo.getText().isEmpty() || MascotaPropietario.getSelectionModel().isEmpty()) {

            mostrarAlerta("Error", "Todos los campos son obligatorios", Alert.AlertType.ERROR);
            return;
        }

        try {
            Propietario propietarioSeleccionado = MascotaPropietario.getSelectionModel().getSelectedItem();

            Mascota mascota = new Mascota(
                    MascotaPasaporte.getText(),
                    MascotaNombre.getText(),
                    LocalDate.parse(MascotaFNacimiento.getText()),
                    Double.parseDouble(MascotaPeso.getText()),
                    MascotaTipo.getText()
            );
            mascota.setPropietario(propietarioSeleccionado);

            int resultado = sqlAccess.insertMascota(mascota);

            if (resultado > 0) {
                mostrarAlerta("Éxito", "Mascota registrada correctamente", Alert.AlertType.INFORMATION);
                limpiarCamposMascota();
                cargarMascotas(); // Actualizar lista de mascotas
            } else {
                mostrarAlerta("Error", "No se pudo registrar la mascota", Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            mostrarAlerta("Error", "Error al registrar: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    private void onBotonGuardarPropietario(ActionEvent event) {
        // Validar campos
        if (PropietarioNombre.getText().isEmpty() || PropietarioApellido.getText().isEmpty() ||
                PropietarioDni.getText().isEmpty() || PropietarioTelefono.getText().isEmpty() ||
                PropietarioDireccion.getText().isEmpty() || PropietarioEmail.getText().isEmpty()) {

            mostrarAlerta("Error", "Todos los campos son obligatorios", Alert.AlertType.ERROR);
            return;
        }

        try {
            Propietario propietario = new Propietario(
                    PropietarioNombre.getText(),
                    PropietarioApellido.getText(),
                    PropietarioDni.getText()
            );
            propietario.setTelefono(PropietarioTelefono.getText());
            propietario.setDireccion(PropietarioDireccion.getText());
            propietario.setEmail(PropietarioEmail.getText());

            int resultado = sqlAccess.insertPropietario(propietario);

            if (resultado > 0) {
                mostrarAlerta("Éxito", "Propietario registrado correctamente", Alert.AlertType.INFORMATION);
                limpiarCamposPropietario();
                cargarPropietarios(); // Actualizar lista de propietarios
            } else {
                mostrarAlerta("Error", "No se pudo registrar el propietario", Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            mostrarAlerta("Error", "Error al registrar: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    private void onBotonGuardarConsulta(ActionEvent event) {
        // Validar campos
        if (ConsultaFecha.getText().isEmpty() || ConsultaDuracion.getText().isEmpty() ||
                ConsultaObservaciones.getText().isEmpty() || ConsultaMascota.getSelectionModel().isEmpty()) {

            mostrarAlerta("Error", "Todos los campos son obligatorios", Alert.AlertType.ERROR);
            return;
        }

        try {
            Mascota mascotaSeleccionada = ConsultaMascota.getSelectionModel().getSelectedItem();

            Consulta consulta = new Consulta(
                    LocalDate.parse(ConsultaFecha.getText()),
                    Integer.parseInt(ConsultaDuracion.getText()),
                    ConsultaObservaciones.getText()
            );
            consulta.setMascota(mascotaSeleccionada);

            int resultado = sqlAccess.insertConsulta(consulta);

            if (resultado > 0) {
                mostrarAlerta("Éxito", "Consulta registrada correctamente", Alert.AlertType.INFORMATION);
                limpiarCamposConsulta();
            } else {
                mostrarAlerta("Error", "No se pudo registrar la consulta", Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            mostrarAlerta("Error", "Error al registrar: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    private void onBuscarMascota(ActionEvent event) {
        String pasaporte = BuscarMascota.getText().trim();

        if (pasaporte.isEmpty()) {
            mostrarAlerta("Error", "Por favor, introduce un pasaporte", Alert.AlertType.ERROR);
            return;
        }

        List<Mascota> mascotasEncontradas = SQLAccess.BuscarMascota(pasaporte);
        ResultadoBusqueda.getItems().clear();

        if (mascotasEncontradas.isEmpty()) {
            ResultadoBusqueda.getItems().add("No se encontraron mascotas con el pasaporte: " + pasaporte);
        } else {
            for (Mascota mascota : mascotasEncontradas) {
                String info = String.format("Pasaporte: %s | Nombre: %s | Peso: %.2f kg | Fecha Nac: %s | Tipo: %s",
                        mascota.getPasaporte(), mascota.getNombre(), mascota.getPeso(),
                        mascota.getFechaNacimiento(), mascota.getTipo());
                ResultadoBusqueda.getItems().add(info);
            }
        }
    }

    @FXML
    private void onExportarDatos(ActionEvent event) {
        // TODO: Implementar exportación de datos
        mostrarAlerta("Información", "Funcionalidad de exportación en desarrollo", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void onSalirApp(ActionEvent event) {
        System.exit(0);
    }

    // Métodos auxiliares
    private void limpiarCamposMascota() {
        MascotaPasaporte.clear();
        MascotaNombre.clear();
        MascotaPeso.clear();
        MascotaFNacimiento.clear();
        MascotaTipo.clear();
        MascotaPropietario.getSelectionModel().clearSelection();
    }

    private void limpiarCamposPropietario() {
        PropietarioNombre.clear();
        PropietarioApellido.clear();
        PropietarioDni.clear();
        PropietarioTelefono.clear();
        PropietarioDireccion.clear();
        PropietarioEmail.clear();
    }

    private void limpiarCamposConsulta() {
        ConsultaFecha.clear();
        ConsultaDuracion.clear();
        ConsultaObservaciones.clear();
        ConsultaMascota.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}