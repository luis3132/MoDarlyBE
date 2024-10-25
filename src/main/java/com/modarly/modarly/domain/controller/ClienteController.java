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

import com.modarly.modarly.domain.dto.ClienteDTO;
import com.modarly.modarly.domain.service.ClienteService;
import com.modarly.modarly.persistence.entity.Cliente;

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
@RequestMapping("/api/cliente")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/list")
    public List<Cliente> getClientes() {
        return clienteService.findAll();
    }
    
    @GetMapping("/list/{cedula}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("cedula") String cedula) {
        return clienteService.findById(cedula)
                    .map(cliente -> new ResponseEntity<>(cliente, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @PostMapping("/new")
    public ResponseEntity<Cliente> createCliente(@RequestBody ClienteDTO cliente) {
        Optional<Cliente> obj = clienteService.findById(cliente.getCedula());
        if(obj.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(clienteService.save(cliente), HttpStatus.CREATED);
        }
    }
    
    @DeleteMapping("/delete/{cedula}")
    public ResponseEntity<Cliente> deleteCliente(@PathVariable("cedula") String cedula) {
        Optional<Cliente> obj = clienteService.findById(cedula);
        if(obj.isPresent()) {
            clienteService.delete(cedula);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Cliente> updateCliente(@RequestBody ClienteDTO cliente) {
        Optional<Cliente> obj = clienteService.findById(cliente.getCedula());
        if(obj.isPresent()) {
            return new ResponseEntity<>(clienteService.save(cliente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
