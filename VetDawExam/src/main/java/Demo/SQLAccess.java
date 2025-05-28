package Demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class SQLAccess {

    public static List<Mascota> getMascotas() {
        List<Mascota> mascotas = new LinkedList<>();
        //Sentencia a la base de datos
        String getProducts = "SELECT * FROM Producto";

        try (Connection connection = SQLDataBaseManager.getConnection(); Statement statement = connection.createStatement();
             ResultSet dataSet = statement.executeQuery(getProducts);){
            while (dataSet.next()){
                String pasaporte = dataSet.getString("pasaporte");
                String nombre = dataSet.getString("nombre");
                String fechaNacimiento = dataSet.getString("fecha");
                String peso = dataSet.getString("peso");
                String tipo =  dataSet.getString("tipo");
                Propietario propietario = dataSet.getObject("propietario", Propietario.class);

                Mascota m = new Mascota(pasaporte, nombre, fechaNacimiento, peso, tipo);
                mascotas.add(m);

            }
        }catch (Exception e){
            System.out.println("Error al obtener los productos: " + e.getMessage());
        }
        return mascotas;
    }

    public static int añadirMascota(Mascota mascota){
        List<Mascota> mascotas = new LinkedList<>();
        int response = -1;
        String añadirMascota = "INSERT INTO Mascota (pasaporte, nombre, fechanacimiento, peso, tipo, propietario) VALUES (?,?,?,?,?,?)";

        try(Connection connection = SQLDataBaseManager.getConnection(); PreparedStatement statement = connection.prepareStatement(añadirMascota);) {
            statement.setNString(1, mascota.getPasaporte());
            statement.setNString(2, mascota.getNombre());
            statement.setNString(3, mascota.getFechanacimiento());
            statement.setNString(4, mascota.getPeso());
            statement.setNString(5, mascota.getTipo());
            statement.setObject(6, Propietario.class);

            response = statement.executeUpdate();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return response;
    }

    public static int añadirPropietario(Propietario propietario){
        List<Propietario> propietarios = new LinkedList<>();
        int response = -1;
        String añadirPropietario = "INSERT INTO Propietario (nombre, apellidos, dni, telefono, direccion, email) VALUES (?,?,?,?,?,?)";

        try(Connection connection = SQLDataBaseManager.getConnection(); PreparedStatement statement = connection.prepareStatement(añadirPropietario);) {
            statement.setNString(1, propietario.getNombre());
            statement.setNString(2, propietario.getApellidos());
            statement.setNString(3, propietario.getDni());
            statement.setNString(4, propietario.getTelefono());
            statement.setNString(5, propietario.getDireccion());
            statement.setNString(6, propietario.getEmail());

            response = statement.executeUpdate();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return response;
    }

    public static int añadirConsulta(Consulta consulta){
        List<Consulta> consultas = new LinkedList<>();
        int response = -1;
        String añadirConsulta = "INSERT INTO Consulta (fecha, duraccion, observaciones, mascota) VALUES (?,?,?,?,)";

        try(Connection connection = SQLDataBaseManager.getConnection(); PreparedStatement statement = connection.prepareStatement(añadirConsulta);) {
            statement.setNString(1, consulta.getFecha().toString());
            statement.setInt(2, consulta.getDuracion());
            statement.setNString(3, consulta.getObservaciones());
            statement.setObject(4, Consulta.class);

            response = statement.executeUpdate();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return response;
    }


    public static int eliminarMascota(Mascota mascota){
        int response = -1;
        String eliminarMascota = "DELETE FROM Producto WHERE pasaporte = ?";

        try(Connection connection = SQLDataBaseManager.getConnection(); PreparedStatement statement = connection.prepareStatement(eliminarMascota);) {
            statement.setNString(1, mascota.getPasaporte());
            response = statement.executeUpdate();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return response;
    }


}
