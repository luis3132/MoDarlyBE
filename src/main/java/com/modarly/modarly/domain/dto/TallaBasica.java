package com.modarly.modarly.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Luis Andres Gonzalez Corzo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TallaBasica {
    private Long id;
    private String talla;
    private Integer cantidad;
    private Integer articulo;
    private Boolean estado;
}
