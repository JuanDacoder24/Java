public class Director extends Trabajador{

    protected boolean estaReunido;
    protected boolean fueraOficina;
    
    public Director(String nombre, String fechaNacimiento, String dni, String direccion, String numeroSS, String email,
            double salario, Departamento departamento, boolean enOficina, boolean estaReunido, boolean fueraOficina) {
        super(nombre, fechaNacimiento, dni, direccion, numeroSS, email, salario, departamento, enOficina);
        this.estaReunido = estaReunido;
        this.fueraOficina = fueraOficina;
    }

    public Director(String nombre, String fechaNacimiento, String dni, String direccion) {
        super(nombre, fechaNacimiento, dni, direccion);
    }

    

    public boolean isEstaReunido() {
        return estaReunido;
    }

    public void setEstaReunido(boolean estaReunido) {
        this.estaReunido = estaReunido;
    }

    public boolean isFueraOficina() {
        return fueraOficina;
    }

    public void setFueraOficina(boolean fueraOficina) {
        this.fueraOficina = fueraOficina;
    }

    @Override
    public String toString() {
        return "Director [numeroSS=" + numeroSS + ", estaReunido=" + estaReunido + ", fueraOficina=" + fueraOficina
                + ", departamento=" + departamento + ", enOficina=" + enOficina + "]";
    }

    
    

}
