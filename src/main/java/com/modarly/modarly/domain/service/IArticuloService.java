package com.modarly.modarly.domain.service;

import java.util.List;
import java.util.Optional;

import com.modarly.modarly.domain.dto.ArticuloCategoriaDTO;
import com.modarly.modarly.domain.dto.ArticuloDTO;
import com.modarly.modarly.persistence.entity.Articulo;

/**
 * 
 * @autor Luis Andres Gonzalez Corzo
 */
public interface IArticuloService {
    List<ArticuloCategoriaDTO> findAll();
    Optional<Articulo> findById(Integer id);
    Articulo save(ArticuloDTO articulo);
    Articulo update(Articulo articulo);
    Boolean delete(Integer id);
}
