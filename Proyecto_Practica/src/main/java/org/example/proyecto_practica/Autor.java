package org.example.proyecto_practica;

import java.time.LocalDate;
import java.util.List;

public class Autor {
    private int id;
    private String nombre;
    private String nacionalidad;
    private LocalDate fechaNacimiento;
    private LocalDate fechaFallecimiento;
    private List<Libros> libros;
}
