package com.modarly.modarly.domain.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modarly.modarly.domain.dto.ArticateDTO;
import com.modarly.modarly.domain.service.ArticateService;
import com.modarly.modarly.persistence.entity.Articate;
import com.modarly.modarly.persistence.entity.ArticatePK;

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
    public ResponseEntity<List<Articate>> createArticateList(@RequestBody List<ArticateDTO> articates) {
        return new ResponseEntity<>(articateService.saveAll(articates), HttpStatus.OK);
    }

    @DeleteMapping("/delete/list")
    public ResponseEntity<List<Boolean>> deleteArticateList(@RequestBody List<ArticateDTO> articates) {
        List<Boolean> results = articates.stream().map(articate -> {
            ArticatePK id = new ArticatePK(articate.getArticulo(), articate.getCategoria());
            if (articateService.findById(id).isPresent()) {
                return articateService.delete(id);
            } else {
                return false;
            }
        }).collect(Collectors.toList());
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

}
