package Demo;

import java.time.LocalDateTime;

public class Mascota {

    private String pasaporte;
    private String nombre;
    private String fechanacimiento;
    private String peso;
    private String tipo;
    private Propietario propietario;

    public Mascota(String pasaporte, String nombre, String fechanacimiento, String peso, String tipo) {
        this.pasaporte = pasaporte;
        this.nombre = nombre;
        this.fechanacimiento = fechanacimiento;
        this.peso = peso;
        this.tipo = tipo;
        this.propietario = propietario;
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(String fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    @Override
    public String toString() {
        return "Mascota{" +
                "pasaporte='" + pasaporte + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fechanacimiento=" + fechanacimiento +
                ", peso=" + peso +
                ", tipo='" + tipo + '\'' +
                ", propietario=" + propietario +
                '}';
    }
}
