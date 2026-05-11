package Demo;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class SQLAccess {

    public int insertMascota(Mascota mascota) {
        int response = -1;
        String getMascota = "INSERT INTO Mascota (Pasaporte, Nombre, Peso, FechaNacimiento, Propietario_dni, Tipo_idTipo) " +
                "VALUES (?, ?, ?, ?, ?, ?);";

        try (Connection connection = SQLDatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(getMascota)) {

            statement.setString(1, mascota.getPasaporte());
            statement.setString(2, mascota.getNombre());
            statement.setDouble(3, mascota.getPeso());
            statement.setDate(4, java.sql.Date.valueOf(mascota.getFechaNacimiento()));
            statement.setString(5, mascota.getPropietario().getDni());
            statement.setString(6, mascota.getTipo());


            response = statement.executeUpdate();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return response;
    }

    public int insertPropietario(Propietario propietario) {
        int response = -1;
        String getPropietario = "INSERT INTO Propietario (dni, Nombre, Apellido, Telefono, Direcion, Email) " +
                "VALUES (?, ?, ?, ?, ?, ?);";

        try (Connection connection = SQLDatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(getPropietario)) {

            statement.setString(1, propietario.getDni());
            statement.setString(2, propietario.getNombre());
            statement.setString(3, propietario.getApellidos());
            statement.setString(4, propietario.getTelefono());
            statement.setString(5, propietario.getDireccion());
            statement.setString(6, propietario.getEmail());


            response = statement.executeUpdate();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return response;
    }

    public int insertConsulta(Consulta consulta) {
        int response = -1;
        String getPropietario = "INSERT INTO Consulta (Fecha, Duracion, Observaciones, Mascota_Pasaporte) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = SQLDatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(getPropietario)) {

            statement.setDate(1, java.sql.Date.valueOf(consulta.getFecha()));
            statement.setInt(2, consulta.getDuracion());
            statement.setString(3, consulta.getObservaciones());
            statement.setString(4, consulta.getMascota().getPasaporte());


            response = statement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return response;
    }

    public static List<Mascota> BuscarMascota(String pasaporte) {
        List<Mascota> mascotas = new LinkedList<>();

        String getMascota = "SELECT * FROM Mascota WHERE Pasaporte = ?";

        try (Connection connection = SQLDatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(getMascota)) {

            statement.setString(1, pasaporte);
            ResultSet dataSet = statement.executeQuery();

            while (dataSet.next()) {
                String pasapoteMascota = dataSet.getNString(1);
                String nombre = dataSet.getString(2);
                double peso = dataSet.getDouble(3);
                LocalDate fechaNacimiento = dataSet.getDate(4).toLocalDate();
                String tipo = dataSet.getString(6);


                Mascota m1 = new Mascota(pasapoteMascota, nombre, fechaNacimiento, peso, tipo);
                mascotas.add(m1);
            }


        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return mascotas;
    }

    public static List<Propietario> getPropietarios() {//METODO PARA MOSTRAR TODOS LOS ARTICULOS
        List<Propietario> propietarios = new LinkedList<>();

        String getArticulos = "SELECT * FROM Propietario ";

        try (Connection connection = SQLDatabaseManager.getConnection(); Statement statement = connection.createStatement();
             ResultSet dataSet = statement.executeQuery(getArticulos);) {

            while (dataSet.next()) {
                String dni = dataSet.getString(1);
                String nombre = dataSet.getNString(2);
                String apellido = dataSet.getNString(3);

                Propietario p1 = new Propietario(nombre, apellido, dni);
                propietarios.add(p1);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return propietarios;
    }

    public static List<Mascota> getMascotas() {
        List<Mascota> mascotas = new LinkedList<>();

        String getMascota = "SELECT * FROM Mascota ";

        try (Connection connection = SQLDatabaseManager.getConnection(); Statement statement = connection.createStatement();
             ResultSet dataSet = statement.executeQuery(getMascota);) {


            while (dataSet.next()) {
                String pasapoteMascota = dataSet.getNString(1);
                String nombre = dataSet.getString(2);
                double peso = dataSet.getDouble(3);
                LocalDate fechaNacimiento = dataSet.getDate(4).toLocalDate();
                String tipo = dataSet.getString(6);


                Mascota m1 = new Mascota(pasapoteMascota, nombre, fechaNacimiento, peso, tipo);
                mascotas.add(m1);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return mascotas;
    }
}
