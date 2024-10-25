package com.modarly.modarly.domain.dto;

import java.util.Date;

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
public class ClienteDTO {
    private String cedula;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String fijo;
    private String descripcion;
    private Boolean mayorista;
    private Date fechaCreacion;
}
