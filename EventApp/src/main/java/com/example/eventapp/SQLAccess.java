package com.example.eventapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SQLAccess {

    // 1A. Verificar si el código interno ya existe para mostrar una advertencia
    public boolean existeCodigoEvento(Connection conn, String codigoInterno) throws SQLException {
        String sql = "SELECT COUNT(*) FROM evento WHERE codigo_interno = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, codigoInterno);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Retorna true si ya existe
                }
            }
        }
        return false;
    }

    // 1B. Insertar el nuevo evento en el sistema
    public boolean registrarEvento(Connection conn, String codigo, String titulo, LocalDateTime fechaHora, int aforo, double precio, String organizadorDni, String tipoEvento) throws SQLException {
        String sql = "INSERT INTO evento (codigo_interno, titulo, fecha_hora, aforo_maximo, precio_entrada, organizador_dni, tipo_evento) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, codigo);
            ps.setString(2, titulo);
            ps.setTimestamp(3, Timestamp.valueOf(fechaHora));
            ps.setInt(4, aforo);
            ps.setDouble(5, precio);
            ps.setString(6, organizadorDni); // Ahora guardamos directamente el DNI
            ps.setString(7, tipoEvento);     // Ahora guardamos directamente el texto del tipo

            return ps.executeUpdate() > 0;
        }
    }

    // 2A. Validar si el DNI o el Email ya están registrados
    public boolean existeAsistente(Connection conn, String dni, String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM asistente WHERE dni = ? OR email = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dni);
            ps.setString(2, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    // 2B. Registrar asistente (por defecto con rol 'asistente')
    public boolean registrarAsistente(Connection conn, String dni, String nombre, String apellidos, String telefono, String email) throws SQLException {
        String sql = "INSERT INTO asistente (dni, nombre, apellidos, telefono, email, tipo) VALUES (?, ?, ?, ?, ?, 'asistente')";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dni);
            ps.setString(2, nombre);
            ps.setString(3, apellidos);
            ps.setString(4, telefono);
            ps.setString(5, email);

            return ps.executeUpdate() > 0;
        }
    }

    // 3A. Verificar si el evento cuenta con aforo disponible
    public boolean hayAforoDisponible(Connection conn, String codigoEvento) throws SQLException {
        String sql = "SELECT (e.aforo_maximo - COUNT(en.numero_entrada)) AS plazas_libres " +
                "FROM evento e " +
                "LEFT JOIN entrada en ON e.codigo_interno = en.evento_codigo " +
                "WHERE e.codigo_interno = ? " +
                "GROUP BY e.codigo_interno";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, codigoEvento);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("plazas_libres") > 0;
                }
            }
        }
        return false; // Si no encuentra el evento, asumimos que no hay aforo
    }

    // 3B. Registrar la venta de la entrada con su número único autogenerado
    public boolean venderEntrada(Connection conn, String numeroEntrada, String asistenteDni, String codigoEvento) throws SQLException {
        String sql = "INSERT INTO entrada (numero_entrada, fecha_compra, asistente_dni, evento_codigo) VALUES (?, NOW(), ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, numeroEntrada); // Número único generado desde tu lógica de Java
            ps.setString(2, asistenteDni);
            ps.setString(3, codigoEvento);

            return ps.executeUpdate() > 0;
        }
    }

    // 4A. Buscar un evento concreto por su código interno
    public Evento buscarPorCodigo(Connection conn, String codigo) throws SQLException {
        // 1. Añadimos el String sql que faltaba
        String sql = "SELECT * FROM evento WHERE codigo_interno = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // 2. Extraemos los campos del evento
                    String cod = rs.getString("codigo_interno");
                    String titulo = rs.getString("titulo");
                    LocalDateTime fecha = rs.getTimestamp("fecha_hora").toLocalDateTime();
                    int aforo = rs.getInt("aforo_maximo");
                    double precio = rs.getDouble("precio_entrada");
                    String organizadorDni = rs.getString("organizador_dni");
                    String tipoEvento = rs.getString("tipo_evento");

                    // 3. Como tu clase Asistente necesita un objeto Tipo, creamos el Tipo ficticio/temporal
                    // (Opcional: Ponemos 0 en el idTipo porque lo quitamos de la base de datos)
                    Tipo tipoObj = new Tipo(tipoEvento);
                    // 4. Creamos el objeto Asistente (organizador) usando su DNI y el tipo anterior
                    Asistente organizadorObj = new Asistente("", "", organizadorDni, "", "", tipoObj);

                    // 5. Devolvemos el Evento construyendo todo el árbol de objetos que pide tu constructor
                    return new Evento(cod, titulo, fecha, aforo, precio, organizadorObj);
                }
            }
        }
        return null;
    }

    // 4B. Obtener la lista completa de eventos si falla la búsqueda directa
    public ObservableList<Evento> obtenerTodosEventos() throws SQLException {
        String sql = "SELECT codigo_interno, titulo, fecha_hora, aforo_maximo, precio_entrada, organizador_dni, tipo_evento FROM evento";
        ObservableList<Evento> eventos = FXCollections.observableArrayList();

        try (Connection connection = SQLManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) { // Al meter el ResultSet en el try-with-resources se cierra solo automáticamente

            while (rs.next()) {
                // 1. Extraemos los datos primitivos de la tabla 'evento'
                String codigo = rs.getString("codigo_interno");
                String titulo = rs.getString("titulo");

                // Convertimos el DATETIME de MySQL a LocalDateTime de Java
                java.sql.Timestamp timestamp = rs.getTimestamp("fecha_hora");
                java.time.LocalDateTime fechaHora = (timestamp != null) ? timestamp.toLocalDateTime() : null;

                int aforoMaximo = rs.getInt("aforo_maximo");
                double precioEntrada = rs.getDouble("precio_entrada");

                // 2. Extraemos las cadenas que hacen de claves foráneas
                String organizadorDni = rs.getString("organizador_dni");
                String nombreTipo = rs.getString("tipo_evento");

                // 3. ¡Construimos la jerarquía en cascada!
                // Creamos el objeto Tipo usando tu clase limpia con solo el String
                Tipo tipoObjeto = new Tipo(nombreTipo);

                // Creamos el Asistente organizador pasándole su DNI y el tipo que acabamos de crear
                // Dejamos los campos de texto vacíos ("") porque no los necesitamos para listar en la tabla
                Asistente organizador = new Asistente("", "", organizadorDni, "", "", tipoObjeto);

                // 4. Instanciamos el Evento completo pasando el organizador anidado
                Evento evento = new Evento(codigo, titulo, fechaHora, aforoMaximo, precioEntrada, organizador);

                // 5. Lo añadimos a la lista de JavaFX
                eventos.add(evento);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la lista de eventos: " + e.getMessage());
            throw e; // Volvemos a lanzar la excepción para que el controlador pueda manejarla o mostrar una alerta
        }

        return eventos;
    }

    // 4C. Modificar única y exclusivamente el aforo y el precio del entrada
    public boolean modificarAforoYPrecio(Connection conn, String codigoInterno, int nuevoAforo, double nuevoPrecio) throws SQLException {
        String sql = "UPDATE evento SET aforo_maximo = ?, precio_entrada = ? WHERE codigo_interno = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, nuevoAforo);
            ps.setDouble(2, nuevoPrecio);
            ps.setString(3, codigoInterno);

            return ps.executeUpdate() > 0;
        }
    }

    public List<Asistente> obtenerAsistentesPorEvento(Connection conn, String codigoEvento) throws SQLException {
        String sql = "SELECT a.* FROM asistente a " +
                "JOIN entrada e ON a.dni = e.asistente_dni " +
                "WHERE e.evento_codigo = ?";

        List<Asistente> asistentes = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, codigoEvento);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // 1. Extraemos los Strings individuales de la tabla asistente
                    String dni = rs.getString("dni");
                    String nombre = rs.getString("nombre");
                    String apellidos = rs.getString("apellidos");
                    String telefono = rs.getString("telefono");
                    String email = rs.getString("email");
                    String tipoEnumTexto = rs.getString("tipo"); // Esto recupera 'asistente' o 'organizador'

                    // 2. Creamos el objeto Tipo que requiere el constructor de Asistente
                    // (Como borraste el idTipo, tu clase Tipo ahora solo recibe el String en su constructor)
                    Tipo tipoObjeto = new Tipo(tipoEnumTexto);

                    // 3. Instanciamos el Asistente en el ORDEN EXACTO de tu constructor:
                    // (nombre, apellidos, dni, telefono, email, tipo)
                    Asistente asistente = new Asistente(nombre, apellidos, dni, telefono, email, tipoObjeto);

                    // 4. Lo añadimos a la lista
                    asistentes.add(asistente);
                }
            }
        }
        return asistentes;
    }
}