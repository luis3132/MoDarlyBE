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
public class ArticatePK implements Serializable {
    private Integer articulo;
    private Integer categoria;
}
