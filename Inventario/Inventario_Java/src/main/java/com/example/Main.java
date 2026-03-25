package com.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String opcion = "";

        // esto es para verificar que se conecta a la base de datos correctamente
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
                    System.out.println("Inserte el nombre del tipo de producto que desea buscar");
                    String tipoBuscar = sc.nextLine();
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
                    
                    if (isReferenciaExistente(referencia)) {
                        System.out.println("Error: La referencia '" + referencia + "' ya existe. No se puede insertar el producto.");
                        break;
                    }

                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Descripcion: ");
                    String descripcion = sc.nextLine();

                    System.out.println("Tipos disponibles:");
                    List<Tipo> tipos = SQLAccessAlmacen.getTipos();
                    for (Tipo tipo : tipos) {
                        System.out.println("  " + tipo.getId() + " - " + tipo.getNombre());
                    }

                    System.out.print("Tipo ID: ");
                    int tipoId = sc.nextInt();
                    sc.nextLine(); 

                    if (!SQLAccessAlmacen.isTipoIdExistente(tipoId)) {
                        System.out.println("Error: El tipo ID " + tipoId + " no existe. Elija un ID de la lista.");
                        break;
                    }

                    String nombreTipo = "";
                    for (Tipo tipo : tipos) {
                        if (tipo.getId() == tipoId) {
                            nombreTipo = tipo.getNombre();
                            break;
                        }
                    }

                    System.out.print("Cantidad: ");
                    int cantidad = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Precio: ");
                    String precioTexto = sc.nextLine();
                    double precio = Double.parseDouble(precioTexto.replace(",", "."));
                    sc.nextLine();

                    System.out.print("Descuento: ");
                    int descuento = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Aplicar descuento (true/false): ");
                    boolean aplicarDto = sc.nextBoolean();
                    sc.nextLine();

                    Tipo tipo = new Tipo(tipoId, nombreTipo);
                    Producto producto = new Producto(referencia, nombre, descripcion, tipo, cantidad, precio, descuento,
                            21, aplicarDto);
                    SQLAccessAlmacen.insertProduct(producto);
                    break;
                }

                case "6": {
                    System.out.print("Referencia del producto a eliminar: ");
                    String refEliminar = sc.nextLine();
                    if (SQLAccessAlmacen.getProductByReferencia(refEliminar) == null) {
                        System.out.println("No se encontro el producto con la referencia " + refEliminar);
                    } else {
                        SQLAccessAlmacen.deleteProductByReferencia(refEliminar);
                    }
                    break;
                }

                case "7": {
                    System.out.print("ID del producto a actualizar: ");
                    int idActualizar = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Referencia: ");
                    String refActualizar = sc.nextLine();
                    sc.nextLine();

                    System.out.print("Nombre: ");
                    String nombreActualizar = sc.nextLine();
                    sc.nextLine();

                    System.out.print("Descripcion: ");
                    String descActualizar = sc.nextLine();
                    sc.nextLine();

                    System.out.print("Tipo ID: ");
                    int tipoIdActualizar = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nombre del tipo: ");
                    String nombreTipoActualizar = sc.nextLine();
                    sc.nextLine();

                    System.out.print("Cantidad: ");
                    int cantActualizar = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Precio: ");
                    double precioActualizar = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Descuento: ");
                    int descuentoActualizar = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Aplicar descuento (true/false): ");
                    boolean aplicarDtoActualizar = sc.nextBoolean();
                    sc.nextLine();

                    Tipo tipoActualizar = new Tipo(tipoIdActualizar, nombreTipoActualizar);
                    Producto productoActualizar = new Producto(idActualizar, refActualizar, nombreActualizar,
                            descActualizar, tipoActualizar, cantActualizar, precioActualizar, descuentoActualizar, 21,
                            aplicarDtoActualizar);
                    SQLAccessAlmacen.updateProduct(productoActualizar);
                    break;
                }

                case "8": {
                    System.out.println("Tipos existentes:");
                    List<Tipo> tiposExistentes = SQLAccessAlmacen.getTipos();
                    for (Tipo tipo : tiposExistentes) {
                        System.out.println("  " + tipo.getId() + " - " + tipo.getNombre());
                    }

                    System.out.print("ID del nuevo tipo: ");
                    int idTipo = sc.nextInt();
                    sc.nextLine(); 

                    if (SQLAccessAlmacen.isTipoIdExistente(idTipo)) {
                        System.out.println("Error: El ID " + idTipo + " ya existe. No se puede crear el tipo.");
                        break;
                    }

                    System.out.print("Nombre del nuevo tipo: ");
                    String nombreTipo = sc.nextLine();

                    if (SQLAccessAlmacen.isTipoNombreExistente(nombreTipo)) {
                        System.out.println("Error: El nombre '" + nombreTipo + "' ya existe. No se puede crear el tipo.");
                        break;
                    }

                    Tipo nuevoTipo = new Tipo(idTipo, nombreTipo);
                    int resultado = SQLAccessAlmacen.insertTipo(nuevoTipo);
                    if (resultado > 0) {
                        System.out.println("Tipo '" + nombreTipo + "' creado exitosamente.");
                    } else {
                        System.out.println("Error al crear el tipo.");
                    }
                    break;
                }

                case "9":
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opcion no valida");
                    break;
            }

        } while (!opcion.equalsIgnoreCase("9"));

        sc.close();
    }

    private static boolean isReferenciaExistente(String referencia) {
        List<Producto> productos = SQLAccessAlmacen.getProductByReferencia(referencia);
        return productos != null && !productos.isEmpty();
    }
}
