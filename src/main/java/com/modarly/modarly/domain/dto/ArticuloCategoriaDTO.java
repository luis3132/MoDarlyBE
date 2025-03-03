package com.modarly.modarly.domain.dto;

import java.util.List;

import com.modarly.modarly.persistence.entity.Categoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 
 * @autor Luis Andres Gonzalez Corzo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticuloCategoriaDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer precioDetal;
    private Integer precioMayorista;
    private Boolean estado;
    List<Categoria> categorias;
    List<TallaBasica> tallas;
}
