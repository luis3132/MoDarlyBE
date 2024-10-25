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
public class VenttallDTO {
    private Long venta;
    private Long talla;
    private Integer cantidad;
    private Integer precioFinal;
}
