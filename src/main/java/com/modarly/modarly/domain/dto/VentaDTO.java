package com.modarly.modarly.domain.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaDTO {
    private Date fecha;
    private String cliente;
    private Integer pagacon;
    private Integer vueltos;
    private String metodoDePago;
}
