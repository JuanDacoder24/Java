package com.example.examen_recuperacion;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SQLAccesVetDaw {

    //Obtener todos los tipos de animales de la base de datos
    public List<Tipo> getAllTypes() {
        List<Tipo> tipos = new LinkedList<>();

        String getTipos = "SELECT idTipo , Tipo FROM tipo";

        try (Connection connection = SQLDataBaseManager.getConnection(); Statement statement = connection.createStatement();
             ResultSet dataSet = statement.executeQuery(getTipos);) {
            while (dataSet.next()) {
                Tipo t = new Tipo(dataSet.getNString(2), dataSet.getInt(1));
                tipos.add(t);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return tipos;

    }

    //Obtener todos los propietarios de la base de datos
    public List<Propietario> getAllPropietarios() {
        List<Propietario> propietarios = new LinkedList<>();

        String getPropietarios = "SELECT * FROM propietario";

        try (Connection connection = SQLDataBaseManager.getConnection(); Statement statement = connection.createStatement();
             ResultSet dataSet = statement.executeQuery(getPropietarios);) {
            while (dataSet.next()) {
                Propietario p = new Propietario(
                        dataSet.getNString(1),
                        dataSet.getNString(2),
                        dataSet.getNString(3),
                        dataSet.getNString(4),
                        dataSet.getNString(5),
                        dataSet.getNString(6)
                );
                propietarios.add(p);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return propietarios;

    }

    public List<Mascota> getAllMascotas() {
        List<Mascota> mascotas = new LinkedList<>();

        String getMascotas = "SELECT m.*, t.tipo, p.* FROM vetdaw.mascota m " +
                "join vetdaw.propietario p on p.dni = m.Propietario_dni " +
                "join vetdaw.tipo t on t.idTipo = m.Tipo_idTipo";

        try (Connection connection = SQLDataBaseManager.getConnection(); Statement statement = connection.createStatement();
             ResultSet dataSet = statement.executeQuery(getMascotas);) {
            while (dataSet.next()) {
                Mascota m = new Mascota(
                        dataSet.getNString(2), //nombre
                        dataSet.getNString(1), //pasaporte
                        dataSet.getDate(4).toLocalDate(), //Fecha nacimiento
                        dataSet.getDouble(3), //peso

                        new Propietario(dataSet.getNString(8),
                                dataSet.getNString(9), dataSet.getNString(10),
                                dataSet.getNString(11), dataSet.getNString(12),
                                dataSet.getNString(13)),

                        new Tipo(dataSet.getNString(7), dataSet.getInt(6))

                );
                mascotas.add(m);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return mascotas;
    }

    public Mascota getMascotasByPasaporte(String pasaporte) {
        List<Mascota> mascotas = new LinkedList<>();

        Mascota mascota = null;

        String getMascotasPasaporte = "SELECT m.*, t.tipo, p.* from mascota m " +
                "join propietario p on p.dni = m.Propietario_dni " +
                "join tipo t on t.idTipo = m.Tipo_idTipo " +
                "where pasaporte = ?";

        try (Connection connection = SQLDataBaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(getMascotasPasaporte);) {
            statement.setString(1, pasaporte);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Mascota m = new Mascota(
                        resultSet.getNString(2), //nombre
                        resultSet.getNString(1), //pasaporte
                        resultSet.getDate(4).toLocalDate(), //Fecha nacimiento
                        resultSet.getDouble(3), //peso

                        new Propietario(resultSet.getNString(8),
                                resultSet.getNString(9), resultSet.getNString(10),
                                resultSet.getNString(11), resultSet.getNString(12),
                                resultSet.getNString(13)),

                        new Tipo(resultSet.getNString(7), resultSet.getInt(6))
                );
                mascota = m;
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return mascota;
    }

    public int getPropietarioNuevo(Propietario propietario) {
        int response = -1;
        String getPropietarioNuevo = "INSERT INTO Propietario (dni, Nombre, Apellido, Telefono, Direcion, Email) VALUES  (?, ?, ?, ?, ?, ?)";
        try (Connection connection = SQLDataBaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getPropietarioNuevo)) {
            preparedStatement.setString(1, propietario.getDni());
            preparedStatement.setString(2, propietario.getNombre());
            preparedStatement.setString(3, propietario.getApellido());
            preparedStatement.setString(4, propietario.getTelefono());
            preparedStatement.setString(5, propietario.getDireccion());
            preparedStatement.setString(6, propietario.getEmail());

            response = preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return response;
    }

    public int getMascotaNueva(Mascota mascota) {
        int response = -1;
        String getPropietarioNuevo = "INSERT INTO Mascota VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = SQLDataBaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getPropietarioNuevo)) {
            preparedStatement.setString(1, mascota.getPasaporte());
            preparedStatement.setString(2, mascota.getNombre());
            preparedStatement.setDouble(3, mascota.getPeso());
            preparedStatement.setDate(4, Date.valueOf(mascota.getFechaNacimiento()));
            preparedStatement.setString(5, mascota.getPropietario().getDni());
            preparedStatement.setString(6, mascota.getTipo().getTipo());

            response = preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return response;
    }

    public int getNuevaConsulta (Consulta consulta) {
        int response = -1;
        String getPropietarioNuevo = "INSERT INTO Consulta (Fecha, Duracion, Observaciones, Mascota_Pasaporte, Mascota_Propietario_dni) VALUES  (?, ?, ?, ?, ?)";
        try (Connection connection = SQLDataBaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getPropietarioNuevo)) {
            preparedStatement.setDate(1, consulta.getFecha());
            preparedStatement.setInt(2, consulta.getDuracion());
            preparedStatement.setString(3, consulta.getObservaciones());
            preparedStatement.setString(4, consulta.getMascota().getPasaporte());
            preparedStatement.setString(5, consulta.getMascota().getPropietario().getDni());


            response = preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return response;
    }

}
