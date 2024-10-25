package com.modarly.modarly.domain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modarly.modarly.domain.dto.VenttallDTO;
import com.modarly.modarly.domain.service.VenttallService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/venttall")
public class VenttallController {
    
    @Autowired
    private VenttallService venttallService;

    @PostMapping("/new")
    public ResponseEntity<List<VenttallDTO>> newVenttall(@RequestBody List<VenttallDTO> venttallDTO) {
        return new ResponseEntity<>(venttallService.saveAll(venttallDTO), venttallDTO.isEmpty() ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }
}
