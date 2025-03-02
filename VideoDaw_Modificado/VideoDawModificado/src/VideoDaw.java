public class VideoDaw {

    private String cif;
    private String direccion;
    private String fechaAlta;
    private Pelicula [] peliculasRegistradas;
    private Cliente [] clientesRegistrados;
    private int PeRegistradas;
    private int ClRegistrados;

    public VideoDaw(String cif, String direccion, String fechaAlta) {
        this.cif = cif;
        this.direccion = direccion;
        this.fechaAlta = fechaAlta;
        this.clientesRegistrados = new Cliente[100];
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

    public String mostrarInfoVideoClub(){
        String InfoClub = String.format("Informacion de club - CIF: %s, Direccion: %s", this.cif, this.direccion);
        return InfoClub;
    }

    public String mostrarPeliculasRegistradas(){
        for(int i = 0; i < PeRegistradas; i++){
            if (peliculasRegistradas[i].getIsAlquilada() == true){
                System.out.println(i + "." + peliculasRegistradas[i].mostrarInfoPelicula());
            }
        }
        return mostrarPeliculasRegistradas();
    }

    public String mostrarClientesRegistrados(){
        if(clientesRegistrados != null){
            for (int i = 0; i < ClRegistrados; i++) {
                System.out.println(i + "." + clientesRegistrados[i].toString());
            }
        }
        return mostrarClientesRegistrados();
    }

    public String alquilarPelicula(Pelicula p, Cliente c){
        for (int i = 0; i < peliculasRegistradas.length; i++) {
            if (peliculasRegistradas[i].getIsAlquilada()) { 
                System.out.println(peliculasRegistradas[i]);
            }
        }
        return alquilarPelicula(p, c);
    }

    public String devolverPelicula(Pelicula p, Cliente c){
        for (int i = 0; i < peliculasRegistradas.length; i++) {
            if (peliculasRegistradas[i] != null) {
                System.out.println(peliculasRegistradas[i]);
            }
        }
        return devolverPelicula(p, c);
    }

    public String darBajaCliente(Cliente c){
        boolean bajarCliente = false;
        for (int i = 0; i < ClRegistrados; i++) {
            this.clientesRegistrados[ClRegistrados - 1] = this.clientesRegistrados[i];
        }
        this.clientesRegistrados[ClRegistrados - 1] = null;
        ClRegistrados--;
        bajarCliente = true;
        return darBajaCliente(c);
    }
    
    public boolean registrarCliente(Cliente c){
        boolean isAdd = false;
        if(c != null){
            this.clientesRegistrados[ClRegistrados] = c;
            ClRegistrados++;
        }
        return isAdd;
    }

    public boolean registrarPelicula(Pelicula p){
        boolean isAdd = false;
        if(p != null){
            this.peliculasRegistradas[PeRegistradas] = p;
            PeRegistradas++;
            p.getIsAlquilada();
        }
        return isAdd;
    }

    public String mostrarPeliculasNoAlquiladas(VideoDaw v1){
        String peliculasNoAlquiladas = "";
            for (int i = 0; i < PeRegistradas; i++) {
                if(peliculasRegistradas[i].getIsAlquilada() == false)
                System.out.println(i + "." + peliculasRegistradas[i].mostrarInfoPelicula());
            }
        return peliculasNoAlquiladas;
    }

    public boolean darBajaPelicula(Pelicula pelicula, int p){
        boolean quitarPelicula = false;
        if(pelicula != null){
            this.peliculasRegistradas[PeRegistradas] = null;
            for (int i = 0; i < PeRegistradas; i++) {
                this.peliculasRegistradas[PeRegistradas - 1] = this.peliculasRegistradas[i];
            }
            this.peliculasRegistradas[PeRegistradas - 1] = null;
            PeRegistradas--;
            quitarPelicula = true;
            System.out.println("Pelicula dada de baja correctamente.");
        }
        return quitarPelicula;
    }
}
