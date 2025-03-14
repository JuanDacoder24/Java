import java.time.LocalDate;
import java.util.LinkedList;

public class Cliente extends Persona{ 
    
    protected String numSocio;
    protected LocalDate fechaBaja;
    LinkedList<Articulo> articulosAlquilados = new LinkedList<>();

    public Cliente(String dni, String nombre, String direccion, LocalDate fechaNacimiento, String numSocio,
            LocalDate fechaBaja) {
        super(dni, nombre, direccion, fechaNacimiento);
        this.numSocio = numSocio;
        this.fechaBaja = fechaBaja;
    }

    public String getNumSocio() {
        return numSocio;
    }

    public void setNumSocio(String numSocio) {
        this.numSocio = numSocio;
    }

    public LocalDate getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public LinkedList<Articulo> getArticulosAlquilados() {
        return articulosAlquilados;
    }

    public void setArticulosAlquilados(LinkedList<Articulo> articulosAlquilados) {
        this.articulosAlquilados = articulosAlquilados;
    }

    @Override
    public String toString() {
        return "Cliente [dni=" + dni + ", numSocio=" + numSocio + ", fechaNacimiento="
                + fechaNacimiento + "]";
    }

    

    
}
