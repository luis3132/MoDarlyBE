package com.modarly.modarly.persistence.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Luis Andres Gonzalez Corzo
 */
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticatePK implements Serializable {
    private Integer articulo;
    private Integer categoria;
}
