import java.util.LinkedList;

public class Gestion {

    LinkedList<Pokemon> pokemon = new LinkedList();
    
    public void registrarPokemon (String nombre){
        for (Pokemon nvpokemon : pokemon) {
            if(nvpokemon.getNombre().equals(nvpokemon.getNombre())){
                System.out.println("El pokemon ya existe");
                return;
            }else{
                pokemon.add(nvpokemon);
                System.out.println("Pokemon registrado");
            }
        }
    }

    public void mostrarPokemons(){
        System.out.println("Estos son los pokemones registrados");
                for (Pokemon p : pokemon) {
                    System.out.println(p);
                }
    }

    public void eliminarPokemon(String nombre){
        for (Pokemon p : pokemon) {
            if(p.getNombre().equals(nombre)){
                pokemon.remove(p);
                System.out.println("Pokemon eliminado");
                return;
            }
        }
        System.out.println("Pokemon no encontrado");
    }

}
