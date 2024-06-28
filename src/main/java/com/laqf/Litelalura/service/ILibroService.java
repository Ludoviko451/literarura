package com.laqf.Litelalura.service;

import com.laqf.Litelalura.model.DatosLibros;
import com.laqf.Litelalura.model.Idioma;
import com.laqf.Litelalura.model.Libro;
import com.laqf.Litelalura.model.LibroDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ILibroService {

    void registrarLibro(DatosLibros datosLibros);
    List<LibroDTO> listarLibros();

    List<LibroDTO> mostrarLibrosPorIdioma(Idioma idiomaSeleccionado);
}
