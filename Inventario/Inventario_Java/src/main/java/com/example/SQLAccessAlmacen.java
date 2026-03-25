package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class SQLAccessAlmacen {

    public static List<Producto> getProductByReferencia(String referencia) {
        List<Producto> productos = new LinkedList<>();

        String sql = "SELECT p.*, t.nombre FROM productos p JOIN tipos t ON p.tipo_id = t.id WHERE p.referencia = ?";

        try (Connection connection = SqlDataManager.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, referencia);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String ref = rs.getString(2);
                String nombre = rs.getString(3);
                String descripcion = rs.getString(4);
                int tipo_id = rs.getInt(5);
                int cantidad = rs.getInt(6);
                double precio = rs.getDouble(7);
                int descuento = rs.getInt(8);
                int iva = rs.getInt(9);
                boolean aplicar_dto = rs.getBoolean(10);
                String tipo_nombre = rs.getString(11);

                Tipo tipo = new Tipo(tipo_id, tipo_nombre);

                productos.add(new Producto(id, ref, nombre, descripcion, tipo, cantidad, precio, descuento, iva,
                        aplicar_dto));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return productos;
    }

    public static List<Producto> getProducts() {
        List<Producto> product = new LinkedList<>();

        // tuve que modificar el tipo de consulta para poder agarrar el objeto tipo
        String sqlProducts = "SELECT p.*, t.nombre FROM productos p JOIN tipos t ON p.tipo_id = t.id";

        try (Connection connection = SqlDataManager.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlProducts)) {

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String referencia = resultSet.getNString(2);
                String nombre = resultSet.getNString(3);
                String descripcion = resultSet.getNString(4);
                int tipo_id = resultSet.getInt(5);
                int cantidad = resultSet.getInt(6);
                double precio = resultSet.getDouble(7);
                int descuento = resultSet.getInt(8);
                int iva = resultSet.getInt(9);
                boolean aplicar_dto = resultSet.getBoolean(10);
                String tipo_nombre = resultSet.getNString(11);

                Tipo tipo = new Tipo(tipo_id, tipo_nombre);

                product.add(new Producto(id, referencia, nombre, descripcion, tipo, cantidad, precio, descuento, iva,
                        aplicar_dto));
            }

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }

        return product;
    }

    public static List<Tipo> getTipos() {
        List<Tipo> tipos = new LinkedList<>();
        String sql = "SELECT id, nombre FROM tipos";
        try (Connection connection = SqlDataManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                tipos.add(new Tipo(id, nombre));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener tipos: " + e.getMessage());
        }
        return tipos;
    }

    public static boolean isTipoIdExistente(int id) {
        List<Tipo> tipos = getTipos();
        for (Tipo tipo : tipos) {
            if (tipo.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public static boolean isTipoNombreExistente(String nombre) {
        List<Tipo> tipos = getTipos();
        for (Tipo tipo : tipos) {
            if (tipo.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    public static List<Producto> getProductosByTipo(String nombreTipo) {
        List<Producto> productos = new LinkedList<>();

        String sql = "SELECT p.*, t.nombre FROM productos p JOIN tipos t ON p.tipo_id = t.id WHERE t.nombre = ?";

        try (Connection connection = SqlDataManager.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, nombreTipo);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String referencia = rs.getString(2);
                String nombre = rs.getString(3);
                String descripcion = rs.getString(4);
                int tipo_id = rs.getInt(5);
                int cantidad = rs.getInt(6);
                double precio = rs.getDouble(7);
                int descuento = rs.getInt(8);
                int iva = rs.getInt(9);
                boolean aplicar_dto = rs.getBoolean(10);
                String tipo_nombre = rs.getString(11);

                Tipo tipo = new Tipo(tipo_id, tipo_nombre);

                productos.add(new Producto(id, referencia, nombre, descripcion, tipo, cantidad, precio, descuento, iva,
                        aplicar_dto));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return productos;
    }

    public static List<Producto> getProductosByCantidad(int cantidadBuscada) {
        List<Producto> productos = new LinkedList<>();

        String sql = "SELECT p.*, t.nombre FROM productos p JOIN tipos t ON p.tipo_id = t.id WHERE p.cantidad = ?";

        try (Connection connection = SqlDataManager.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, cantidadBuscada);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String referencia = rs.getString(2);
                String nombre = rs.getString(3);
                String descripcion = rs.getString(4);
                int tipo_id = rs.getInt(5);
                int cantidad = rs.getInt(6);
                double precio = rs.getDouble(7);
                int descuento = rs.getInt(8);
                int iva = rs.getInt(9);
                boolean aplicar_dto = rs.getBoolean(10);
                String tipo_nombre = rs.getString(11);

                Tipo tipo = new Tipo(tipo_id, tipo_nombre);

                productos.add(new Producto(id, referencia, nombre, descripcion, tipo,
                        cantidad, precio, descuento, iva, aplicar_dto));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return productos;
    }

    public static int deleteProductByReferencia(String referencia) {

        int elements = -1;

        String sqlDeleteProduct = "DELETE FROM productos WHERE referencia = ?";
        try (Connection connection = SqlDataManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlDeleteProduct)) {

            preparedStatement.setString(1, referencia);
            elements = preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println("error al eliminar producto" + e.getMessage());
        }
        return elements;
    }

    public static int insertProduct(Producto producto) {

        int response = -1;

        String sqlInsertProduct = "INSERT INTO productos (referencia, nombre, descripcion, tipo_id, cantidad, precio, descuento, iva, aplicar_dto) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = SqlDataManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertProduct)) {
            preparedStatement.setString(1, producto.getReferencia());
            preparedStatement.setString(2, producto.getNombre());
            preparedStatement.setString(3, producto.getDescripcion());
            preparedStatement.setInt(4, producto.getTipo().getId());
            preparedStatement.setInt(5, producto.getCantidad());
            preparedStatement.setDouble(6, producto.getPrecio());
            preparedStatement.setDouble(7, producto.getDescuento());
            preparedStatement.setDouble(8, producto.getIva());
            preparedStatement.setBoolean(9, producto.isAplicarDto());

            response = preparedStatement.executeUpdate();

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Precio inválido, usa punto o coma para decimales. " + e.getMessage(), e);
            
        } catch (Exception e) {
            System.out.println("error al insertar producto " + e.getMessage());
        }
        return response;
    }

    public static int insertTipo(Tipo tipo) {

        int response = -1;

        String sqlInsertTipo = "INSERT INTO tipos (nombre) VALUES(?)";

        try (Connection connection = SqlDataManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertTipo)) {

            preparedStatement.setString(1, tipo.getNombre());

            response = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("error al insertar tipo" + e.getMessage());
        }
        return response;
    }

    public static int updateProduct(Producto producto) {

        int response = -1;

        String sqlUpdateProduct = "UPDATE productos SET descripcion = ?, cantidad = ?, precio = ?, descuento = ?, aplicar_dto = ? WHERE id = ?";

        try (Connection connection = SqlDataManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdateProduct)) {
            preparedStatement.setString(1, producto.getDescripcion());
            preparedStatement.setInt(2, producto.getCantidad());
            preparedStatement.setDouble(3, producto.getPrecio());
            preparedStatement.setInt(4, producto.getDescuento());
            preparedStatement.setBoolean(5, producto.isAplicarDto());

            preparedStatement.setInt(6, producto.getId());

            response = preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println("error al actualizar producto" + e.getMessage());
        }
        return response;
    }

}
