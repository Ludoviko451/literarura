package com.laqf.Litelalura.service;

import com.laqf.Litelalura.model.DatosLibros;
import com.laqf.Litelalura.model.Idioma;
import com.laqf.Litelalura.model.Libro;
import com.laqf.Litelalura.model.LibroDTO;
import com.laqf.Litelalura.repositorio.LibroRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroService implements ILibroService {

    private LibroRepositorio libroRepositorio;

    public LibroService(LibroRepositorio libroRepositorio) {
        this.libroRepositorio = libroRepositorio;
    }

    @Override
    public void registrarLibro(DatosLibros datosLibros) {
        try {
            Libro libro = new Libro(datosLibros);

            if(libroRepositorio.findByTitulo(libro.getTitulo()) != null) {
                System.out.println("El libro ya existe");
                return;
            }
            libroRepositorio.save(libro);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<LibroDTO> listarLibros() {
        List<Libro> libros = libroRepositorio.findAll();
        return libros.stream()
                .map(LibroDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<LibroDTO> mostrarLibrosPorIdioma(Idioma idiomaSeleccionado) {

        List<Libro> libros = libroRepositorio.findByIdiomasContaining(idiomaSeleccionado);
        return libros.stream()
                .map(LibroDTO::new)
                .collect(Collectors.toList());
    }
}
