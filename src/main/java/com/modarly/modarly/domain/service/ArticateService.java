package com.modarly.modarly.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modarly.modarly.persistence.entity.Articate;
import com.modarly.modarly.persistence.entity.ArticatePK;
import com.modarly.modarly.persistence.repository.ArticateRepository;

import jakarta.transaction.Transactional;

/**
 * 
 * @autor Luis Andres Gonzalez Corzo
 */
@Service
@Transactional
public class ArticateService implements IArticateService {
    
    @Autowired
    private ArticateRepository articateRepository;

    @Override
    public Articate save(Articate articate) {
        return articateRepository.save(articate);
    }

    @Override
    public List<Articate> saveAll(List<Articate> articates) {
        return articateRepository.saveAll(articates);
    }

    @Override
    public Optional<Articate> findById(ArticatePK articate) {
        return articateRepository.findById(articate);
    }

    @Override
    public Boolean delete(ArticatePK articate) {
        if (findById(articate).isPresent()) {
            articateRepository.deleteById(articate);
            return true;
        } else {
            return false;
        }
    }
    
}
