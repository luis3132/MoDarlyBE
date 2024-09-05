package com.modarly.modarly.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modarly.modarly.persistence.entity.Cliente;

/**
 * 
 * @autor Luis Andres Gonzalez Corzo
 */
public interface ClienteRepository extends JpaRepository<Cliente, String> {
    
}
