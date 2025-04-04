package com.decroly;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class SQLAccessMercaDaw {

    public Producto buscarProductoPorRef(String referencia) {
        Producto producto = null;
        String sqlStatement = "SELECT * FROM productos WHERE referencia = ?;";
        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlStatement)) {
            statement.setNString(1, referencia); 
    
            try (ResultSet dataSet = statement.executeQuery()) {
                if (dataSet.next()) {
                    int id = dataSet.getInt(1);
                    String ref = dataSet.getNString(2);
                    String nombre = dataSet.getNString(3);
                    String descripcion = dataSet.getNString(4);
                    int cantidad = dataSet.getInt(5);
                    double precio = dataSet.getDouble(6);
                    int descuento = dataSet.getInt(7);
                    int iva = dataSet.getInt(8);
                    boolean aplicarDto = dataSet.getBoolean(9);
                    int idProducto = dataSet.getInt(10);
                    String nombreTipo = dataSet.getNString(11);
    
                    producto = new Producto(id, ref, nombre, descripcion, cantidad, precio, descuento, iva, aplicarDto, idProducto, nombreTipo);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al buscar producto por referencia: " + e.getMessage());
        }
        return producto;
    }

    public List<Producto> mostrarLista(){
        List<Producto> lista = new LinkedList<>();

        String mLista = "select * from productos";
        try (Connection connection = SQLDataManager.getConnection(); Statement statement = connection.createStatement();
        ResultSet dataSet = statement.executeQuery(mLista);) {
            while(dataSet.next()){

                int id = dataSet.getInt(1);
                String referencia = dataSet.getNString(2);
                String nombre = dataSet.getNString(3);
                String descripcion = dataSet.getNString(4);
                int cantidad = dataSet.getInt(5);
                double precio = dataSet.getDouble(6);
                int descuento = dataSet.getInt(7);
                int iva = dataSet.getInt(8);
                boolean aplicarDto = dataSet.getBoolean(9);
                int idProducto = dataSet.getInt(10);
                String nombreTipo = dataSet.getNString(11);
                
                Producto p1 = new Producto(id, referencia, nombre, descripcion, cantidad, precio, descuento, iva, aplicarDto, idProducto, nombreTipo);
                lista.add(p1);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return lista;
    }


    public int insertarProducto(Producto producto){
        int respuesta = -1;
        String sqlStatement = "INSERT INTO productos (id, referencia, nombre, descripcion, cantidad, precio, descuento, iva, aplicarDto, id_tipo)" + "VALUES(?,?,?,?,?,?,?,?,?,?)";
        try (Connection connection = SQLDataManager.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlStatement)) {
            statement.setInt(1, producto.getId());
            statement.setNString(2, producto.getReferencia());
            statement.setNString(3, producto.getNombre());
            statement.setNString(4, producto.getDescripcion());
            statement.setInt(5, producto.getCantidad());
            statement.setDouble(6, producto.getPrecio());
            statement.setInt(7, producto.getDescuento());
            statement.setInt(8, producto.getIva());
            statement.setBoolean(9, producto.isAplicarDto());

            respuesta = statement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return respuesta;
    }

    public int editarProducto(Producto producto){
        int respuesta = -1;
        String sqlStatement = "update productos set id = ?, referencia = ?, nombre = ?, descripcion = ?, cantidad = ?, precio = ?, descuento = ?, iva = ?, id_tipo = ?;";
        try (Connection connection = SQLDataManager.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlStatement);) {
            statement.setInt(1, producto.getId());
            statement.setNString(2, producto.getReferencia());
            statement.setNString(3, producto.getNombre());
            statement.setNString(4, producto.getDescripcion());
            statement.setInt(5, producto.getCantidad());
            statement.setDouble(6, producto.getPrecio());
            statement.setInt(7, producto.getDescuento());
            statement.setInt(8, producto.getIva());
            statement.setBoolean(9, producto.isAplicarDto());

            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return respuesta;
    }
    
    public int eliminarByRef(String referencia) {
        int elementos = 0; 
        String sqlStatement = "DELETE FROM productos WHERE referencia = ?;";
        try (Connection connection = SQLDataManager.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(sqlStatement)) {
            statement.setNString(2, referencia); 
    
            elementos += statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }
        return elementos;
    }

}
