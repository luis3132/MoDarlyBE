package com.modarly.modarly.persistence.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Data;

/**
 * 
 * @author Luis Andres Gonzalez Corzo
 */
@Embeddable
@Data
public class VenttallPK implements Serializable {
    private Long venta;
    private Long talla;
}
