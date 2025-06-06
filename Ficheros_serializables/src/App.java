
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        Libro libro;
        String opcion = "";

        String isbn;
        String titulo;
        String autor;
        String fechaPublicacion;

        LinkedList<Libro> libros = new LinkedList<>();

        try (FileOutputStream fileOut = new FileOutputStream("./Resources/Libros.dat");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(libros);

        } catch (IOException e) {
            e.printStackTrace();
        }

        do {
            System.out.println("1. Crear Libro y registrarlo en la Biblioteca (ISBN único)");
            System.out.println("2. Mostrar Libros existentes por (ISBN, titulo, Autor, Fecha)");
            System.out.println("3. Eliminar Libro por ISBN");
            System.out.println("4. Guardar Libros en el fichero");
            System.out.println("5. Guardar y Salir");
            teclado = new Scanner(System.in);
            opcion = teclado.nextLine();

            switch (opcion) {
                case "1":
                do { 
                    System.out.println("Empieza insertando el isbn");
                    isbn = teclado.nextLine();
                } while (!isbnUnico(libros, isbn));

                System.out.println("Ingresa el titulo");
                titulo = teclado.nextLine();
                System.out.println("Ingresa el nombre del autor");
                autor = teclado.nextLine();
                System.out.println("Ingresa la fecha de publicacion");
                fechaPublicacion = teclado.nextLine();
                libro = new Libro(isbn, titulo, autor, fechaPublicacion);
                libros.add(libro);
                    break;

                case "2":
                System.out.println("Estos son los libros existentes");
                    for (Libro linea : libros) {
                        System.out.println(linea);
                    }
                    break;

                case "3":
                System.out.println("Ingresa el isbn");
                isbn = teclado.nextLine();
                libroEliminado(libros, isbn);
                    break;

                case "4":
                try (ObjectOutputStream buffer = new ObjectOutputStream(new FileOutputStream("Libros.dat",false))) {
                    buffer.writeObject(libros);
                    System.out.println("Libros guardados correctamente.");
                } catch (IOException e) {
                    System.out.println("Error al guardar: " + e.getMessage());
                }
                    break;

                case "5":
                    System.out.println("Guardando y saliendo del programa");
                    break;
                default:
                    System.out.println("Inserta el numero de nuevo");
            }
        } while (!opcion.equals("5"));

    }
    public static boolean isbnUnico(LinkedList<Libro> libros, String isbn){
        for (Libro isbnUnico : libros) {
            if(isbnUnico.getIsbn().equals(isbn)){
                System.out.println("El isbn ya existe: " + isbn + ", Inserta otro por favor");
            }
        }
        return true;
    }

    public static boolean libroEliminado(LinkedList<Libro> libros, String isbn) {
        Iterator<Libro> iterator = libros.iterator();
        while (iterator.hasNext()) {
            Libro Libro = iterator.next();
            if (Libro.getIsbn().equals(isbn)) {
                iterator.remove();
                System.out.println("El Libro se ha eliminado correctamente.");
                return true;
            }
        }
        System.out.println("Libro no encontrado.");
        return false;
    }

    
}
