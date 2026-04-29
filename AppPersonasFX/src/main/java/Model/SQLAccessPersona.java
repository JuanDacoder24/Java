package Model;

import Configuration.SQLDataBaseManager;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class SQLAccessPersona {

    public static List<Persona> getAllpersonas(){
        List<Persona> personas = new LinkedList<>();

        //Sentencia SQL
        String sqlpersonas = "SELECT * FROM person";

        try(Connection connection = SQLDataBaseManager.getConnection();
            Statement stament = connection.createStatement();
            ResultSet resultSets = stament.executeQuery(sqlpersonas)) {

            while (resultSets.next()) {
                String dni = resultSets.getNString(1);
                String name = resultSets.getNString(2);
                String surname = resultSets.getNString(3);
                String email = resultSets.getNString(4);
                int age = resultSets.getInt(5);
                String phone = resultSets.getNString(6);

                //Uso de patron builder
                Persona p = Persona.builder()
                        .dni(dni)
                        .name(name)
                        .surname(surname)
                        .email(email)
                        .age(age)
                        .phone(phone)
                        .build();

                personas.add(p);
            }

        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }

        return personas;
    }

    public static List<Persona> getpersonasByNameContains(String name){
        List<Persona> personas = new LinkedList<>();

        //Sentencia SQL
        String sqlpersonas = "SELECT * FROM person where name like ?";

        try(Connection connection = SQLDataBaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlpersonas)){

            statement.setString(1, name+"%");
            ResultSet resultSets = statement.executeQuery();

            while (resultSets.next()) {
                String dni = resultSets.getNString(1);
                String nameDB = resultSets.getNString(2);
                String surname = resultSets.getNString(3);
                String email = resultSets.getNString(4);
                int age = resultSets.getInt(5);
                String phone = resultSets.getNString(6);

                //Uso de patron builder
                Persona p = Persona.builder()
                        .dni(dni)
                        .name(nameDB)
                        .surname(surname)
                        .email(email)
                        .age(age)
                        .phone(phone)
                        .build();

                personas.add(p);
            }

        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }

        return personas;
    }



    public static Persona getPersonaByDni(String dni){
        Persona p = null;
        String sqlpersonas = "SELECT * FROM person WHERE dni = ?";

        try(Connection connection = SQLDataBaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlpersonas)){
            statement.setString(1, dni);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String dniDB = resultSet.getNString(1);
                String name = resultSet.getNString(2);
                String surname = resultSet.getNString(3);
                String email = resultSet.getNString(4);
                int age = resultSet.getInt(5);
                String phone = resultSet.getNString(6);

                //Uso de patron builder
                p = Persona.builder()
                        .dni(dniDB)
                        .name(name)
                        .surname(surname)
                        .email(email)
                        .age(age)
                        .phone(phone)
                        .build();

            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        return p;
    }

    public static Persona getPersonaByEmail(String email){
        Persona p = null;
        String sqlpersonas = "SELECT * FROM person WHERE email = ?";

        try(Connection connection = SQLDataBaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlpersonas)){
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String dni = resultSet.getNString(1);
                String name = resultSet.getNString(2);
                String surname = resultSet.getNString(3);
                String emailDB = resultSet.getNString(4);
                int age = resultSet.getInt(5);
                String phone = resultSet.getNString(6);

                //Uso de patron builder
                p = Persona.builder()
                        .dni(dni)
                        .name(name)
                        .surname(surname)
                        .email(emailDB)
                        .age(age)
                        .phone(phone)
                        .build();

            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        return p;
    }

    public static boolean createPersona(Persona persona){
        boolean result = false;
        String sqlInsertpersonas = "INSERT INTO person (dni, name, surname, email, age, phone) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try(Connection connection = SQLDataBaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlInsertpersonas)){
            statement.setString(1, persona.getDni());
            statement.setString(2, persona.getName());
            statement.setString(3, persona.getSurname());
            statement.setString(4, persona.getEmail());
            statement.setInt(5, persona.getAge());
            statement.setString(6, persona.getPhone());

            statement.execute();
            result = true;

        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        return result;
    }

    public static boolean updatePersona(Persona persona){
        boolean result = false;
        String sqlUpdatePersona = "UPDATE person set name = ?, surname = ?," +
                "email = ?, age = ?, phone = ?  where dni = ?";

        try(Connection connection = SQLDataBaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlUpdatePersona))
        {
            statement.setString(1, persona.getName());
            statement.setString(2, persona.getSurname());
            statement.setString(3, persona.getEmail());
            statement.setInt(4, persona.getAge());
            statement.setString(5, persona.getPhone());

            //para el where
            statement.setString(6, persona.getDni());
            statement.executeUpdate();
            result = true;

        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        return result;
    }

    public static boolean deletePersonaByDNI(String dni){
        boolean result = false;
        String sqlDeletepersonas = "DELETE FROM person WHERE dni = ?";

        try(Connection connection = SQLDataBaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlDeletepersonas)){
            statement.setString(1, dni);
            statement.execute();
            result = true;

        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        return result;
    }

}
