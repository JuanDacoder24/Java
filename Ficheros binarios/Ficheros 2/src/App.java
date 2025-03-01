
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class App {

    private static final String FILE_PATH = "./resources/Almacen.dat";

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String opcion;
        String codigo;
        String nombre;
        int cantidad;
        double precio;

        LinkedList<Producto> productos = cargarProductos();

        do {
            System.out.println("1. Crear producto");
            System.out.println("2. Mostrar productos existentes");
            System.out.println("3. Eliminar producto por código");
            System.out.println("4. Guardar productos en el fichero");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = teclado.nextLine();

            switch (opcion) {
                case "1":
                    try {
                        System.out.print("Ingrese el código del producto: ");
                        codigo = teclado.nextLine();
                        System.out.print("Ingrese el nombre del producto: ");
                        nombre = teclado.nextLine();
                        System.out.print("Ingrese la cantidad del producto: ");
                        while (!teclado.hasNextInt()) {
                            System.out.println("Por favor, ingrese un número entero válido.");
                            teclado.next();
                        }
                        cantidad = teclado.nextInt();
                        System.out.print("Indique el precio del producto: ");
                        while (!teclado.hasNextDouble()) {
                            System.out.println("Por favor, ingrese un número válido para el precio.");
                            teclado.next();
                        }
                        precio = teclado.nextDouble();
                        teclado.nextLine(); 
                        productos.add(new Producto(codigo, nombre, cantidad, precio));
                        System.out.println("Producto agregado exitosamente.");
                    } catch (InputMismatchException e) {
                        System.out.println("Error en la entrada de datos. Intente de nuevo.");
                        teclado.nextLine();
                    }
                    break;

                case "2":
                    if (productos.isEmpty()) {
                        System.out.println("No hay productos registrados.");
                    } else {
                        System.out.println("Lista de productos:");
                        for (Producto p : productos) {
                            System.out.println(p);
                        }
                    }
                    break;

                case "3":
                    System.out.print("Ingrese el código del producto a eliminar: ");
                    codigo = teclado.nextLine();
                    boolean eliminado = productos.removeIf(p -> p.getCodigo().equals(codigo));
                    if (eliminado) {
                        System.out.println("Producto eliminado correctamente.");
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                    break;

                case "4":
                    guardarProductos(productos);
                    System.out.println("Productos guardados correctamente.");
                    break;

                case "5":
                    System.out.println("Saliendo del programa. ¡Hasta pronto!");
                    break;

                default:
                    System.out.println("ERROR: Inserte un número válido.");
            }
        } while (!opcion.equals("5"));
    }

    private static LinkedList<Producto> cargarProductos() {
        LinkedList<Producto> productos = new LinkedList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            productos = (LinkedList<Producto>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo, iniciando nuevo almacenamiento.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar productos: " + e.getMessage());
        }
        return productos;
    }

    private static void guardarProductos(LinkedList<Producto> productos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(productos);
        } catch (IOException e) {
            System.out.println("Error al guardar productos: " + e.getMessage());
        }
    }
    
}