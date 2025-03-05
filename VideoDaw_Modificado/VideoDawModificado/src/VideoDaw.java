
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

    public String mostrarPeliculasRegistradas() {
        StringBuilder sb = new StringBuilder();
        if (peliculasRegistradas.isEmpty()) {
            sb.append("No hay películas registradas.\n");
        } else {
            for (Pelicula p : peliculasRegistradas) {
                sb.append(p.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    public String mostrarClientesRegistrados() {
        StringBuilder sb = new StringBuilder();
        if (clientesRegistrados.isEmpty()) {
            sb.append("No hay clientes registrados.\n");
        } else {
            for (Cliente c : clientesRegistrados) {
                sb.append(c.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    public String alquilarPelicula(Pelicula p, Cliente c){
        for (Pelicula a : peliculasRegistradas) {
            if (a.equals(peliculasRegistradas)){
                System.out.println(a);
            }
        }
        return alquilarPelicula(p, c);
    }

    public boolean devolverPelicula(Pelicula p, Cliente c) {
        if (peliculasRegistradas.contains(p) && clientesRegistrados.contains(c)) {
            System.out.println("Pelicula devuelta: " + p.getTitulo() + " por " + c.getNombre());
            return true;
        }
        System.out.println("No se pudo devolver la película.");
        return false;
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

    public boolean eliminarPelicula(Pelicula p) {
        return peliculasRegistradas.remove(p);
    }
    
    public boolean registrarCliente(Cliente c){
        for (Cliente cliente : clientesRegistrados) {
            if(cliente.getNombre().equalsIgnoreCase(ClRegistrados)){
                System.out.println("El contacto ya existe un contacto con el nombre " + ClRegistrados);
                return false;  
            }
        }
        Cliente nuevoCliente = new Cliente(PeRegistradas, ClRegistrados, direccion, null, cif, null);
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
        Pelicula nuevaPelicula = new Pelicula(null, PeRegistradas, ClRegistrados);
        peliculasRegistradas.add(nuevaPelicula);
        System.out.println("Pelicula registrada con exito: " + nuevaPelicula);
        contador++;
        return true;
    }

    public boolean mostrarClientesRegistrados(Cliente c){
        for (Cliente cliente : clientesRegistrados) {
            System.out.println(cliente);
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "VideoDaw [cif=" + cif + ", direccion=" + direccion + "]";
    }

    
}
