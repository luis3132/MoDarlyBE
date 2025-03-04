package com.modarly.modarly.domain.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.modarly.modarly.domain.dto.VentaTallaVenttallDTO;
import com.modarly.modarly.persistence.entity.Venta;

/**
 * 
 * @autor Luis Andres Gonzalez Corzo
 */
public interface IVentaService {
    public Long save(VentaTallaVenttallDTO venta);
    public Integer countVentasByCliente(String cliente);
    public Optional<Venta> findById(Long id);
    public List<Venta> findVentasByHoy(String cliente);
    public List<Venta> findVentasBySemana(Date inicio, Date fin, String cliente);
    public List<Venta> findVentasByMes(Date inicio, String cliente);
    public List<Venta> findVentasByAnio(Date inicio, String cliente);
    public List<Venta> findVentasByRango(Date inicio, Date fin, String cliente);
}
