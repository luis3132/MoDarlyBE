package com.modarly.modarly.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modarly.modarly.domain.dto.TallaDTO;
import com.modarly.modarly.persistence.entity.Talla;
import com.modarly.modarly.persistence.repository.TallaRepository;

import jakarta.transaction.Transactional;

/**
 * 
 * @author Luis Andres Gonzalez Corzo
 */
@Service
@Transactional
public class TallaService implements ITallaService {

    @Autowired
    private TallaRepository tallaRepository;

    @Override
    public Optional<Talla> findById(Long id) {
        return tallaRepository.findById(id);
    }

    @Override
    public Talla save(TallaDTO talla) {
        Talla tallaEntity = new Talla();
        tallaEntity.setArticulo(talla.getArticulo());
        tallaEntity.setTalla(talla.getTalla());
        tallaEntity.setCantidad(talla.getCantidad());
        return tallaRepository.save(tallaEntity);
    }

    @Override
    public Talla saveId(Talla talla) {
        return tallaRepository.save(talla);
    }

    @Override
    public List<Talla> saveAll(List<TallaDTO> tallas) {
        List<Talla> tallasEntity = tallas.stream().map(talla -> {
            Talla tallaEntity = new Talla();
            tallaEntity.setArticulo(talla.getArticulo());
            tallaEntity.setTalla(talla.getTalla());
            tallaEntity.setCantidad(talla.getCantidad());
            return tallaEntity;
        }).collect(Collectors.toList());
        return tallaRepository.saveAll(tallasEntity);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Talla> talla = findById(id);
        if (talla.isPresent()) {
            tallaRepository.deleteById(id);;
            return true;
        } else {
            return false;
        }
    }



}
