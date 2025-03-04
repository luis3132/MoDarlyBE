package com.modarly.modarly.domain.dto;

import java.util.List;

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
public class ArticuloNewDTO {
    private String nombre;
    private String descripcion;
    private Integer precioDetal;
    private Integer precioMayorista;
    private List<Integer> categorias;
    private List<TallaDTO> tallas;
}
