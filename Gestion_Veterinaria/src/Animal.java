public class Animal {

    private String nombre;
    private int edad;
    private String especie;
    

    public Animal(String nombre, int edad, String especie) {
        this.nombre = nombre;
        this.edad = edad;
        this.especie = especie;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getEspecie() {
        return especie;
    }

    @Override
    public String toString() {
        return "Animal [nombre=" + nombre + ", edad=" + edad + ", especie=" + especie + "]";
    }

    

}
