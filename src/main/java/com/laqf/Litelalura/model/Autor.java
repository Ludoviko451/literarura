package com.laqf.Litelalura.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;
import java.util.ArrayList;
@Getter
@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String fechaNacimiento;
    private String fechaDeMuerte;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "autor_libro",
            joinColumns = { @JoinColumn(name = "autor_id") },
            inverseJoinColumns = { @JoinColumn(name = "libro_id") }
    )
    private List<Libro> libros = new ArrayList<>();

    public Autor() {
    }

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.fechaNacimiento = datosAutor.fechaDeNacimiento();
        this.fechaDeMuerte = datosAutor.fechaDeMuerte();
    }

    // Getters y setters omitidos por brevedad

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
