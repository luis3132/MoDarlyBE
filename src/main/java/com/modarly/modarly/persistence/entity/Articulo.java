package com.modarly.modarly.persistence.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "Articulo")
public class Articulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String descripcion;
    private Integer precioDetal;
    private Integer precioMayorista;
    
    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean estado;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo")
    @JsonManagedReference("articulo-articate")
    private List<Articate> articate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo")
    @JsonManagedReference("articulo-talla")
    private List<Talla> tallas;
}
