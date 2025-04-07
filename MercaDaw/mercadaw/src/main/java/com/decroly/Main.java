package com.decroly;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        SQLAccessMercaDaw miMercaDaw = new SQLAccessMercaDaw();
        List<Producto> listas = miMercaDaw.mostrarLista();
        
        Scanner teclado = new Scanner(System.in);
        String opcion = "";

        do { 
            System.out.println("****BIENVENIDO A MERCADAW****");
            System.out.println("1. Mostrar todos los Productos en el Inventario.");
            System.out.println("2. Buscar producto por referencia.");
            System.out.println("3. Buscar productos por tipo.");
            System.out.println("4. Buscar producto por cantidad.");
            System.out.println("5. Insertar un nuevo producto (no permitir referencias repetidas)");
            System.out.println("6. Eliminar Producto por referencia.");
            System.out.println("7. Actualizar producto");
            System.out.println("8. Insertar un nuevo tipo de producto.");
            System.out.println("9. Salir");
            teclado = new Scanner(System.in);
            opcion = teclado.nextLine();

            switch (opcion) {
                case "1":
                    for (Producto mn : listas) {
                        System.out.println(mn);
                    }
                    break;
                
                case "2":
                    System.out.println("Introduce la referencia del producto:");
                    String referencia = teclado.nextLine(); 
                    Producto producto = miMercaDaw.buscarProductoPorRef(referencia); 
                    if (producto != null) {
                        System.out.println("Producto encontrado: " + producto.getNombre() + " - " + producto.getDescripcion() + " - " + producto.getCantidad() + " unidades disponibles.");
                    } else {
                        System.out.println("No se encontró ningún producto con esa referencia.");
                    }
                    break;
                
                case "3":
                    System.out.println("Introduce el tipo de producto:");
                    String tipo = teclado.nextLine();
                    Producto productoPorTipo = miMercaDaw.buscarProductoPorTipo(tipo);
                    if (productoPorTipo != null) {
                        System.out.println("Producto encontrado: " + productoPorTipo.getNombre());
                    } else {
                        System.out.println("No se encontró ningún producto con ese tipo.");
                    }
                    break;
                
                case "4":
                    System.out.println("Introduce la cantidad del producto:");
                    int cantidad = teclado.nextInt();
                    Producto productoPorCantidad = miMercaDaw.buscarProductoPorCant(cantidad);

                    if (productoPorCantidad != null) {
                        System.out.println("Producto encontrado: " + productoPorCantidad.getNombre());
                    } else {
                        System.out.println("No se encontró ningún producto con esa cantidad.");
                    }
                    break;

                case "5":
                    try {
                        
                        System.out.println("Introduce la referencia del nuevo producto (formato AAA000):");
                        String ref = teclado.nextLine();
                        if (validRef(ref)) {
                            System.out.println("Introduce el nombre del nuevo producto:");
                            String nombre = teclado.nextLine();
                            System.out.println("Introduce la descripción del nuevo producto:");
                            String nuevaDescripcion = teclado.nextLine();
                            System.out.println("Introduce la cantidad del nuevo producto:");
                            int nuevaCantidad = teclado.nextInt();
                            System.out.println("Introduce el precio del nuevo producto:");
                            double nuevoPrecio = teclado.nextDouble();
                            System.out.println("Introduce el descuento del nuevo producto:");
                            int nuevoDescuento = teclado.nextInt();
                            System.out.println("Introduce el IVA del nuevo producto:");
                            int nuevoIva = teclado.nextInt();
                            System.out.println("¿Deseas aplicar descuento a este producto? (true/false):");
                            boolean aplicarDto = teclado.nextBoolean();

                            Producto nuevProducto = new Producto(0, ref, nombre, nuevaDescripcion, nuevaCantidad, nuevoPrecio, nuevoDescuento, nuevoIva, aplicarDto, 0, null);
                            miMercaDaw.insertarProducto(nuevProducto);
                            System.out.println("Producto insertado con éxito.");
                        } else {
                            System.out.println("Referencia no válida. Debe seguir el formato AAA000.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Error: " + e.getMessage());
                        teclado.nextLine();
                    } catch (Exception e) {
                        System.out.println("Error al insertar el producto: " + e.getMessage());
                    }
                    break;
                
                case "6":
                    System.out.println("Introduce la referencia del producto a eliminar:");
                    String refEliminar = teclado.nextLine();
                    if (validRef(refEliminar)) {
                        Producto productoEliminar = miMercaDaw.buscarProductoPorRef(refEliminar);
                        if (productoEliminar != null) {
                            miMercaDaw.eliminarByRef(refEliminar);
                            System.out.println("Producto eliminado con éxito.");
                        } else {
                            System.out.println("No se encontró ningún producto con esa referencia.");
                        }
                    } else {
                        System.out.println("Referencia no válida. Debe seguir el formato AAA000.");
                    }
                    break;

                case "7":
                    System.out.println("Introduce la referencia del producto a actualizar:");
                    String refActualizar = teclado.nextLine();
                    if (validRef(refActualizar)) {
                        Producto productoActualizar = miMercaDaw.buscarProductoPorRef(refActualizar);
                        if (productoActualizar != null) {
                            System.out.println("Producto encontrado: " + productoActualizar.getNombre());

                        } else {
                            System.out.println("No se encontró ningún producto con esa referencia.");
                        }
                    } else {
                        System.out.println("Referencia no válida. Debe seguir el formato AAA000.");
                    }
                    break;
                
                case "8":

                    break;

                case "9":
                System.out.println("Saliendo del programa, HASTA PRONTO");
                    break;

                default:
                    System.out.println("Por favor pulsar la tecla correcta");
            }

        } while (!opcion.equalsIgnoreCase("9"));

    }
    public static boolean validRef(String referencia) {
        Pattern pat = Pattern.compile("[A-Z]{3}[0-9]{3}");
        Matcher mat = pat.matcher(referencia);
        return mat.matches();
    }
}