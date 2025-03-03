package com.modarly.modarly.domain.service;

import java.util.Optional;

import com.modarly.modarly.domain.dto.ArticuloNewDTO;
import com.modarly.modarly.domain.dto.ListarInventarioDTO;
import com.modarly.modarly.persistence.entity.Articulo;

/**
 * 
 * @autor Luis Andres Gonzalez Corzo
 */
public interface IArticuloService {
    ListarInventarioDTO findAll();
    Optional<Articulo> findById(Integer id);
    Articulo save(ArticuloNewDTO articulo);
    Articulo update(Articulo articulo);
    Boolean delete(Integer id);
}
