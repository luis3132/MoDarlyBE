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
public class TallaDTO {
    private Integer articulo;
    private String talla;
    private Integer cantidad;
}
