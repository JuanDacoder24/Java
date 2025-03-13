import java.util.LinkedList;
import java.util.Scanner;

public class App {
    public static void main(String[] args){
        
        
        Scanner teclado = new Scanner(System.in);
        String opcion = "";
        Jugador jugador;
        String nombre;
        int edad;
        String sexo;
        String ubicacion;
        String idJugador;
        Pokemon pokemon;

        do { 
            System.out.println("1. Registrar jugador");
            System.out.println("2. Ingresar pokemon al Pokémon Storage");
            System.out.println("3. Mostrar Pokémon Storage");
            System.out.println("4. Eliminar Pokémon del Pokémon Storage");
            System.out.println("5. Salir");
            teclado = new Scanner(System.in);
            opcion = teclado.nextLine();

            switch (opcion) {
                case "1":
                System.out.println("Ingrese el nickname del jugador");
                nombre = teclado.nextLine();
                System.out.println("Ingrese la edad del jugador");
                edad = teclado.nextInt();
                teclado.nextLine();
                if(edad < 18){
                    System.out.println("No puedes registrarte, eres menor de edad");
                }else{
                    System.out.println("Puedes registrarte, tienes la edad suficiente");
                }
                System.out.println("Ingrese el sexo del jugador");
                sexo = teclado.nextLine();
                System.out.println("Ingrese la ubicacion(Pais) del jugador");
                ubicacion = teclado.nextLine();
                System.out.println("Ingrese el id del jugador");
                idJugador = teclado.nextLine();
                jugador = new Jugador(nombre, edad, sexo, ubicacion, idJugador);
                jugador.registrarJugador(jugador);
                System.out.println("Bienvenido " + nombre + idJugador);
                break;

                case "2":
                System.out.println("Ingrese el nombre del pokemon");
                String nombrePokemon;
                nombrePokemon = teclado.nextLine();
                System.out.println("Ingrese el tipo del pokemon");
                String tipoPokemon;
                tipoPokemon = teclado.nextLine();
                System.out.println("Ingrese el nivel del pokemon");
                int nivelPokemon;
                nivelPokemon = teclado.nextInt();
                pokemon = new Pokemon(nombrePokemon, tipoPokemon, nivelPokemon);
                System.out.println("Pokemon registrado");
                break;

                case "3":
                pokemon.mostrarPokemons();
                break;

                case "4":
                System.out.println("Ingrese el nombre del pokemon a eliminar");
                String nombrePokemonEliminar;
                nombrePokemonEliminar = teclado.nextLine();
                pokemon.eliminarPokemon(nombrePokemonEliminar);
                break;

                case "5":
                    
                default:
                    break;
            }
        } while (true);

    }
}
