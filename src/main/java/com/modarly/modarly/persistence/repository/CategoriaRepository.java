package com.modarly.modarly.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modarly.modarly.persistence.entity.Categoria;

/**
 * 
 * @autor Luis Andres Gonzalez Corzo
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    
}
