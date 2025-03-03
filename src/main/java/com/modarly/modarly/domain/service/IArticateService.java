package com.modarly.modarly.domain.service;

import java.util.List;
import java.util.Optional;

import com.modarly.modarly.domain.dto.ArticateDTO;
import com.modarly.modarly.persistence.entity.Articate;
import com.modarly.modarly.persistence.entity.ArticatePK;

/**
 * 
 * @autor Luis Andres Gonzalez Corzo
 */
public interface IArticateService {
    public List<Articate> saveAll(List<ArticateDTO> articates);
    public Optional<Articate> findById(ArticatePK id);
    public Boolean delete(ArticatePK id);
}
