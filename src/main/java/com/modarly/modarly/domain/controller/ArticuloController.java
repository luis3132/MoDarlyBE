package com.modarly.modarly.domain.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modarly.modarly.domain.dto.ArticuloCategoriaDTO;
import com.modarly.modarly.domain.dto.ArticuloDTO;
import com.modarly.modarly.domain.service.ArticuloService;
import com.modarly.modarly.persistence.entity.Articulo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * 
 * @autor Luis Andres Gonzalez Corzo
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/articulo")
public class ArticuloController {

    @Autowired
    private ArticuloService articuloService;

    @GetMapping("/list")
    public List<ArticuloCategoriaDTO> getArticulos() {
        return (List<ArticuloCategoriaDTO>) articuloService.findAll();
    }
    
    @PostMapping("/new")
    public ResponseEntity<Articulo> createArticulo(@RequestBody ArticuloDTO articuloDTO) {
        return new ResponseEntity<>(articuloService.save(articuloDTO), HttpStatus.CREATED);
    }
    
    @PutMapping("/update")
    public ResponseEntity<Articulo> updateArticulo(@RequestBody Articulo articulo) {
        Optional<Articulo> obj = articuloService.findById(articulo.getId());
        if (obj.isPresent()) {
            return new ResponseEntity<>(articuloService.update(articulo), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Articulo> deleteArticulo(@PathVariable("id") Integer id) {
        Optional<Articulo> obj = articuloService.findById(id);
        if (obj.isPresent()) {
            articuloService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}