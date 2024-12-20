package com.modarly.modarly.persistence.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "Cliente")
public class Cliente {
    @Id
    private String cedula;
    
    private String nombres;
    private String apellidos;
    private String telefono;
    private String fijo;
    private String descripcion;
    private Boolean mayorista;
    private Date fechaCreacion;
    private Boolean estado;
}
