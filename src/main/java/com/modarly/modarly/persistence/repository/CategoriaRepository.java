package com.modarly.modarly.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.modarly.modarly.persistence.entity.Categoria;

/**
 * 
 * @autor Luis Andres Gonzalez Corzo
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    @Query("SELECT c FROM Categoria c WHERE c.padre = :padre")
    List<Categoria> findByPadre(@Param("padre") String padre);

    @Query("SELECT c FROM Categoria c JOIN Articate a ON c.id = a.categoria.id WHERE c.padre = :padre")
    List<Categoria> articuloCategoria(@Param("padre") String padre);
}
