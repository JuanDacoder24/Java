public class Cliente extends Persona{

    protected String telefono;
    protected String correo;
    protected String direccion;

    public Cliente(String apellido, String direccion, double edad, String identificacion, String nombre, String telefono, String correo) {
        super(apellido, edad, identificacion, nombre);
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Cliente [telefono=" + telefono + ", nombre=" + nombre + ", correo=" + correo + ", apellido=" + apellido + ", direccion=" + direccion + "]";
    }

    

    
    


}
