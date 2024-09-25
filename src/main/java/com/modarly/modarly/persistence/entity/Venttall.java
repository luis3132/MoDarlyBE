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
@Table(name = "venttall")
public class Venttall {
    @EmbeddedId
    private VenttallPK id;

    private Integer cantidad;
    private Integer precioFinal;

    @ManyToOne
    @MapsId("venta")
    @JsonBackReference("venta-venttall")
    @JoinColumn(name = "venta", referencedColumnName = "id", insertable = false, updatable = false)
    private Venta venta;

    @ManyToOne
    @MapsId("talla")
    @JoinColumn(name = "Talla", referencedColumnName = "id", insertable = false, updatable = false)
    private Talla talla;
}
