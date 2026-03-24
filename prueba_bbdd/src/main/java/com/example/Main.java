package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String opcion = "";

        //esto es para verificar que se conecta a la base de datos correctamente
        System.out.println("Probando conexion a la base de datos..." + SqlDataManager.getConnection());

        do { 
            System.out.println("1. Mostrar todos los Productos en el Inventario.");
            System.out.println("2. Buscar producto por referencia.");
            System.out.println("3. Buscar productos por tipo.");
            System.out.println("4. Buscar producto por cantidad.");
            System.out.println("5. Insertar un nuevo producto (no permitir referencias repetidas).");
            System.out.println("6. Eliminar Producto por referencia.");
            System.out.println("7. Actualizar producto (descripción, cantidad, precio, descuento, AplicarDto).");
            System.out.println("8. Insertar un nuevo tipo de producto.");
            System.out.println("9. Salir.");

            sc = new Scanner(System.in);
            opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    System.out.println("Mostrando todos los productos en el inventario...");
                    System.out.println(SQLAccessAlmacen.getProducts());
                    break;

                case "2":
                    System.out.println("Inserte la referencia del producto que desea buscar");
                    String refBuscar = sc.nextLine();
                    System.out.println(SQLAccessAlmacen.getProductByReferencia(refBuscar));
                    break;

                case "3":
                    System.out.println("Inserte el ID del tipo de producto que desea buscar");
                    int tipoBuscar = sc.nextInt();
                    System.out.println(SQLAccessAlmacen.getProductosByTipo(tipoBuscar));
                    break;

                case "4":
                    System.out.println("Inserte la cantidad del producto que desea buscar");
                    int cantidadBuscada = sc.nextInt();
                    System.out.println(SQLAccessAlmacen.getProductosByCantidad(cantidadBuscada));
                    break;

                case "5": {
                    System.out.println("Insertando un nuevo producto...");
                    System.out.print("Referencia: ");
                    String referencia = sc.nextLine();

                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Descripcion: ");
                    String descripcion = sc.nextLine();

                    System.out.print("Tipo ID: ");
                    int tipoId = sc.nextInt();
                    
                    System.out.print("Nombre del tipo: ");
                    String nombreTipo = sc.nextLine();
                    sc.nextLine();

                    System.out.print("Cantidad: ");
                    int cantidad = sc.nextInt();

                    System.out.print("Precio: ");
                    double precio = sc.nextDouble();

                    System.out.print("Descuento: ");
                    int descuento = sc.nextInt();

                    System.out.print("Aplicar descuento (true/false): ");
                    boolean aplicarDto = sc.nextBoolean();
                    sc.nextLine(); 

                    Tipo tipo = new Tipo(tipoId, nombreTipo);
                    Producto producto = new Producto(referencia, nombre, descripcion, tipo, cantidad, precio, descuento, 21, aplicarDto);
                    SQLAccessAlmacen.insertProduct(producto);
                    break;
                }

                case "6": {
                    System.out.print("Referencia del producto a eliminar: ");
                    String refEliminar = sc.nextLine();
                    SQLAccessAlmacen.deleteProductByReferencia(refEliminar);
                    break;
                }

                case "7": {
                    System.out.print("ID del producto a actualizar: ");
                    int idActualizar = sc.nextInt();

                    System.out.print("Referencia: ");
                    String refActualizar = sc.nextLine();

                    System.out.print("Nombre: ");
                    String nombreActualizar = sc.nextLine();
                    sc.nextLine();

                    System.out.print("Descripcion: ");
                    String descActualizar = sc.nextLine();

                    System.out.print("Tipo ID: ");
                    int tipoIdActualizar = sc.nextInt();
                    
                    System.out.print("Nombre del tipo: ");
                    String nombreTipoActualizar = sc.nextLine();

                    System.out.print("Cantidad: ");
                    int cantActualizar = sc.nextInt();

                    System.out.print("Precio: ");
                    double precioActualizar = sc.nextDouble();

                    System.out.print("Descuento: ");
                    int descuentoActualizar = sc.nextInt();

                    System.out.print("Aplicar descuento (true/false): ");
                    boolean aplicarDtoActualizar = sc.nextBoolean();
                    sc.nextLine(); 

                    Tipo tipoActualizar = new Tipo(tipoIdActualizar, nombreTipoActualizar);
                    Producto productoActualizar = new Producto(idActualizar, refActualizar, nombreActualizar, descActualizar, tipoActualizar, cantActualizar, precioActualizar, descuentoActualizar, 21, aplicarDtoActualizar);
                    SQLAccessAlmacen.updateProduct(productoActualizar);
                    break;
                }

                case "8": {
                    System.out.print("Codigo del nuevo tipo: ");
                    String codigoTipo = sc.nextLine();
                    System.out.print("Descripcion del tipo: ");
                    String descTipo = sc.nextLine();

                    System.out.println("Nuevo tipo: " + codigoTipo + " - " + descTipo);
                    break;
                }
                    
                case "9":
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
            
        } while (!opcion.equalsIgnoreCase("9"));

        sc.close();
    }
}
