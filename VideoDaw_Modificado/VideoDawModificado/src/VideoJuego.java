import java.time.LocalDate;
import java.time.LocalDateTime;

public class VideoJuego extends Articulo {

    protected Generos genero;
    protected LocalDateTime fechaAlquiler;
    protected boolean isAlquilada;

    public VideoJuego(LocalDateTime fechaAlquiler, Generos genero, boolean isAlquilada, String codigo, String titulo, LocalDate fechaRegistro, LocalDate fechaBaja) {
        super(codigo, titulo, fechaRegistro, fechaBaja);
        this.fechaAlquiler = fechaAlquiler;
        this.genero = genero;
        this.isAlquilada = isAlquilada;
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
        return "Pelicula [codigo=" + codigo + ", titulo=" + titulo + ", genero=" + genero + ", FechaRegistro="
                + FechaRegistro + ", fechaAlquiler=" + fechaAlquiler + ", FechaBaja=" + FechaBaja + ", isAlquilada="
                + isAlquilada + "]";
    }
}
