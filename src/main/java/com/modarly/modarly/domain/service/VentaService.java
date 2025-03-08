package com.modarly.modarly.domain.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modarly.modarly.domain.dto.VentaTallaVenttallDTO;
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

    @Autowired
    private VenttallService venttallService;

    @Autowired
    private TallaService tallaService;

    @Override
    public Long save(VentaTallaVenttallDTO venta) {

        tallaService.saveId(venta.getTalla());

        Venta ventaEntity = convertToEntity(venta);
        Venta ventaSaved = ventaRepository.save(ventaEntity);

        venta.getVenta().getVenttall().forEach(venttall -> venttall.setVenta(ventaSaved.getId()));

        venttallService.saveAll(venta.getVenta().getVenttall());

        return ventaSaved.getId();
    }

    @Override
    public Optional<Venta> findById(Long id) {
        return ventaRepository.findById(id);
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

    private Venta convertToEntity(VentaTallaVenttallDTO venta) {
        Venta ventaEntity = new Venta();
        Cliente cliente = clienteService.findById(venta.getVenta().getCliente()).get();
        ventaEntity.setCliente(cliente);
        ventaEntity.setFecha(venta.getVenta().getFecha());
        ventaEntity.setPagacon(venta.getVenta().getPagacon());
        ventaEntity.setVueltos(venta.getVenta().getVueltos());
        ventaEntity.setMetodoDePago(venta.getVenta().getMetodoDePago());
        return ventaEntity;
    }

    private List<Date> getStartAndEndOfDay() {
        List<Date> list = new ArrayList<Date>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, Calendar.getInstance().get(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
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

}
