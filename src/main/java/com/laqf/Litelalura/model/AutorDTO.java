package com.laqf.Litelalura.model;

import java.util.List;
import java.util.stream.Collectors;

public class AutorDTO {

    private String nombre;
    private String fechaNacimiento;
    private String fechaDeMuerte;
    private List<LibroDTO> libros;
    public AutorDTO(Autor autor) {
        this.nombre = autor.getNombre();
        this.fechaNacimiento = autor.getFechaNacimiento();
        this.fechaDeMuerte = autor.getFechaDeMuerte();
        this.libros = autor.getLibros().stream().map(LibroDTO::new).collect(Collectors.toList());
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public String getFechaDeMuerte() {
        return fechaDeMuerte;
    }
    public void setFechaDeMuerte(String fechaDeMuerte) {
        this.fechaDeMuerte = fechaDeMuerte;
    }

    @Override
    public String toString() {
        return "***************************\n" +
                "Autor: " + nombre + "\n" +
                "Fecha de nacimiento: " + fechaNacimiento + "\n" +
                "Fecha de muerte: " + fechaDeMuerte + "\n" +
                "Libros: " + libros + "\n" +
                "***************************\n";
    }
}
