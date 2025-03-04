import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GestionVideoDaw {
    public static void main(String[] args){
        
        Scanner teclado = new Scanner (System.in);
        System.out.println("***Bienvenido a VideoDaw***");
        String opcion = "";
        Cliente cliente;
        Pelicula pelicula;
        VideoDaw videoD = null;

        do { 
            teclado = new Scanner (System.in);
            System.out.println("1 - Crear y registrar VideoClub en la franquicia");
            System.out.println("2 - Registrar película en videoclub");
            System.out.println("3 - Crear y registrar cliente en videoclub");
            System.out.println("4 - Alquilar película");
            System.out.println("5 - Devolver película");
            System.out.println("6 - Dar de baja cliente");
            System.out.println("7 - Dar de baja película");
            System.out.println("8 - Salir");
            opcion = teclado.nextLine();

            switch (opcion) {
                case "1":
                    System.out.print("Introduzca el CIF para el registro del VideoClub: ");
                    String cif;
                    do {
                        cif = teclado.nextLine().toUpperCase();
                        if (setValidCif(cif)) {
                            System.out.println("CIF válido.");
                        } else {
                            System.out.println("CIF incorrecto, inténtelo otra vez.");
                        }
                    } while (!setValidCif(cif));

                    System.out.print("Introduzca la dirección del local: ");
                    String direccion = teclado.nextLine();
                    System.out.print("Introduzca la fecha de alta (DD/MM/AAAA): ");
                    String fechaAlta = teclado.nextLine();

                    videoD = new VideoDaw(cif, direccion, fechaAlta);
                    System.out.println("Videoclub registrado con éxito.");
                    break;

                    case "2":
                    if (videoD == null) {
                        System.out.println("Debe registrar primero el videoclub antes de añadir películas.");
                        break;
                    }
                    System.out.print("Introduzca el título de la película: ");
                    String tituloPelicula = teclado.nextLine();
                    Pelicula nuevaPelicula = new Pelicula(null, null, false, tituloPelicula, null, null, null);
                    if (videoD.registrarPelicula(nuevaPelicula)) {
                        System.out.println("Película registrada correctamente.");
                    } else {
                        System.out.println("Error: La película ya está registrada.");
                    }
                    break;
                
                case "3":
                    if (videoD == null) {
                        System.out.println("Debe registrar primero el videoclub antes de añadir clientes.");
                        break;
                    }
                    String dni;
                    do {
                        System.out.print("Introduzca el DNI del cliente: ");
                        dni = teclado.nextLine().toUpperCase();
                        if (setValidDni(dni)) {
                            System.out.println("DNI válido.");
                        } else {
                            System.out.println("DNI incorrecto, inténtelo otra vez.");
                        }
                    } while (!setValidDni(dni));
                
                    System.out.print("Introduzca el nombre del cliente: ");
                    String nombreCliente = teclado.nextLine();
                
                    Cliente nuevoCliente = new Cliente(dni, nombreCliente, null, null, null, null, null);
                    
                    if (videoD.registrarCliente(nuevoCliente)) {
                        System.out.println("Cliente registrado correctamente.");
                    } else {
                        System.out.println("Error: El cliente ya está registrado.");
                    }
                    break;
                
                case "4":
                    if (videoD == null) {
                        System.out.println("Debe registrar primero el videoclub antes de alquilar películas.");
                        break;
                    }
                    System.out.println("Clientes disponibles:");
                    videoD.mostrarClientesRegistrados();
                    System.out.print("Ingrese el nombre del cliente que alquilará: ");
                    String clienteAlquila = teclado.nextLine();
                
                    System.out.println("Películas disponibles:");
                    videoD.mostrarPeliculasRegistradas();
                    System.out.print("Ingrese el título de la película a alquilar: ");
                    String peliculaAlquilar = teclado.nextLine();
                
                    Cliente clienteEncontrado = buscarCliente(videoD, clienteAlquila);
                    Pelicula peliculaEncontrada = buscarPelicula(videoD, peliculaAlquilar);
                
                    if (clienteEncontrado != null && peliculaEncontrada != null) {
                        videoD.alquilarPelicula(peliculaEncontrada, clienteEncontrado);
                        System.out.println("Película alquilada correctamente.");
                    } else {
                        System.out.println("Cliente o película no encontrados.");
                    }
                    break;
                
                case "5":
                    if (videoD == null) {
                        System.out.println("Debe registrar primero el videoclub antes de devolver películas.");
                        break;
                    }
                    System.out.print("Ingrese el nombre del cliente que devuelve la película: ");
                    String clienteDevuelve = teclado.nextLine();
                    System.out.print("Ingrese el título de la película a devolver: ");
                    String peliculaDevolver = teclado.nextLine();
                
                    Cliente clienteDev = buscarCliente(videoD, clienteDevuelve);
                    Pelicula peliculaDev = buscarPelicula(videoD, peliculaDevolver);
                
                    if (clienteDev != null && peliculaDev != null) {
                        videoD.devolverPelicula(peliculaDev, clienteDev);
                        System.out.println("Película devuelta correctamente.");
                    } else {
                        System.out.println("Cliente o película no encontrados.");
                    }
                    break;
                
                case "6":
                    if (videoD == null) {
                        System.out.println("Debe registrar primero el videoclub antes de eliminar clientes.");
                        break;
                    }
                    System.out.print("Ingrese el nombre del cliente a dar de baja: ");
                    String clienteBaja = teclado.nextLine();
                
                    Cliente clienteEliminar = buscarCliente(videoD, clienteBaja);
                
                    if (clienteEliminar != null && videoD.darBajaCliente(clienteEliminar)) {
                        System.out.println("Cliente dado de baja correctamente.");
                    } else {
                        System.out.println("No se encontró al cliente.");
                    }
                    break;
                
                case "7":
                try {
                    System.out.print("Ingrese el título de la película a dar de baja: ");
                    String peliculaBaja = teclado.nextLine().trim();
                
                    if (peliculaBaja.isEmpty()) {
                        System.out.println("Error: El título de la película no puede estar vacío.");
                    } else {
                        Pelicula peliculaEliminar = buscarPelicula(videoD, peliculaBaja);
                        
                        if (peliculaEliminar != null) {
                            if (videoD.eliminarPelicula(peliculaEliminar)) {
                                System.out.println("Película eliminada correctamente.");
                            } else {
                                System.out.println("Error: No se pudo eliminar la película.");
                            }
                        } else {
                            System.out.println("No se encontró la película.");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error al intentar eliminar la película: " + e.getMessage());
                }
                break;
                case "8":
                    System.out.println("Saliendo del programa. ¡Hasta pronto!");
                    break;

                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }
        } while (!opcion.equals("8"));

        teclado.close();
    }

    public static boolean setValidDni(String dni) {
        Pattern pat = Pattern.compile("[A-Z]{1}[0-9]{8}");
        Matcher mat = pat.matcher(dni);
        return mat.matches();
    }

    public static boolean setValidCif(String cif) {
        Pattern pat = Pattern.compile("[A-Z]{1}[0-9]{7}[A-Z]{1}");
        Matcher mat = pat.matcher(cif);
        return mat.matches();
    }

    private static Cliente buscarCliente(VideoDaw videoD, String nombre) {
        for (Cliente c : videoD.clientesRegistrados) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                return c;
            }
        }
        return null;
    }

    private static Pelicula buscarPelicula(VideoDaw videoD, String titulo) {
        for (Pelicula p : videoD.peliculasRegistradas) {
            if (p.getTitulo().equalsIgnoreCase(titulo)) {
                return p;
            }
        }
        return null;
    }
}