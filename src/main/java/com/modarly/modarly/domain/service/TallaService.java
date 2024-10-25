package com.modarly.modarly.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modarly.modarly.domain.dto.TallaBasica;
import com.modarly.modarly.domain.dto.TallaDTO;
import com.modarly.modarly.persistence.entity.Articulo;
import com.modarly.modarly.persistence.entity.Talla;
import com.modarly.modarly.persistence.repository.ArticuloRepository;
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

    @Autowired
    private ArticuloRepository articuloRepository;

    @Override
    public Optional<Talla> findById(Long id) {
        return tallaRepository.findById(id);
    }

    @Override
    public TallaBasica save(TallaDTO talla) {
        Talla tallaEntity = convertToEntity(talla);
        tallaEntity.setEstado(true);
        Talla tallaSaved = tallaRepository.save(tallaEntity);
        return convertToDTO(tallaSaved);
    }

    @Override
    public List<Talla> saveId(List<TallaBasica> talla) {
        List<Talla> tallas = talla.stream().map(tallaDTO -> {
            Talla tallaEntity = convertToEntity(tallaDTO);
            return tallaEntity;
        }).collect(Collectors.toList());
        return tallaRepository.saveAll(tallas);
    }

    @Override
    public List<TallaBasica> saveAll(List<TallaDTO> tallas) {
        List<Talla> tallasEntity = tallas.stream().map(talla -> {
            Talla tallaEntity = convertToEntity(talla);
            tallaEntity.setEstado(true);
            return tallaEntity;
        }).collect(Collectors.toList());
        List<Talla> tallasSaved = tallaRepository.saveAll(tallasEntity);
        List<TallaBasica> tallasDTO = tallasSaved.stream().map(talla -> {
            TallaBasica tallaDTO = convertToDTO(talla);
            return tallaDTO;
        }).collect(Collectors.toList());
        return tallasDTO;
    }

    @Override
    public List<Boolean> delete(List<Long> id) {
        List<Boolean> result = id.stream().map(
            idTalla -> {
                Optional<Talla> talla = findById(idTalla);
                if (talla.isPresent()) {
                    talla.get().setEstado(false);
                    tallaRepository.save(talla.get());
                    return true;
                } else {
                    return false;
                }
            }
        ).collect(Collectors.toList());
        return result;
    }

    private Talla convertToEntity(TallaDTO talla) {
        Talla tallaEntity = new Talla();
        tallaEntity.setTalla(talla.getTalla());
        tallaEntity.setCantidad(talla.getCantidad());
        Articulo articulo = fetchArticuloById(talla.getArticulo());
        tallaEntity.setArticulo(articulo);
        return tallaEntity;
    }

    private Talla convertToEntity(TallaBasica talla) {
        Talla tallaEntity = new Talla();
        tallaEntity.setId(talla.getId());
        tallaEntity.setTalla(talla.getTalla());
        tallaEntity.setCantidad(talla.getCantidad());
        // Assuming TallaBasica also has an Articulo ID
        Articulo articulo = fetchArticuloById(talla.getArticulo());
        tallaEntity.setArticulo(articulo);
        return tallaEntity;
    }

    private TallaBasica convertToDTO(Talla talla) {
        TallaBasica tallaDTO = new TallaBasica();
        tallaDTO.setId(talla.getId());
        tallaDTO.setTalla(talla.getTalla());
        tallaDTO.setCantidad(talla.getCantidad());
        tallaDTO.setEstado(talla.getEstado());
        tallaDTO.setArticulo(talla.getArticulo().getId());
        return tallaDTO;
    }

    private Articulo fetchArticuloById(Integer id) {
        return articuloRepository.findById(id).get();
    }

}
