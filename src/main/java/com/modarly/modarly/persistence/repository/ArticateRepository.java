package com.modarly.modarly.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modarly.modarly.persistence.entity.Articate;
import com.modarly.modarly.persistence.entity.ArticatePK;

/**
 * 
 * @autor Luis Andres Gonzalez Corzo
 */
public interface ArticateRepository extends JpaRepository<Articate, ArticatePK> {
    
}
