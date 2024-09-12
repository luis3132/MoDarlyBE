package com.modarly.modarly.domain.controller;

import java.util.List;
import java.util.Optional;

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

import com.modarly.modarly.domain.service.ArticateService;
import com.modarly.modarly.persistence.entity.Articate;

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

    @PostMapping("/new/list")
    public ResponseEntity<List<Articate>> createArticateList(@RequestBody List<Articate> articates) {
        return new ResponseEntity<>(articateService.saveAll(articates), HttpStatus.CREATED);
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
    public ResponseEntity<Articate> deleteArticate(@RequestBody Articate articate) {
        if (articateService.findById(articate.getId()).isPresent()) {
            articateService.delete(articate.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
