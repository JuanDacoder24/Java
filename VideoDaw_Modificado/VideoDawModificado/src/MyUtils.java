import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtils {
public static double leerDoublePantalla(String mensaje){
        Scanner reader = new Scanner(System.in);
        System.out.println(mensaje);
        double valor = reader.nextDouble();
        return valor;
    }

    public static void imprimir(String mensaje){
        System.out.println(mensaje);
    }

    public static String leerTextoPantalla(String mensaje){
        Scanner reader = new Scanner(System.in);
        System.out.println(mensaje);
        String valor = reader.nextLine();
        return valor;
    }

    public static boolean comprobarPatron(String patron, String texto)
    {
        boolean isOk = false;
        Pattern pat = Pattern.compile(patron); //"[0-9]{7,8}[A-Za-z]"
        Matcher mat = pat.matcher(texto);
        isOk = mat.matches();
        return isOk;
    }

    public static String comprobarPatronRepetidamente(String patron, String mensaje){
        boolean isOk = false;
        String texto;
        do { 
            texto = MyUtils.leerTextoPantalla(mensaje);
            isOk = MyUtils.comprobarPatron(patron, texto);
            if(!isOk){
                MyUtils.imprimir("El formato es incorrecto");
            }
        } while (!isOk);
        return texto;
    }
    public static Generos listaPeliculas(){
        Scanner teclado= new Scanner(System.in);

        Generos genero =  null;
        System.out.println("Seleccione el genero de la pelicula:");
        for (int i = 0; i < Generos.values().length; i++) {
            System.out.println((i+1) + ". " + Generos.values()[i]);
        }
        int opcion = teclado.nextInt();
        if (opcion >= 1 && opcion <= Generos.values().length){
            genero = Generos.values()[opcion - 1];
            System.out.println("El genero seleccionado es: " + genero);
        }
        return genero;
    }

}
