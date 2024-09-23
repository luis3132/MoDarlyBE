package com.modarly.modarly.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modarly.modarly.domain.dto.ArticuloCategoriaDTO;
import com.modarly.modarly.domain.dto.ArticuloDTO;
import com.modarly.modarly.domain.dto.TallaBasica;
import com.modarly.modarly.persistence.entity.Articulo;
import com.modarly.modarly.persistence.entity.Categoria;
import com.modarly.modarly.persistence.repository.ArticuloRepository;

import jakarta.transaction.Transactional;

/**
 * 
 * @autor Luis Andres Gonzalez Corzo
 */

@Service
@Transactional
public class ArticuloService implements IArticuloService {

    @Autowired
    private ArticuloRepository articuloRepository;

    @Override
    public List<ArticuloCategoriaDTO> findAll() {
        List<Articulo> articulo = articuloRepository.findAll();
        List<ArticuloCategoriaDTO> articuloDTO = new ArrayList<>();
        for (Articulo art : articulo) {
            ArticuloCategoriaDTO artDTO = new ArticuloCategoriaDTO();
            artDTO.setId(art.getId());
            artDTO.setNombre(art.getNombre());
            artDTO.setDescripcion(art.getDescripcion());
            artDTO.setPrecioDetal(art.getPrecioDetal());
            artDTO.setPrecioMayorista(art.getPrecioMayorista());
            List<Categoria> categorias = art.getArticate()
                                                .stream()
                                                .map(articate -> {
                                                    Categoria categoria = articate.getCategoria();
                                                    return categoria;
                                                })
                                                .collect(Collectors.toList()); 
            artDTO.setCategorias(categorias);
            List<TallaBasica> tallaDTOs = art.getTallas()
                                               .stream()
                                               .map(talla -> {
                                                   TallaBasica tallaDTO = new TallaBasica();
                                                   tallaDTO.setId(talla.getId());
                                                   tallaDTO.setTalla(talla.getTalla());
                                                   tallaDTO.setCantidad(talla.getCantidad());
                                                   tallaDTO.setArticulo(talla.getArticulo().getId());
                                                   return tallaDTO;
                                               })
                                               .collect(Collectors.toList());
            artDTO.setTallas(tallaDTOs);          
            articuloDTO.add(artDTO);
        }
        return articuloDTO;
    }

    @Override
    public Optional<Articulo> findById(Integer id) {
        return articuloRepository.findById(id);
    }

    @Override
    public Articulo save(ArticuloDTO articulo) {
        Articulo newArticulo = new Articulo();
        newArticulo.setNombre(articulo.getNombre());
        newArticulo.setDescripcion(articulo.getDescripcion());
        newArticulo.setPrecioDetal(articulo.getPrecioDetal());
        newArticulo.setPrecioMayorista(articulo.getPrecioMayorista());
        return articuloRepository.save(newArticulo);
    }

    @Override
    public Articulo update(Articulo articulo) {
        return articuloRepository.save(articulo);
    }

    @Override
    public Boolean delete(Integer id) {
        if (articuloRepository.findById(id).isPresent()) {
            articuloRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
}
