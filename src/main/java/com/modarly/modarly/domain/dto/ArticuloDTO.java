package com.modarly.modarly.domain.dto;

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
public class ArticuloDTO {
    private String nombre;
    private String descripcion;
    private Integer precioDetal;
    private Integer precioMayorista;
}
