package com.modarly.modarly.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return (List<Categoria>) categoriaRepository.findAll()
                .stream()
                .filter(c -> Boolean.TRUE.equals(c.getEstado()))
                .collect(Collectors.toList());
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
        categoriaEntity.setEstado(categoria.getEstado());
        return categoriaRepository.save(categoriaEntity);
    }

    @Override
    public Categoria update(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Boolean delete(Integer id) {
        Optional<Categoria> obj = categoriaRepository.findById(id);
        Categoria categoria = obj.get();
        // Conmprobar si el objeto existe
        if (!obj.isPresent()) {
            return false;
        }
        // Comprobar si no tiene articulos asociados
        List<Categoria> articulos = categoriaRepository.articuloCategoria(obj.get().getPadre());
        if (articulos.size() > 0) {
            // Comprar si es una clase hija
            if (!categoria.getHija().equals("")) {
                obj.get().setEstado(false);
                categoriaRepository.save(obj.get());
                return true;
            }
            // clase padre
            categoriaRepository.findByPadre(obj.get().getPadre())
                    .stream()
                    .forEach(c -> {
                        c.setEstado(false);
                        categoriaRepository.save(c);
                    });
            return true;
        }
        // Clase hija
        if (!categoria.getHija().equals("")) {
            categoriaRepository.deleteById(id);
            return true;
        }

        categoriaRepository.findByPadre(obj.get().getPadre())
                .stream()
                .forEach(c -> {
                    categoriaRepository.deleteById(c.getId());
                });
        return true;
    }
}