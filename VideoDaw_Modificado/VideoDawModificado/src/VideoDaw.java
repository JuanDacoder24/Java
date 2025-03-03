
import java.util.LinkedList;

public class VideoDaw {

    private String cif;
    private String direccion;
    private String fechaAlta;
    private String PeRegistradas;
    private String ClRegistrados;
    private int contador;

    LinkedList<Pelicula> peliculasRegistradas = new LinkedList<>(); 
    LinkedList<Cliente> clientesRegistrados = new LinkedList<>();

    public VideoDaw(String cif, String direccion, String fechaAlta) {
        this.cif = cif;
        this.direccion = direccion;
        this.fechaAlta = fechaAlta;
    }

    public String getCif() {
        return cif;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public String mostrarPeliculasRegistradas(){
        for (Pelicula p : peliculasRegistradas) {
            System.out.println(p);
        }
        return mostrarPeliculasRegistradas();
    }

    public String mostrarClientesRegistrados(){
        for (Cliente c : clientesRegistrados) {
            System.out.println(c);
        }
        return mostrarClientesRegistrados();
    }

    public String alquilarPelicula(Pelicula p, Cliente c){
        for (Pelicula a : peliculasRegistradas) {
            if (a.equals(peliculasRegistradas)){
                System.out.println(a);
            }
        }
        return alquilarPelicula(p, c);
    }

    public String devolverPelicula(Pelicula p, Cliente c){
        
        return devolverPelicula(p, c);
    }

    public boolean darBajaCliente(Cliente c){
        for (Cliente bajaCliente : clientesRegistrados) {
            if(bajaCliente.getNombre().equalsIgnoreCase(ClRegistrados)){
                clientesRegistrados.remove(bajaCliente);
                return true;
            }
        }
        return false;
    }
    
    public boolean registrarCliente(Cliente c){
        for (Cliente cliente : clientesRegistrados) {
            if(cliente.getNombre().equalsIgnoreCase(ClRegistrados)){
                System.out.println("El contacto ya existe un contacto con el nombre " + ClRegistrados);
                return false;  
            }
        }
        Cliente nuevoCliente = new Cliente(PeRegistradas, ClRegistrados, direccion, null, cif, null, null);
        clientesRegistrados.add(nuevoCliente);
        System.out.println("Nuevo cliente registrado: " + nuevoCliente);
        contador++;
        return true;
    }

    public boolean registrarPelicula(Pelicula p){
        for (Pelicula pelicula : peliculasRegistradas) {
            if(pelicula.getTitulo().equalsIgnoreCase(PeRegistradas)){
                System.out.println("Esta pelicula ya se encuentra registrada " + PeRegistradas);
                return false;
            }
        }
        Pelicula nuevaPelicula = new Pelicula(null, null, false, PeRegistradas, ClRegistrados, null, null);
        peliculasRegistradas.add(nuevaPelicula);
        System.out.println("Pelicula registrada con exito: " + nuevaPelicula);
        contador++;
        return true;
    }

    public String mostrarPeliculasNoAlquiladas(VideoDaw v1){
        
        return peliculasNoAlquiladas;
    }

    public boolean darBajaPelicula(Pelicula pelicula, int p){
        
        return quitarPelicula;
    }

    @Override
    public String toString() {
        return "VideoDaw [cif=" + cif + ", direccion=" + direccion + "]";
    }

    
}
