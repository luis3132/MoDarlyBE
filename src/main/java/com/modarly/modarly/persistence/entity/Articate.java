package com.modarly.modarly.persistence.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Luis Andres Gonzalez Corzo
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "articate")
public class Articate {
    @EmbeddedId
    private ArticatePK id;

    @ManyToOne
    @JoinColumn(name = "Categoria", referencedColumnName = "id", insertable = false, updatable = false)
    private Categoria categoria;
}
