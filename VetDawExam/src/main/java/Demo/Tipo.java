package Demo;

public class Tipo {
    private int idTpo;
    private String tipo;

    public Tipo(int idTpo, String tipo) {
        this.idTpo = idTpo;
        this.tipo = tipo;
    }

    public int getIdTpo() {
        return idTpo;
    }

    public void setIdTpo(int idTpo) {
        this.idTpo = idTpo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Tipo{" +
                "idTpo=" + idTpo +
                ", tipo='" + tipo + '\'' +
                '}';
    }

}