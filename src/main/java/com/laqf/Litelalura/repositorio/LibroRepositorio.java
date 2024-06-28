package com.laqf.Litelalura.repositorio;

import com.laqf.Litelalura.model.Idioma;
import com.laqf.Litelalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepositorio extends JpaRepository<Libro, Long> {

    List<Libro> findByIdiomasContaining(Idioma idioma);

    Libro findByTitulo(String titulo);

}
