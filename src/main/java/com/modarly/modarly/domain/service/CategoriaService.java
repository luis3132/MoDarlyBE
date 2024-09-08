package com.modarly.modarly.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modarly.modarly.domain.dto.CategoriaDTO;
import com.modarly.modarly.persistence.entity.Categoria;
import com.modarly.modarly.persistence.repository.CategoriaRepository;

import jakarta.transaction.Transactional;

/**
 * 
 * @autor Luis Andres Gonzalez Corzo
 */
@Service
@Transactional
public class CategoriaService implements ICategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> findAll() {
        return (List<Categoria>) categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> findById(Integer id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Categoria save(CategoriaDTO categoria) {
        Categoria categoriaEntity = new Categoria();
        categoriaEntity.setPadre(categoria.getPadre());
        categoriaEntity.setHija(categoria.getHija());
        return categoriaRepository.save(categoriaEntity);
    }

    @Override
    public Categoria update(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Boolean delete(Integer id) {
        return categoriaRepository.findById(id).map(categoria -> {
            categoriaRepository.delete(categoria);
            return true;
        }).orElse(false);
    }
}
