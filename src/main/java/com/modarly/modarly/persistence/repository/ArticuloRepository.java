package com.modarly.modarly.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modarly.modarly.persistence.entity.Articulo;

/**
 * 
 * @autor Luis Andres Gonzalez Corzo
 */
public interface ArticuloRepository extends JpaRepository<Articulo, Integer> {
    
}
