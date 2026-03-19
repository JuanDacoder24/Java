import java.io.Serial;

public class Libro extends Material {        
    
    @Serial
    private static final long serialVersionUID = 7043212809282576555L;


    private String autor;
    private int numPaginas;

    public Libro(int id, String titulo, String autor, int numPaginas) {
    
    super(id, titulo);
        this.autor = autor;
        this.numPaginas = numPaginas;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getnumPaginas() {
        return numPaginas;
    }

    public void setnumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }

    @Override
    public String getTipoMaterial() {
        return "Libro";
    }

    @Override
    public String toString() {
        return "Libro [id=" + id + ", titulo=" + titulo + ", disponible=" + disponible + ", autor=" + autor
                + ", numPaginas=" + numPaginas + "]";
    }

}
