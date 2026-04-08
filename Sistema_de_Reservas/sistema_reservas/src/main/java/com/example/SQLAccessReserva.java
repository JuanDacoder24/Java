package com.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class SQLAccessReserva {

    public static List<Reservas> getReservas(){
        List<Reservas> reservas = new LinkedList<>();

        String sqlReservas = "SELECT * FROM reservas";

        try(Connection connection = SQLDataManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlReservas)){

            while (resultSet.next()) { 
                int id = resultSet.getInt(1);
                int usuario_id = resultSet.getInt(2);
                int instalacion_id = resultSet.getInt(3);
                LocalDate fecha = resultSet.getDate(4).toLocalDate();
                LocalDateTime hora_inicio = resultSet.getTimestamp(5).toLocalDateTime();
                LocalDateTime hora_fin = resultSet.getTimestamp(6).toLocalDateTime();
                Estado estado = Estado.valueOf(resultSet.getString(7));

                Reservas reserva = new Reservas(id, usuario_id, instalacion_id, fecha, hora_inicio, hora_fin, estado);
                reservas.add(reserva);
            }

        } catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }

        return reservas;
    }

    public static List<Instalaciones> getPistas(){
        List<Instalaciones> pistas = new LinkedList<>();

        String sqlPistas = "SELECT * FROM instalaciones";

        try(Connection connection = SQLDataManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlPistas)){

            while (resultSet.next()) { 
                int id = resultSet.getInt(1);
                String nombre = resultSet.getString(2);
                double precioHora = resultSet.getDouble(3);
                Tipo tipo = Tipo.valueOf(resultSet.getString(4));

                Instalaciones pista = new Instalaciones(id, nombre, precioHora, tipo);
                pistas.add(pista);
            }

        } catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }

        return pistas;
    }

    public static List<Usuarios> getUsuarios(){
        List <Usuarios> usuarios = new LinkedList<>();

        String sqlUsuarios = "SELECT * FROM usuarios";

        try(Connection connection = SQLDataManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlUsuarios)){

            while (resultSet.next()) { 
                int id = resultSet.getInt(1);
                String nombre = resultSet.getString(2);
                String email = resultSet.getString(3);
                String password = resultSet.getString(4);

                Usuarios usuario = new Usuarios(id, nombre, email, password);
                usuarios.add(usuario);
            }

        } catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }

        return usuarios;
    }

    public static int registarUsuario(Usuarios usuario){
        int res = -1;

        String sqlRegistrarUsuario = "INSERT INTO usuarios (nombre, email, password, rol) VALUES (?, ?, ?, ?)";

        try(Connection connection = SQLDataManager.getConnection();
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sqlRegistrarUsuario)){

            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.setString(3, usuario.getPassword());

            res = preparedStatement.executeUpdate();

        } catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }

        return res;
    }

    private static int reservarPista(Reservas reserva) { 
    int res = -1;
    
    String sqlReservarPista = "INSERT INTO reservas (usuario_id, instalacion_id, fecha, hora_inicio, hora_fin, estado) VALUES (?, ?, ?, ?, ?, ?)";
    
    try(Connection connection = SQLDataManager.getConnection();
        java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sqlReservarPista)){
        
        preparedStatement.setInt(1, reserva.getUsuarioId()); 
        preparedStatement.setInt(2, reserva.getInstalacionId());
        preparedStatement.setDate(3, java.sql.Date.valueOf(reserva.getFecha()));
        preparedStatement.setTimestamp(4, java.sql.Timestamp.valueOf(reserva.getHoraInicio()));
        preparedStatement.setTimestamp(5, java.sql.Timestamp.valueOf(reserva.getHoraFin()));
        preparedStatement.setString(6, reserva.getEstado().name());
        
        res = preparedStatement.executeUpdate();
        
    } catch(SQLException e){
        System.out.println("SQLException: " + e.getMessage());
    }
    
    return res;
}

    public static boolean isPistaDisponible(int instalacionId, LocalDate fecha, LocalDateTime horaInicio, LocalDateTime horaFin) {
    String sqlCheckDisponibilidad = "SELECT COUNT(*) FROM reservas WHERE instalacion_id = ? AND fecha = ? AND estado != 'CANCELADA' AND ((hora_inicio <= ? AND hora_fin > ?) OR (hora_inicio < ? AND hora_fin >= ?) OR (hora_inicio >= ? AND hora_fin <= ?))";
    
    try(Connection connection = SQLDataManager.getConnection();
        java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sqlCheckDisponibilidad)) {
        
        // ESTOS SON LOS PARAMETROS PARA VER SI HAY SOLAPAMIENTO DE HORARIOS
        preparedStatement.setInt(1, instalacionId);
        preparedStatement.setDate(2, java.sql.Date.valueOf(fecha));
        preparedStatement.setTimestamp(3, java.sql.Timestamp.valueOf(horaInicio));
        preparedStatement.setTimestamp(4, java.sql.Timestamp.valueOf(horaInicio));
        preparedStatement.setTimestamp(5, java.sql.Timestamp.valueOf(horaFin));
        preparedStatement.setTimestamp(6, java.sql.Timestamp.valueOf(horaFin));
        preparedStatement.setTimestamp(7, java.sql.Timestamp.valueOf(horaInicio));
        preparedStatement.setTimestamp(8, java.sql.Timestamp.valueOf(horaFin));
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        if (resultSet.next()) {
            int reservasExistentes = resultSet.getInt(1);
            return reservasExistentes == 0;
        }
        
    } catch(SQLException e) {
        System.out.println("SQLException al verificar disponibilidad: " + e.getMessage());
    }
    
    return false;
}

public static int reservarPistaConVerificacion(Reservas reserva) {

    if (!isPistaDisponible(reserva.getInstalacionId(), reserva.getFecha(), 
        reserva.getHoraInicio(), reserva.getHoraFin())) {
        System.out.println("La pista no está disponible en el horario solicitado");
        return -2; 
    }
    
    return reservarPista(reserva);
}

    public static int updatePista(Instalaciones pista){
        int elements = -1;

        String sqlUpdatePista = "UPDATE instalaciones SET nombre = ?, precio_hora = ?, tipo = ? WHERE id = ?";

        try(Connection connection = SQLDataManager.getConnection();
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdatePista)){

            preparedStatement.setString(1, pista.getNombre());
            preparedStatement.setDouble(2, pista.getPrecioHora());
            preparedStatement.setString(3, pista.getTipo().name());
            
            preparedStatement.setInt(4, pista.getId());

            elements = preparedStatement.executeUpdate();

        } catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }

        return elements;
    }

    public static int deleteReservaById(int id){
        int elements = -1;

        String sqlDeleteReserva = "DELETE FROM reservas WHERE id = ?";

        try(Connection connection = SQLDataManager.getConnection();
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sqlDeleteReserva)){

            preparedStatement.setInt(1, id);

            elements = preparedStatement.executeUpdate();

        } catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }

        return elements;
    }



}
