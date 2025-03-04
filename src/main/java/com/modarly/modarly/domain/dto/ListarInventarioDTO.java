package com.modarly.modarly.domain.dto;

import java.util.List;

import com.modarly.modarly.persistence.entity.Categoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @autor Luis Andres Gonzalez Corzo
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListarInventarioDTO {
    private List<ArticuloCategoriaDTO> articulos;
    private List<Categoria> categorias;
}
