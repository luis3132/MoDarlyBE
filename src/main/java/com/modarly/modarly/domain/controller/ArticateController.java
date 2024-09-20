package com.modarly.modarly.domain.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modarly.modarly.domain.dto.ArticateDTO;
import com.modarly.modarly.domain.service.ArticateService;
import com.modarly.modarly.domain.service.ArticuloService;
import com.modarly.modarly.domain.service.CategoriaService;
import com.modarly.modarly.persistence.entity.Articate;
import com.modarly.modarly.persistence.entity.ArticatePK;
import com.modarly.modarly.persistence.entity.Articulo;
import com.modarly.modarly.persistence.entity.Categoria;

/**
 * 
 * @autor Luis Andres Gonzalez Corzo
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/api/articate")
public class ArticateController {

    @Autowired
    private ArticateService articateService;
    @Autowired
    private ArticuloService articuloService;
    @Autowired
    private CategoriaService categoriaService;

    @PostMapping("/new/list")
    public ResponseEntity<List<Articate>> createArticateList(@RequestBody List<ArticateDTO> articates) {
        List<Articate> articateList = articates.stream().map(articateDTO -> {
            Articulo articulo = articuloService.findById(articateDTO.getArticulo())
                    .orElseThrow(() -> new RuntimeException("Articulo not found"));
            Categoria categoria = categoriaService.findById(articateDTO.getCategoria())
                    .orElseThrow(() -> new RuntimeException("Categoria not found"));
            ArticatePK id = new ArticatePK(articulo.getId(), categoria.getId());
            return new Articate(id, articulo, categoria);
        }).collect(Collectors.toList());
        return new ResponseEntity<>(articateService.saveAll(articateList), HttpStatus.CREATED);
    }

    @PostMapping("/new")
    public ResponseEntity<Articate> createArticate(@RequestBody Articate articate) {
        return new ResponseEntity<>(articateService.save(articate), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Articate> updateArticate(@RequestBody Articate articate) {
        Optional<Articate> obj = articateService.findById(articate.getId());
        if (obj.isPresent()) {
            return new ResponseEntity<>(articateService.save(articate), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Articate> deleteArticate(@RequestBody ArticateDTO articate) {
        ArticatePK id = new ArticatePK(articate.getArticulo(), articate.getCategoria());
        if (articateService.findById(id).isPresent()) {
            articateService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
