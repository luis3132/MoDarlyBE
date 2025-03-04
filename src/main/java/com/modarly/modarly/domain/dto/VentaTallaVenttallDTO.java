package com.modarly.modarly.domain.dto;

import java.util.List;

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
public class VentaTallaVenttallDTO {
    private VentaDTO venta;
    private List<TallaBasica> talla;
}
