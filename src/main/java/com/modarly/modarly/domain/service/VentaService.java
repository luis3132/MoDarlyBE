package com.modarly.modarly.domain.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modarly.modarly.domain.dto.VentaDTO;
import com.modarly.modarly.persistence.entity.Cliente;
import com.modarly.modarly.persistence.entity.Venta;
import com.modarly.modarly.persistence.repository.VentaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class VentaService implements IVentaService {

    @Autowired
    private VentaRepository ventaRepository;
    
    @Autowired
    private ClienteService clienteService;

    @Override
    public Venta save(VentaDTO venta) {
        Venta ventaEntity = convertToEntity(venta);
        return ventaRepository.save(ventaEntity);
    }
    
    @Override
    public Integer countVentasByCliente(String cliente) {
        return ventaRepository.countVentasByCliente(cliente);
    }

    @Override
    public List<Venta> findVentasByHoy(String cliente) {
        if (cliente == null) {
            return ventaRepository.findVentasByHoy();
        } else {
            return ventaRepository.findVentasByHoyCliente(cliente);
        }
    }

    @Override
    public List<Venta> findVentasBySemana(Date inicio, Date fin, String cliente) {
        if (cliente == null) {
            return ventaRepository.findVentasByRango(inicio, fin);
        } else {
            return ventaRepository.findVentasByRangoCliente(inicio, fin, cliente);
        }
    }

    @Override
    public List<Venta> findVentasByMes(Date inicio, String cliente) {
        if (cliente == null) {
            return ventaRepository.findVentasByMes(inicio);
        } else {
            return ventaRepository.findVentasByMesCliente(inicio, cliente);
        }
    }

    @Override
    public List<Venta> findVentasByAnio(Date inicio, String cliente) {
        if (cliente == null) {
            return ventaRepository.findVentasByAnio(inicio);
        } else {
            return ventaRepository.findVentasByAnioCliente(inicio, cliente);
        }
    }

    @Override
    public List<Venta> findVentasByRango(Date inicio, Date fin, String cliente) {
        if (cliente == null) {
            return ventaRepository.findVentasByRango(inicio, fin);
        } else {
            return ventaRepository.findVentasByRangoCliente(inicio, fin, cliente);
        }
    }

    private Venta convertToEntity(VentaDTO venta) {
        Venta ventaEntity = new Venta();
        Cliente cliente = clienteService.findById(venta.getCliente()).get();
        ventaEntity.setCliente(cliente);
        ventaEntity.setFecha(venta.getFecha());
        ventaEntity.setPagacon(venta.getPagacon());
        ventaEntity.setVueltos(venta.getVueltos());
        return ventaEntity;
    }
    
}
