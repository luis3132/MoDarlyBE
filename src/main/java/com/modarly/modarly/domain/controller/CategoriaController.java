package com.modarly.modarly.domain.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modarly.modarly.domain.dto.CategoriaDTO;
import com.modarly.modarly.domain.service.CategoriaService;
import com.modarly.modarly.persistence.entity.Categoria;

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
@RequestMapping("/api/categoria")
public class CategoriaController {
    
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/list")
    public List<Categoria> getCategorias() {
        return categoriaService.findAll();
    }

    @PostMapping("/new")
    public ResponseEntity<Categoria> createCategoria(@RequestBody CategoriaDTO categoriaDTO) {  
        Categoria categoria = categoriaService.save(categoriaDTO);   
        if (categoria != null) {
            return new ResponseEntity<>(categoria, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,String>> deleteCategoria(@PathVariable("id") Integer id) {
        Optional<Categoria> obj = categoriaService.findById(id);
        Map<String,String> response = new HashMap<>();
        if (obj.isPresent()) {
            categoriaService.delete(id);
            response.put("mensaje", "Eliminado con exito");
            return new ResponseEntity<>(response,HttpStatus.OK);
        } else {
            response.put("mensaje", "No se encontro!");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Categoria> updateCategoria(@RequestBody Categoria categoria) {
        Optional<Categoria> obj = categoriaService.findById(categoria.getId());
        if (obj.isPresent()) {
            return new ResponseEntity<>(categoriaService.update(categoria), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
