package com.laqf.Litelalura.service;

import com.laqf.Litelalura.model.Autor;
import com.laqf.Litelalura.model.AutorDTO;
import com.laqf.Litelalura.repositorio.AutorRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class AutorService implements IAutorService {

    AutorRepositorio autorRepositorio;
    @Autowired
    public AutorService(AutorRepositorio autorRepositorio) {
        this.autorRepositorio = autorRepositorio;
    }
    @Override
    @Transactional
    public List<AutorDTO> buscarAutores() {

        List<Autor>  autores= autorRepositorio.findAll();
        return autores.stream().map(AutorDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<AutorDTO> buscarAutoresPorAnio(int anio) {
        List<Autor> autores = autorRepositorio.findAll();
        List<Autor> autoresFiltrados = autores.stream()
                .filter(a -> {
                    String fechaDeMuerte = a.getFechaDeMuerte();
                    return fechaDeMuerte != null && !fechaDeMuerte.isEmpty() && Integer.parseInt(fechaDeMuerte) > anio;
                })
                .collect(Collectors.toList());
        return autoresFiltrados.stream().map(AutorDTO::new).collect(Collectors.toList());
    }

}
