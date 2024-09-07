package com.modarly.modarly.persistence.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo")
    private List<Articate> articate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo")
    private List<Talla> tallas;
}
