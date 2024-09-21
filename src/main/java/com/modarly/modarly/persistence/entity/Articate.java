package com.modarly.modarly.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
    @JsonBackReference("articulo-articate")
    @MapsId("articulo")
    @JoinColumn(name = "articulo" , referencedColumnName = "id", insertable = false, updatable = false)
    private Articulo articulo;

    @ManyToOne
    @JsonBackReference("categoria-articate")
    @MapsId("categoria")
    @JoinColumn(name = "categoria", referencedColumnName = "id", insertable = false, updatable = false)
    private Categoria categoria;
}
