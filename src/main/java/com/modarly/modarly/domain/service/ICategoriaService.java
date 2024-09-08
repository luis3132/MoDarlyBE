package com.modarly.modarly.domain.service;

import java.util.List;
import java.util.Optional;

import com.modarly.modarly.domain.dto.CategoriaDTO;
import com.modarly.modarly.persistence.entity.Categoria;

/**
 * 
 * @autor Luis Andres Gonzalez Corzo
 */
public interface ICategoriaService {
    List<Categoria> findAll();
    Optional<Categoria> findById(Integer id);
    Categoria save(CategoriaDTO categoria);
    Categoria update(Categoria categoria);
    Boolean delete(Integer id);
}
