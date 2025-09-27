public class Persona {

    protected String identificacion;
    protected String nombre;
    protected String apellido;
    protected double edad;

    public Persona(String apellido,double edad, String identificacion, String nombre) {
        this.apellido = apellido;
        this.edad = edad;
        this.identificacion = identificacion;
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public double getEdad() {
        return edad;
    }

    public void setEdad(double edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Persona [identificacion=" + identificacion + ", nombre=" + nombre + ", apellido=" + apellido + ", edad="
                + edad + "]";
    }





}
