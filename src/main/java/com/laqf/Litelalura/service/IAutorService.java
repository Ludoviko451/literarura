package com.laqf.Litelalura.service;

import com.laqf.Litelalura.model.AutorDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IAutorService {

    List<AutorDTO> buscarAutores();

    List<AutorDTO> buscarAutoresPorAnio(int i);
}
