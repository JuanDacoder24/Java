
import java.io.Serializable;

public class Libro implements Serializable{

    private static final long serialVersionUID = 4269683637138280526L;
    
    private String isbn;
    private String titulo;
    private String autor;
    private String fechaPublicacion;

    public Libro(String isbn, String titulo, String autor, String fechaPublicacion) {
    
    
    this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    @Override
    public String toString() {
        return "Libro [isbn=" + isbn + ", titulo=" + titulo + ", autor=" + autor + ", fechaPublicacion="
                + fechaPublicacion + "]";
    }

}
