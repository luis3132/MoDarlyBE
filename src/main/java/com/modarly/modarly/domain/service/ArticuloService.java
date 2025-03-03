package com.modarly.modarly.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.modarly.modarly.domain.dto.ArticateDTO;
import com.modarly.modarly.domain.dto.ArticuloCategoriaDTO;
import com.modarly.modarly.domain.dto.ArticuloNewDTO;
import com.modarly.modarly.domain.dto.ListarInventarioDTO;
import com.modarly.modarly.domain.dto.TallaBasica;
import com.modarly.modarly.domain.dto.TallaDTO;
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

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private TallaService tallaService;

    @Autowired
    @Lazy
    private ArticateService articateService;

    @Override
    public ListarInventarioDTO findAll() {
        List<Articulo> articulo = articuloRepository.findAll();
        List<ArticuloCategoriaDTO> articuloDTO = new ArrayList<>();
        ListarInventarioDTO listarInventarioDTO = new ListarInventarioDTO();
        for (Articulo art : articulo) {
            if (art.getEstado()) {
                ArticuloCategoriaDTO artDTO = new ArticuloCategoriaDTO();
                artDTO.setId(art.getId());
                artDTO.setNombre(art.getNombre());
                artDTO.setDescripcion(art.getDescripcion());
                artDTO.setPrecioDetal(art.getPrecioDetal());
                artDTO.setPrecioMayorista(art.getPrecioMayorista());
                artDTO.setEstado(art.getEstado());
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
                        .filter(talla -> Boolean.TRUE.equals(talla.getEstado()))
                        .map(talla -> {
                            TallaBasica tallaDTO = new TallaBasica();
                            tallaDTO.setId(talla.getId());
                            tallaDTO.setTalla(talla.getTalla());
                            tallaDTO.setCantidad(talla.getCantidad());
                            tallaDTO.setArticulo(talla.getArticulo().getId());
                            tallaDTO.setEstado(talla.getEstado());
                            return tallaDTO;
                        })
                        .collect(Collectors.toList());
                artDTO.setTallas(tallaDTOs);
                articuloDTO.add(artDTO);
            }
        }
        listarInventarioDTO.setCategorias(categoriaService.findAll());
        listarInventarioDTO.setArticulos(articuloDTO);
        return listarInventarioDTO;
    }

    @Override
    public Optional<Articulo> findById(Integer id) {
        return articuloRepository.findById(id);
    }

    @Override
    public Articulo save(ArticuloNewDTO articulo) {
        Articulo nuevoArticulo = articuloRepository.save(convertDTOtoEntity(articulo));

        List<ArticateDTO> articateDTOs = articulo.getCategorias()
                .stream()
                .map(categoria -> {
                    ArticateDTO articateDTO = new ArticateDTO();
                    articateDTO.setArticulo(nuevoArticulo.getId());
                    articateDTO.setCategoria(categoria);
                    return articateDTO;
                })
                .collect(Collectors.toList());

        List<TallaDTO> tallas = articulo.getTallas()
                .stream()
                .map(talla -> {
                    TallaDTO tallaDTO = new TallaDTO();
                    tallaDTO.setTalla(talla.getTalla());
                    tallaDTO.setCantidad(talla.getCantidad());
                    tallaDTO.setArticulo(nuevoArticulo.getId());
                    return tallaDTO;
                })
                .collect(Collectors.toList());

        articateService.saveAll(articateDTOs);

        tallaService.saveAll(tallas);

        return nuevoArticulo;
    }

    @Override
    public Articulo update(Articulo articulo) {
        return articuloRepository.save(articulo);
    }

    @Override
    public Boolean delete(Integer id) {
        Optional<Articulo> art = articuloRepository.findById(id);
        if (art.isPresent()) {
            art.get().setEstado(false);
            articuloRepository.save(art.get());
            return true;
        }
        return false;
    }

    private Articulo convertDTOtoEntity(ArticuloNewDTO articulo) {
        Articulo newArticulo = new Articulo();
        newArticulo.setNombre(articulo.getNombre());
        newArticulo.setDescripcion(articulo.getDescripcion());
        newArticulo.setPrecioDetal(articulo.getPrecioDetal());
        newArticulo.setPrecioMayorista(articulo.getPrecioMayorista());
        newArticulo.setEstado(true);
        return newArticulo;
    }

}
