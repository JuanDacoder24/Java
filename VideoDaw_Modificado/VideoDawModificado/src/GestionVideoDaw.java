import java.time.LocalDateTime;
import java.time.Period;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GestionVideoDaw {
    public static void main(String[] args) throws Exception {
        
        Scanner teclado = new Scanner (System.in);
        System.out.println("***Bienvenido a VideoDaw***");
        String opcion = "";
        Cliente cliente;
        Pelicula pelicula;
        VideoDaw videoD;

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
                String cif;
                do {
                    System.out.println("Introduzca el CIF para el registro del VideoClub");
                    cif = teclado.nextLine().toUpperCase();
                    if(setValidCif(cif)){
                        System.out.println("Correcto");
                    }else
                    System.out.println("Incorrecto, intentelo otra vez");
                }while (!setValidCif(cif));

                System.out.println("Introduza la direccion de su local");
                String direccion = teclado.nextLine();
                System.out.println(direccion + " Se ha registrado");
                    break;

                case "2":
                teclado = new Scanner (System.in);
                System.out.println("Registrar Pelicula");
                String titulo = MyUtils.leerTextoPantalla("Introduce el titulo de la pelicula");
                Generos genero = MyUtils.listaPeliculas();
                Pelicula p = new Pelicula(titulo, genero);
                System.out.println(p.mostrarInfoPelicula());
                    break;

                case "3":
                String dni;
                do {
                    System.out.println("Primero introduzca el DNI para registrar al cliente");
                    dni = teclado.nextLine().toUpperCase();
                    if(setValidDni(dni)){
                        System.out.println("Correcto");
                    }else
                    System.out.println("Incorrecto, intentelo otra vez");
                }while (!setValidDni(dni));
                System.out.println("Introduce el nombre");
                String nombre = teclado.nextLine();
                System.out.println(nombre + " Se ha resgistrado");
                    break;

                case "4":
                System.out.println("Quien va a alquilar la pelicula?");
                videoD.mostrarClientesRegistrados();
                int c = teclado.nextInt();
                System.out.println("Que pelicula quieres alquilar");
                int p1 = teclado.nextInt();
                videoD.alquilarPelicula(p, c);
                    break;

                case "5":
                System.out.println("Selecciona el cliente que quiere devolver una pelicula.");
                videoD.mostrarClientes();
                int cl = teclado.nextInt();
                System.out.println("Que pelicula va a devolver");
                videoD.devolverPelicula(p, c);
                int cl1 = teclado.nextInt();
                    break;

                case "6":
                System.out.println("Selecciona el cliente que quieres dar de baja.");
                videoD.mostrarClientesRegistrados();
                int cl2 = teclado.nextInt();
                videoD.darBajaCliente(c);
                    break;

                case "7":
                System.out.println("Selecciona la pelicula que quieres dar de baja.");
                videoD.mostrarPeliculasNoAlquiladas(videoD);
                int pe = teclado.nextInt();
                videoD.darBajaPelicula(pelicula, p);
                    break;

                case "8":
                System.out.println("Saliendo del programa, HASTA PRONTO");
                    break;
                default:
                System.out.println("Introduzca la alternativa correcta");
            }
        } while (!opcion.equalsIgnoreCase("8"));
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
}
