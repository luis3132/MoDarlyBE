package com.modarly.modarly.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modarly.modarly.persistence.entity.Venta;

/**
 * 
 * @autor Luis Andres Gonzalez Corzo
 */
public interface VentaRepository extends JpaRepository<Venta, Long> {
    
}
