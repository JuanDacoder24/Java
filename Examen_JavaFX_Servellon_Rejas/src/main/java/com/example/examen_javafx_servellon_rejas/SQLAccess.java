package com.example.examen_javafx_servellon_rejas;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.util.ArrayList;

public class SQLAccess {

    public static void añadirMascota(Mascota mascota) throws SQLException {
        String sql = "INSERT INTO mascota (Pasaporte, Nombre, Peso, FechaNacimiento, Propietario_dni, Tipo_idTipo) VALUES (?,?,?,?,?,?)";

        try(Connection connection = SQLManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, mascota.getPasaporte());
            ps.setString(2, mascota.getNombre());
            ps.setDouble(3, mascota.getPeso());
            ps.setTimestamp(4, Timestamp.valueOf(mascota.getFechaNacimiento()));
            ps.setString(5, mascota.getPropietario().getDni());
            ps.setInt(6, mascota.getTipo().getIdTipo());

            ps.executeUpdate();
        }
    }

    public ObservableList<Tipo> cargarTiposConsulta() throws SQLException {
        String sql = "SELECT idTipo, Tipo FROM Tipo";
        ObservableList<Tipo> tipos = FXCollections.observableArrayList();

        try (Connection con = SQLManager.getConnection();
             Statement statement = con.createStatement()) {

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                tipos.add(new  Tipo(rs.getInt("idTipo"), rs.getString("Tipo")));
            }
        }
        return tipos;
    }

    public Propietario buscarPropietarioPorDNI(String dni) throws SQLException {
        String sql = "SELECT * FROM Propietario WHERE dni = ?";

        try (Connection connection = SQLManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Propietario p = new Propietario();
                p.setDni(rs.getString("dni"));
                p.setNombre(rs.getString("Nombre"));
                p.setApellido(rs.getString("Apellido"));
                p.setTelefono(rs.getString("Telefono"));
                p.setDirecion(rs.getString("Direcion"));
                p.setEmail(rs.getString("Email"));
                return p;
            } else {
                return null;
            }
        }
    }

    public static int añadirPropietario(Propietario propietario) {
        int res = -1;

        String sql = "INSERT INTO Propietario (dni, Nombre, Apellido, Telefono, Direcion, Email) VALUES (?,?,?,?,?,?)";

        try (Connection connection = SQLManager.getConnection(); java.sql.PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, propietario.getDni());
            ps.setString(2, propietario.getNombre());
            ps.setString(3, propietario.getApellido());
            ps.setString(4, propietario.getTelefono());
            ps.setString(5, propietario.getDirecion());
            ps.setString(6, propietario.getEmail());

            res = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }

        return res;
    }


    public static void añadirConsulta(Consulta consulta) throws SQLException {
        String sql = "INSERT INTO Consulta (Fecha, Duracion, Observaciones, Mascota_Pasaporte, Mascota_Propietario_dni) VALUES (?,?,?,?,?)";

        try (Connection connection = SQLManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setTimestamp(1, Timestamp.valueOf(consulta.getFecha()));
            ps.setInt(2, consulta.getDuracion());
            ps.setString(3, consulta.getObservaciones());
            ps.setString(4, consulta.getMascota().getPasaporte());
            ps.setString(5, consulta.getMascota().getPropietario().getDni());

            ps.executeUpdate();
        }
    }

    public Mascota buscarMascotaPorPasaporte(String pasaporte) throws SQLException {
        String sql = "SELECT * FROM Mascota WHERE Pasaporte = ?";

        try (Connection connection = SQLManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, pasaporte);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Propietario propietario = buscarPropietarioPorDNI(rs.getString("Propietario_dni"));

                Tipo tipo = new Tipo(rs.getInt("Tipo_idTipo"), null);

                Mascota m = new Mascota();
                m.setPasaporte(rs.getString("Pasaporte"));
                m.setNombre(rs.getString("Nombre"));
                m.setPeso(rs.getDouble("Peso"));
                m.setFechaNacimiento(rs.getTimestamp("FechaNacimiento").toLocalDateTime());
                m.setPropietario(propietario);
                m.setTipo(tipo);
                return m;
            } else {
                return null;
            }
        }
    }

    public ObservableList<Mascota> cargarMascotas() throws SQLException {
        String sql = "SELECT * FROM Mascota";
        ObservableList<Mascota> mascotas = FXCollections.observableArrayList();

        try (Connection connection = SQLManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Propietario propietario = buscarPropietarioPorDNI(rs.getString("Propietario_dni"));
                Tipo tipo = new Tipo(rs.getInt("Tipo_idTipo"), null);

                Mascota m = new Mascota();
                m.setPasaporte(rs.getString("Pasaporte"));
                m.setNombre(rs.getString("Nombre"));
                m.setPeso(rs.getDouble("Peso"));
                m.setFechaNacimiento(rs.getTimestamp("FechaNacimiento").toLocalDateTime());
                m.setPropietario(propietario);
                m.setTipo(tipo);
                mascotas.add(m);
            }
        }
        return mascotas;
    }


    public static void actualizarMascota(Mascota mascota) throws SQLException {
        String sql = "UPDATE Mascota SET Nombre = ?, Peso = ? WHERE Pasaporte = ?";

        try (Connection connection = SQLManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, mascota.getNombre());
            ps.setDouble(2, mascota.getPeso());
            ps.setString(3, mascota.getPasaporte());

            ps.executeUpdate();
        }
    }

    public static void exportarMascotas(ObservableList<Mascota> mascotas) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("mascotas.dat"))) {
            oos.writeObject(new ArrayList<>(mascotas));
        }
    }








}

