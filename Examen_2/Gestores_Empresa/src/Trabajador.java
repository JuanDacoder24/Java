public class Trabajador extends Persona{

    protected String numeroSS;
    protected String email;
    protected double salario;
    protected Departamento departamento;
    protected boolean enOficina;
    
    public Trabajador(String nombre, String fechaNacimiento, String dni, String direccion, String numeroSS,
            String email, double salario, Departamento departamento, boolean enOficina) {
        super(nombre, fechaNacimiento, dni, direccion);
        this.numeroSS = numeroSS;
        this.email = email;
        this.salario = salario;
        this.departamento = departamento;
        this.enOficina = enOficina;
    }
     public Trabajador(String nombre, String fechaNacimiento, String dni, String direccion){
        super(nombre, fechaNacimiento, dni, direccion);
     }

    public String getNumeroSS() {
        return numeroSS;
    }

    public void setNumeroSS(String numeroSS) {
        this.numeroSS = numeroSS;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public boolean isEnOficina() {
        return enOficina;
    }

    public void setEnOficina(boolean enOficina) {
        this.enOficina = enOficina;
    }

    @Override
    public String toString() {
        return super.toString() + "Trabajador [numeroSS=" + numeroSS + ", email=" + email + ", salario=" + salario + ", departamento="
                + departamento + ", enOficina=" + enOficina + "]";
    }

    

}
