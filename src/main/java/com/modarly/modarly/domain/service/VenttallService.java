package com.modarly.modarly.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modarly.modarly.domain.dto.VenttallDTO;
import com.modarly.modarly.persistence.entity.Talla;
import com.modarly.modarly.persistence.entity.Venta;
import com.modarly.modarly.persistence.entity.Venttall;
import com.modarly.modarly.persistence.entity.VenttallPK;
import com.modarly.modarly.persistence.repository.TallaRepository;
import com.modarly.modarly.persistence.repository.VentaRepository;
import com.modarly.modarly.persistence.repository.VenttallRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class VenttallService implements IVenttallService {
    
    @Autowired
    private VenttallRepository venttallRepository;

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private TallaRepository tallaRepository;

    @Override
    public List<VenttallDTO> saveAll(List<VenttallDTO> venttall) {
        List<Venttall> venttallEntities = venttall.stream().map(this::convertToEntity).toList();
        venttallRepository.saveAll(venttallEntities);
        return venttallEntities.stream().map(this::convertToDTO).toList();
    }

    private Venttall convertToEntity(VenttallDTO venttallDTO) {
        Venttall venttall = new Venttall();
        VenttallPK id = new VenttallPK();
        Talla talla = tallaRepository.findById(venttallDTO.getTalla()).get();
        Venta venta = ventaRepository.findById(venttallDTO.getVenta()).get();
        id.setTalla(talla.getId());
        id.setVenta(venta.getId());
        venttall.setId(id);
        venttall.setCantidad(venttallDTO.getCantidad());
        venttall.setPrecioFinal(venttallDTO.getPrecioFinal());
        venttall.setTalla(talla);
        venttall.setVenta(venta);
        return venttall;
    }

    private VenttallDTO convertToDTO(Venttall venttall) {
        VenttallDTO venttallDTO = new VenttallDTO();
        venttallDTO.setCantidad(venttall.getCantidad());
        venttallDTO.setPrecioFinal(venttall.getPrecioFinal());
        venttallDTO.setTalla(venttall.getTalla().getId());
        venttallDTO.setVenta(venttall.getVenta().getId());
        return venttallDTO;
    }
}
