import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner teclado = new Scanner (System.in);
        Producto producto;
        String opcion = "";
        String codigo;
        String nombre;
        int cantidad;
        double precio;

        LinkedList<Producto> productos = new LinkedList<>();
        try (FileReader file1 = new FileReader("./resources/Almacen.csv"); BufferedReader reader = new BufferedReader(file1);) {
            String linea = reader.readLine();
            while(linea != null){
                
                String[] datos = linea.split(",");
                Producto p =  new Producto(datos[0], (datos[1]), Integer.parseInt(datos[2]), Double.parseDouble(datos[3]));
                productos.add(p);
                linea = reader.readLine();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        do { 
            System.out.println("1. Crear producto");
            System.out.println("2. Mostrar productos existentes");
            System.out.println("3. Eliminar producto por código");
            System.out.println("4. Guardar productos en el fichero");
            System.out.println("5. Salir");
            teclado = new Scanner(System.in);
            opcion = teclado.nextLine();

            switch (opcion) {
                case "1":
                    try {
                        System.out.println("Ingrese el codigo del producto");
                        codigo = teclado.nextLine();
                        System.out.println("Ingrese el nombre del producto");
                        nombre = teclado.nextLine();
                        System.out.println("Ingrese la cantidad del producto");
                        cantidad = teclado.nextInt();
                        System.out.println("Indique el precio del producto");
                        precio = teclado.nextDouble();
                        Producto p = new Producto(codigo, nombre, cantidad, precio);
                        productos.add(p);
                   
                    } catch (InputMismatchException e) {
                        System.out.println("Haz introducido un punto, ingresa una coma, vuelve a crear el producto por favor");
                    }
                    break;

                case "2":
                    System.out.println("Estos son los productos");
                    for (Producto linea : productos) {
                        System.out.println(linea);
                    }
                    break;

                case "3":
                    System.out.println("Ingresa el codigo del producto");
                    codigo = teclado.nextLine();
                    productoEliminado(productos, codigo);
                    break;

                case "4":
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("./resources/Almacen.csv", false))) {
                    for (Producto p : productos) {
                        writer.write(p.getCodigo() + "," + p.getNombre() + "," + p.getCantidad() + "," + p.getPrecio());
                        writer.newLine();
                    }
                    System.out.println("Productos guardados correctamente.");
                } catch (IOException e) {
                    System.out.println("Error al guardar los productos: " + e.getMessage());
                }
                break;

                case "5":
                    System.out.println("Saliendo del programa, HASTA PRONTO");
                    break;
                default:
                System.out.println("ERROR, inserte el numero correctamente");
                    
            }
        } while (!opcion.equals("5"));

    }

    public static boolean productoEliminado(LinkedList<Producto> productos, String codigo) {
        Iterator<Producto> iterator = productos.iterator();
        while (iterator.hasNext()) {
            Producto producto = iterator.next();
            if (producto.getCodigo().equals(codigo)) {
                iterator.remove();
                System.out.println("El producto se ha eliminado correctamente.");
                return true;
            }
        }
        System.out.println("Producto no encontrado.");
        return false;
    }
    
}