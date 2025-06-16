
import java.time.LocalDateTime;
import java.util.LinkedList;

public class Cita {

    private Semana dia;
    private LocalDateTime hora;
    private String lugar;
    private String motivo;
    private Animal animal;
    private String propietario;

    LinkedList<Cita> citas = new LinkedList<>();

    LinkedList<Animal> animales = new LinkedList<>();

    public Cita() {
    }

    public Cita(Animal animal, Semana dia, LocalDateTime hora, String lugar, String motivo, String propietario) {
        this.animal = animal;
        this.dia = dia;
        this.hora = hora;
        this.lugar = lugar;
        this.motivo = motivo;
        this.propietario = propietario;
    }

    public Semana getDia() {
        return dia;
    }

    public void setDia(Semana dia) {
        this.dia = dia;
    }

    public LocalDateTime getHora() {
        return hora;
    }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public void registrarAnimal(Animal animal){
        for (Animal registrar : animales) {
            if (registrar.getNombre().equalsIgnoreCase(animal.getNombre())) {
                System.out.println("El animal ya está registrado.");
                return;
            }else {
                System.out.println("Animal registrado: " + animal.getNombre());
            }
        }
    }

    public void agendarCita(Cita cita) {
        citas.add(cita);
        System.out.println("Cita agendada: " + cita.toString());
    }

    public void mostrarCitas() {
        for (Cita cita : citas) {
            System.out.println(cita.toString());
        }
    }



}
