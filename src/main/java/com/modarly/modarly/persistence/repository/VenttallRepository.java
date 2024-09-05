package com.modarly.modarly.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modarly.modarly.persistence.entity.Venttall;
import com.modarly.modarly.persistence.entity.VenttallPK;

/**
 * 
 * @autor Luis Andres Gonzalez Corzo
 */
public interface VenttallRepository extends JpaRepository<Venttall, VenttallPK> {
    
}
