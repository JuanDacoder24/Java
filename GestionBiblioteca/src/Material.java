import java.io.Serial;

public abstract class Material {
    @Serial
    private static final long serialVersionUID = 9223372036854775807L;

    protected int id;
    protected String titulo;
    protected boolean disponible;

    public Material(int id, String titulo) {
    
    this.id = id;
        this.titulo = titulo;
        this.disponible = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    
    @Override
    public String toString() {
        return "Material [id=" + id + ", titulo=" + titulo + ", disponible=" + disponible + "]";
    }

    

    public abstract String getTipoMaterial();
}