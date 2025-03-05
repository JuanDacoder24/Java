import java.time.LocalDateTime;

public class Pelicula extends Articulo{

    protected Generos genero;
    protected LocalDateTime fechaAlquiler;
    protected boolean isAlquilada;

    public Pelicula(Generos genero, String codigo, String titulo) {
        super(codigo, titulo);
        this.fechaAlquiler = LocalDateTime.now();
        this.genero = genero;
        this.isAlquilada = false;
    }

    public Generos getGenero() {
        return genero;
    }

    public void setGenero(Generos genero) {
        this.genero = genero;
    }

    public LocalDateTime getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(LocalDateTime fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public boolean isAlquilada() {
        return isAlquilada;
    }

    public void setAlquilada(boolean isAlquilada) {
        this.isAlquilada = isAlquilada;
    }

    @Override
    public String toString() {
        return "Pelicula [genero=" + genero + ", titulo=" + titulo + ", FechaRegistro=" + FechaRegistro + "]";
    }

    
}
