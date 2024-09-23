package com.modarly.modarly.domain.service;

import java.util.List;
import java.util.Optional;

import com.modarly.modarly.domain.dto.TallaBasica;
import com.modarly.modarly.domain.dto.TallaDTO;
import com.modarly.modarly.persistence.entity.Talla;

public interface ITallaService {
    public Optional<Talla> findById(Long id);
    public TallaBasica save(TallaDTO talla);
    public List<Talla> saveId(List<TallaBasica> talla);
    public List<TallaBasica> saveAll(List<TallaDTO> tallas);
    public List<Boolean> delete(List<Long> id);
}
