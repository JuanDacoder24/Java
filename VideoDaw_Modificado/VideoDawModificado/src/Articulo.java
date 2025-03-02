
import java.time.LocalDate;

public class Articulo {

    protected String codigo;
    protected String titulo;
    protected LocalDate FechaRegistro;
    protected LocalDate FechaBaja;

    public Articulo(String codigo, String titulo, LocalDate fechaRegistro, LocalDate fechaBaja) {
        this.codigo = codigo;
        this.titulo = titulo;
        FechaRegistro = fechaRegistro;
        FechaBaja = fechaBaja;
    }
    
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public LocalDate getFechaRegistro() {
        return FechaRegistro;
    }
    public void setFechaRegistro(LocalDate fechaRegistro) {
        FechaRegistro = fechaRegistro;
    }
    public LocalDate getFechaBaja() {
        return FechaBaja;
    }
    public void setFechaBaja(LocalDate fechaBaja) {
        FechaBaja = fechaBaja;
    }

    @Override
    public String toString() {
        return "Articulo [codigo=" + codigo + ", titulo=" + titulo + ", FechaRegistro=" + FechaRegistro + ", FechaBaja="
                + FechaBaja + "]";
    }

}
