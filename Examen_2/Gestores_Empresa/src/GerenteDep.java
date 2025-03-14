
import java.util.LinkedList;

public class GerenteDep extends Trabajador{

    protected int numTrabajadores;
    protected String telefono;

    LinkedList<Trabajador> TbjresDepartamento;

    public GerenteDep(String nombre, String fechaNacimiento, String dni, String direccion, String numeroSS,
            String email, double salario, Departamento departamento, boolean enOficina, int numTrabajadores,
            String telefono, LinkedList<Trabajador> tbjresDepartamento) {
        super(nombre, fechaNacimiento, dni, direccion, numeroSS, email, salario, departamento, enOficina);
        this.numTrabajadores = numTrabajadores;
        this.telefono = telefono;
        TbjresDepartamento = new LinkedList<>();
    }

    public int getNumTrabajadores() {
        return numTrabajadores;
    }

    public void setNumTrabajadores(int numTrabajadores) {
        this.numTrabajadores = numTrabajadores;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LinkedList<Trabajador> getTbjresDepartamento() {
        return TbjresDepartamento;
    }

    public void setTbjresDepartamento(LinkedList<Trabajador> tbjresDepartamento) {
        TbjresDepartamento = tbjresDepartamento;
    }

    @Override
    public String toString() {
        return "GerenteDep [numTrabajadores=" + numTrabajadores + ", telefono=" + telefono + ", departamento="
                + departamento + ", enOficina=" + enOficina + "]";
    }
    

}
