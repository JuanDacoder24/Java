public class Trabajador extends Persona{

    protected String idTrabajador;

    public Trabajador(String apellido, double edad, String identificacion, String nombre, String idTrabajador) {
        super(apellido, edad, identificacion, nombre);
        this.idTrabajador = idTrabajador;
    }

    public String getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(String idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    @Override
    public String toString() {
        return "Trabajador [identificacion=" + identificacion + ", idTrabajador=" + idTrabajador + ", nombre=" + nombre
                + ", apellido=" + apellido + ", edad=" + edad + "]";
    }


    

}
