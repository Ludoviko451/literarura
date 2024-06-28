package com.laqf.Litelalura.model;


import java.util.List;
import java.util.stream.Collectors;

public class LibroDTO {

    private Long id;
    private String titulo;
    private List<String> idiomas;
    private List<String> nombresAutores;
    private int numeroDeDescargas;

    public LibroDTO() {
        // Constructor vacío requerido por algunas herramientas de mapeo
    }

    public LibroDTO(Libro libro) {
        this.id = libro.getId();
        this.titulo = libro.getTitulo();
        this.idiomas = libro.getIdiomas().stream()
                .map(Idioma::getNombreCompleto)
                .collect(Collectors.toList());
        this.nombresAutores = libro.getAutores().stream()
                .map(autor -> autor.getNombre())
                .collect(Collectors.toList());
        this.numeroDeDescargas = libro.getNumeroDeDescargas();
    }

    // Getters y setters (pueden ser automáticos o definidos según necesidad)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public List<String> getNombresAutores() {
        return nombresAutores;
    }

    public void setNombresAutores(List<String> nombresAutores) {
        this.nombresAutores = nombresAutores;
    }

    public int getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(int numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return
                "Libro: " + titulo + "\n" +
                "Idiomas: " + idiomas + "\n" +
                "Autores: " + nombresAutores + "\n" +
                "Numero de descargas: " + numeroDeDescargas + "\n";
    }
}
