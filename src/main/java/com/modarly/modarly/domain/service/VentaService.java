package com.modarly.modarly.domain.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modarly.modarly.domain.dto.VentaBasicaDTO;
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
    public VentaBasicaDTO save(VentaDTO venta) {
        Venta ventaEntity = convertToEntity(venta);
        Venta ventaSaved = ventaRepository.save(ventaEntity);
        return convertToDTO(ventaSaved);
    }

    @Override
    public Integer countVentasByCliente(String cliente) {
        return ventaRepository.countVentasByCliente(cliente);
    }

    @Override
    public List<Venta> findVentasByHoy(String cliente) {
        List<Date> startAndEndOfDay = getStartAndEndOfDay();
        if (cliente == null) {
            return ventaRepository.findVentasByHoy(startAndEndOfDay.get(0), startAndEndOfDay.get(1));
        } else {
            return ventaRepository.findVentasByHoyCliente(startAndEndOfDay.get(0), startAndEndOfDay.get(1), cliente);
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
        ventaEntity.setMetodoDePago(venta.getMetodoDePago());
        return ventaEntity;
    }

    private List<Date> getStartAndEndOfDay() {
        List<Date> list = new ArrayList<Date>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        list.add(calendar.getTime());

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        list.add(calendar.getTime());

        return list;
    }

    private VentaBasicaDTO convertToDTO(Venta venta) {
        VentaBasicaDTO ventaDTO = new VentaBasicaDTO();
        ventaDTO.setId(venta.getId());
        ventaDTO.setCliente(venta.getCliente().getCedula());
        ventaDTO.setFecha(venta.getFecha());
        ventaDTO.setMetodoDePago(venta.getMetodoDePago());
        ventaDTO.setPagacon(venta.getPagacon());
        ventaDTO.setVueltos(venta.getVueltos());
        return ventaDTO;
    }

}
