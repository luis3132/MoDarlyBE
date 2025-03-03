package com.modarly.modarly.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.modarly.modarly.domain.dto.ArticateDTO;
import com.modarly.modarly.persistence.entity.Articate;
import com.modarly.modarly.persistence.entity.ArticatePK;
import com.modarly.modarly.persistence.entity.Articulo;
import com.modarly.modarly.persistence.entity.Categoria;
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

    @Autowired
    @Lazy
    private ArticuloService articuloService;

    @Autowired
    private CategoriaService categoriaService;

    @Override
    public List<Articate> saveAll(List<ArticateDTO> articates) {
        List<Articate> articateList = articates.stream().map(articateDTO -> convertDTOtoEntity(articateDTO)).toList();
        return articateRepository.saveAll(articateList);
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

    private Articate convertDTOtoEntity(ArticateDTO articateDTO) {
        Articate obj = new Articate();

        ArticatePK articatePK = new ArticatePK();
        articatePK.setArticulo(articateDTO.getArticulo());
        articatePK.setCategoria(articateDTO.getCategoria());

        Optional<Articulo> articulo = articuloService.findById(articateDTO.getArticulo());
        Optional<Categoria> categoria = categoriaService.findById(articateDTO.getCategoria());

        if (articulo.isPresent() && categoria.isPresent()) {
            obj.setId(articatePK);
            obj.setArticulo(articulo.get());
            obj.setCategoria(categoria.get());
            return obj;
        } else {
            return null;
        }
    }
    
}
