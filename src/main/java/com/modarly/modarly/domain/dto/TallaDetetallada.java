package com.modarly.modarly.domain.dto;

import com.modarly.modarly.persistence.entity.Articulo;

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
public class TallaDetetallada {
    private Long id;
    private String talla;
    private Integer cantidad;
    private Articulo articulo;
}
