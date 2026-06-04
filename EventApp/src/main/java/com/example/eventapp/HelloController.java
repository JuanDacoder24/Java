package com.example.eventapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class HelloController {

    // Instancia de tu capa de acceso a datos
    private final SQLAccess database = new SQLAccess();

    // Contenedores principales de vistas
    @FXML private VBox PanelBotones;
    @FXML private VBox PanelEvento;
    @FXML private VBox PanelEntradas;
    @FXML private VBox PanelRegistrarAsistente;
    @FXML private VBox PanelBuscarEvento;

    // Formulario: Registrar Evento
    @FXML private TextField codigoInterno;
    @FXML private TextField titulo;
    @FXML private DatePicker selectorFecha;
    @FXML private ComboBox<String> comboHora;
    @FXML private ComboBox<String> comboTipoEvento;
    @FXML private TextField aforoMaximo;
    @FXML private TextField precioEntada;

    // Formulario: Vender Entrada
    @FXML private TextField campoNumeroEntrada;
    @FXML private TextField campoDniAsistente;
    @FXML private ComboBox<Evento> comboEvento;

    // Formulario: Registrar Asistente
    @FXML private TextField campoNombreAsistente;
    @FXML private TextField campoApellidosAsistente;
    @FXML private TextField campoDniNuevo;
    @FXML private TextField campoTelefonoAsistente;
    @FXML private TextField campoEmailAsistente;

    // Formulario: Buscar y Modificar
    @FXML private ListView<Evento> listaEventos;
    @FXML private TextField campoBuscarCodigo;
    @FXML private TextField campoAforo;
    @FXML private TextField campoPrecio;

    @FXML
    public void initialize() {
        // Inicialización de la navegación superpuesta
        ocultarTodosLosPaneles();
        PanelBotones.setVisible(true);

        // Cargar las horas estándar en el ComboBox
        comboHora.setItems(FXCollections.observableArrayList(
                "08:00", "10:00", "12:00", "16:00", "18:00", "20:00", "22:00"
        ));

        // Carga inicial de datos de la base de datos real
        refrescarDatosDeListas();

        // Escuchador del ListView para cargar datos automáticamente al hacer clic en un evento
        listaEventos.getSelectionModel().selectedItemProperty().addListener((obs, viejo, nuevo) -> {
            if (nuevo != null) {
                campoBuscarCodigo.setText(nuevo.getCodigoInterno());
                campoAforo.setText(String.valueOf(nuevo.getAforoMaximo()));
                campoPrecio.setText(String.valueOf(nuevo.getPrecioEntrada()));
            }
        });

        // Cargar tipos de eventos reales desde la BD al arrancar la pantalla
        try (Connection conn = SQLManager.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT tipo FROM tipo")) {

            ObservableList<String> tiposDeBd = FXCollections.observableArrayList();
            while (rs.next()) {
                tiposDeBd.add(rs.getString("tipo"));
            }
            comboTipoEvento.setItems(tiposDeBd);

        } catch (SQLException e) {
            System.err.println("No se pudieron cargar los tipos en el combo: " + e.getMessage());
        }
    }

    // =========================================================================
    // NAVEGACIÓN
    // =========================================================================
    @FXML void BotonRegistrarEvento(MouseEvent event) { mostrarPanel(PanelEvento); }
    @FXML void BotonRegistrarEntradas(MouseEvent event) { mostrarPanel(PanelEntradas); }
    @FXML void BotonRegistrarAsistente(MouseEvent event) { mostrarPanel(PanelRegistrarAsistente); }
    @FXML void BotonListaEventos(MouseEvent event) { mostrarPanel(PanelBuscarEvento); }
    @FXML void BotonVolver(MouseEvent event) { mostrarPanel(PanelBotones); }

    private void mostrarPanel(VBox panel) {
        ocultarTodosLosPaneles();
        panel.setVisible(true);
    }

    private void ocultarTodosLosPaneles() {
        PanelBotones.setVisible(false);
        PanelEvento.setVisible(false);
        PanelEntradas.setVisible(false);
        PanelRegistrarAsistente.setVisible(false);
        PanelBuscarEvento.setVisible(false);
    }

    // =========================================================================
    // LÓGICA DE NEGOCIO (Llamadas reales a SQLAccess)
    // =========================================================================

    /**
     * 1B. Registrar Evento en MySQL usando validación de código duplicado (1A)
     */
    @FXML
    void RegistrarEvento(MouseEvent event) {
        // Validación previa de que ha seleccionado algo en el desplegable
        if (comboTipoEvento.getValue() == null) {
            mostrarAlerta("Campo incompleto", "Por favor, seleccione un tipo de evento de la lista.");
            return;
        }

        // Extraemos el String directamente del ComboBox
        String txtTipo = comboTipoEvento.getValue();
        String codigo = codigoInterno.getText().trim().toUpperCase();
        String txtTitulo = titulo.getText().trim();

        // Creamos una conexión limpia usando un bloque try-with-resources
        try (Connection conn = SQLManager.getConnection()) {
            int aforo = Integer.parseInt(aforoMaximo.getText().trim());
            double precio = Double.parseDouble(precioEntada.getText().trim());

            LocalDate fecha = selectorFecha.getValue();
            LocalTime hora = LocalTime.parse(comboHora.getValue());
            LocalDateTime fechaHora = LocalDateTime.of(fecha, hora);

            // 1A. Validación de código existente
            if (database.existeCodigoEvento(conn, codigo)) {
                mostrarAlerta("Código Duplicado", "El código de evento '" + codigo + "' ya está registrado.");
                return;
            }

            // Usamos un DNI de organizador que ya exista en tu base de datos
            String organizadorDni = "44556677D";

            // 1B. Inserción real
            boolean exito = database.registrarEvento(conn, codigo, txtTitulo, fechaHora, aforo, precio, organizadorDni, txtTipo);

            if (exito) {
                mostrarAlerta("Éxito", "Evento guardado correctamente en la Base de Datos.");
                refrescarDatosDeListas(); // Sincroniza la UI
                BotonVolver(event);
            }

        } catch (NumberFormatException e) {
            mostrarAlerta("Formato Erróneo", "El aforo debe ser entero y el precio decimal.");
        } catch (SQLException e) {
            mostrarAlerta("Error de Base de Datos", "Error al registrar el evento: " + e.getMessage());
        }
    }

    /**
     * 2B. Registrar Asistente en MySQL usando validación de DNI/Email (2A)
     */
    @FXML
    void RegistrarAsistente(MouseEvent event) {
        if (campoDniNuevo.getText().isEmpty() || campoNombreAsistente.getText().isEmpty() || campoEmailAsistente.getText().isEmpty()) {
            mostrarAlerta("Campos vacíos", "El DNI, el Nombre y el Correo Electrónico son obligatorios.");
            return;
        }

        String dni = campoDniNuevo.getText().trim().toUpperCase();
        String nombre = campoNombreAsistente.getText().trim();
        String apellidos = campoApellidosAsistente.getText().trim();
        String telefono = campoTelefonoAsistente.getText().trim();
        String email = campoEmailAsistente.getText().trim();

        try (Connection conn = SQLManager.getConnection()) {
            // 2A. Validar si ya existe el asistente por DNI o Email
            if (database.existeAsistente(conn, dni, email)) {
                mostrarAlerta("Registro Duplicado", "El DNI o el Email introducido ya pertenecen a un asistente registrado.");
                return;
            }

            // 2B. Insertar el asistente
            boolean exito = database.registrarAsistente(conn, dni, nombre, apellidos, telefono, email);
            if (exito) {
                mostrarAlerta("Éxito", "Asistente registrado de forma correcta.");
                BotonVolver(event);
            }

        } catch (SQLException e) {
            mostrarAlerta("Error de Base de Datos", "Error al registrar asistente: " + e.getMessage());
        }
    }

    /**
     * 3B. Vender Entrada validando si hay aforo disponible (3A)
     */
    @FXML
    void RegistrarEntrada(MouseEvent event) {
        if (campoNumeroEntrada.getText().isEmpty() || campoDniAsistente.getText().isEmpty() || comboEvento.getValue() == null) {
            mostrarAlerta("Campos vacíos", "Debe completar el número de entrada, el DNI y seleccionar el evento.");
            return;
        }

        String numEntrada = campoNumeroEntrada.getText().trim();
        String dni = campoDniAsistente.getText().trim().toUpperCase();

        // =========================================================================
        // 🌟 LA SOLUCIÓN INMUNE A ERRORES AQUÍ 🌟
        // =========================================================================
        // En lugar de confiar en .getCodigoInterno(), leemos el String visual.
        // Si sale "Evento{codigoInterno=CINE004, ...}", limpiamos el texto para quedarnos solo con el código.
        String textoCombo = comboEvento.getValue().toString();
        String codigoEvento = "";

        if (textoCombo.contains("codigoInterno=")) {
            // Si el combo sigue tirando el churro feo por defecto:
            // Cortamos lo que hay entre "codigoInterno=" y la primera coma ","
            int inicio = textoCombo.indexOf("codigoInterno=") + "codigoInterno=".length();
            int fin = textoCombo.indexOf(",", inicio);
            codigoEvento = textoCombo.substring(inicio, fin).trim();
        } else if (textoCombo.contains("[") && textoCombo.contains("]")) {
            // Si mutó al formato limpio "[CINE004] - Titulo"
            int inicio = textoCombo.indexOf("[") + 1;
            int fin = textoCombo.indexOf("]");
            codigoEvento = textoCombo.substring(inicio, fin).trim();
        } else {
            // Por si acaso todo falla, usamos el método directo
            codigoEvento = comboEvento.getValue().getCodigoInterno().trim();
        }

        // Hacemos un último control de seguridad: si es más largo de 10 caracteres, lo podamos para salvar la BD
        if (codigoEvento.length() > 10) {
            codigoEvento = codigoEvento.substring(0, 8).replaceAll("[^A-Za-z0-9]", "");
        }
        // =========================================================================

        // Ahora continuamos con tu bloque try-catch tal y como lo tenías:
        try (Connection conn = SQLManager.getConnection()) {

            // Validación de aforo que corregimos antes
            if (!database.hayAforoDisponible(conn, codigoEvento)) {
                mostrarAlerta("Aforo Completo", "Lo sentimos, no quedan plazas libres disponibles para este evento.");
                return;
            }

            // Envío blindado a la base de datos (Ahora 'codigoEvento' medirá 7 u 8 caracteres como máximo)
            boolean exito = database.venderEntrada(conn, numEntrada, dni, codigoEvento);
            if (exito) {
                mostrarAlerta("Venta Completada", "Entrada '" + numEntrada + "' registrada con éxito.");
                BotonVolver(event);
            }

        } catch (SQLException e) {
            mostrarAlerta("Error SQL", "No se pudo procesar la venta de la entrada: " + e.getMessage());
        }
    }

    /**
     * 4A. Buscar Evento por su código único en la barra de búsqueda
     */
    @FXML
    void BuscarEvento(MouseEvent event) {
        String codigoBusqueda = campoBuscarCodigo.getText().trim().toUpperCase();

        if (codigoBusqueda.isEmpty()) {
            mostrarAlerta("Campo vacío", "Introduce un código para iniciar la búsqueda.");
            return;
        }

        try (Connection conn = SQLManager.getConnection()) {
            // 4A. Ejecución del SELECT filtrado
            Evento encontrado = database.buscarPorCodigo(conn, codigoBusqueda);

            if (encontrado != null) {
                // Selecciona el evento en la lista visual automáticamente
                listaEventos.getSelectionModel().select(encontrado);

                // Rellena los inputs de modificación
                campoAforo.setText(String.valueOf(encontrado.getAforoMaximo()));
                campoPrecio.setText(String.valueOf(encontrado.getPrecioEntrada()));

                mostrarAlerta("Evento Encontrado", "Se ha seleccionado: " + encontrado.getTitulo());
            } else {
                mostrarAlerta("Sin Resultados", "No existe ningún evento registrado bajo el código: " + codigoBusqueda);
            }

        } catch (SQLException e) {
            mostrarAlerta("Error de búsqueda", "Error al consultar código: " + e.getMessage());
        }
    }

    /**
     * 4C. Modificar el aforo y el precio del evento seleccionado
     */
    @FXML
    void ActualizarEvento(MouseEvent event) {
        // Comprobamos que haya un ítem seleccionado de la lista
        Evento seleccionado = listaEventos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta("Sin selección", "Por favor, seleccione un evento de la lista superior para modificarlo.");
            return;
        }

        try (Connection conn = SQLManager.getConnection()) {
            int nuevoAforo = Integer.parseInt(campoAforo.getText().trim());
            double nuevoPrecio = Double.parseDouble(campoPrecio.getText().trim());

            // 4C. Ejecutamos el UPDATE en la Base de Datos
            boolean exito = database.modificarAforoYPrecio(conn, seleccionado.getCodigoInterno(), nuevoAforo, nuevoPrecio);

            if (exito) {
                mostrarAlerta("Actualizado", "Cambios guardados con éxito en la base de datos.");

                // Forzar actualización visual en caliente en la UI de JavaFX
                refrescarDatosDeListas();
            }

        } catch (NumberFormatException e) {
            mostrarAlerta("Formato inválido", "El nuevo aforo y precio deben ser valores numéricos correctos.");
        } catch (SQLException e) {
            mostrarAlerta("Fallo en Actualización", "No se pudieron guardar los cambios: " + e.getMessage());
        }
    }

    @FXML
    private void ExportarDatos() {
        try {
            ObservableList<Evento> mascotas = database.obtenerTodosEventos();
            if (mascotas.isEmpty()) {
                showAlert("Aviso", "No hay datos de mascotas para exportar.");
                return;
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("eventos.dat"))) {
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

    /**
     * 4B. Obtener y volcar todos los eventos reales de la BD en la interfaz
     */
    private void refrescarDatosDeListas() {
        try {
            // 4B. Llamada al método que incluye su propia gestión interna de conexión
            ObservableList<Evento> listaBd = database.obtenerTodosEventos();

            if (listaBd != null) {
                comboEvento.setItems(listaBd);
                listaEventos.setItems(listaBd);
            }
        } catch (SQLException e) {
            mostrarAlerta("Fallo de sincronización", "No se pudieron actualizar las listas desde MySQL: " + e.getMessage());
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}