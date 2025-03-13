import java.util.LinkedList;

public class Jugador extends Persona{

    protected String idJugador;
    protected String email;
    
    LinkedList<Jugador> player = new LinkedList<Jugador>();
    public Jugador(String nombre, int edad, String sexo, String direccion, String idJugador) {
        super(nombre, edad, sexo, direccion);
        this.idJugador = idJugador;
    }

    public String getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(String idJugador) {
        this.idJugador = idJugador;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void registrarJugador (Jugador j){
        for (Jugador nvplayer : player) {
            if(nvplayer.getNombre().equals(nvplayer.getNombre())){
                System.out.println("El jugador ya existe");
                return;
            }else{
                player.add(nvplayer);
                System.out.println("Jugador registrado");
            }
        }
    }

    @Override
    public String toString() {
        return super.toString() + "Jugador [idJugador=" + idJugador + ", email=" + email + "]";
    }

    
}
