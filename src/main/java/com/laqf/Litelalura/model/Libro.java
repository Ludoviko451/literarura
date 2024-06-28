package com.laqf.Litelalura.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
@Getter
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ElementCollection(targetClass = Idioma.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "libro_idiomas", joinColumns = @JoinColumn(name = "libro_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "idioma")
    private List<Idioma> idiomas;


    @ManyToMany(mappedBy = "libros", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autores;

    private int numeroDeDescargas;

    public Libro() {
    }

    public Libro(DatosLibros datosLibros) {
        this.titulo = datosLibros.titulo();
        this.autores = datosLibros.autor().stream().map(Autor::new).collect(Collectors.toList());
        this.idiomas = datosLibros.idiomas().stream()
                .map(Idioma::valueOf)
                .collect(Collectors.toList());
        this.numeroDeDescargas = datosLibros.numeroDeDescargas();

        for (Autor autor : this.autores) {
            autor.getLibros().add(this);
        }
    }
}
