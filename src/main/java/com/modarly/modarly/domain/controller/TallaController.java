package com.modarly.modarly.domain.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modarly.modarly.domain.dto.TallaDTO;
import com.modarly.modarly.domain.service.TallaService;
import com.modarly.modarly.persistence.entity.Talla;


/**
 * 
 * @autor Luis Andres Gonzalez Corzo
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/talla")
public class TallaController {

    @Autowired
    private TallaService tallaService;

    @GetMapping("/list/{id}")
    public ResponseEntity<Talla> findById(@PathVariable("id") Long id) {
        Optional<Talla> talla = tallaService.findById(id);
        if (talla.isPresent()) {
            return new ResponseEntity<>(talla.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Talla> createTalla(@RequestBody TallaDTO talla) {
        return new ResponseEntity<>(tallaService.save(talla), HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<Talla> updateTalla(@RequestBody Talla talla) {
        return new ResponseEntity<>(tallaService.saveId(talla), HttpStatus.OK);
    }

    @PostMapping("/new/list")
    public ResponseEntity<List<Talla>> createTallas(@RequestBody List<TallaDTO> tallas) {
        return new ResponseEntity<>(tallaService.saveAll(tallas), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteTalla(@PathVariable("id") Long id) {
        return new ResponseEntity<>(tallaService.delete(id), HttpStatus.OK);
    }

}
