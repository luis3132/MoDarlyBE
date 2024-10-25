package com.modarly.modarly.domain.service;

import java.util.List;
import java.util.Optional;

import com.modarly.modarly.domain.dto.ClienteDTO;
import com.modarly.modarly.persistence.entity.Cliente;


/**
 * 
 * @author Luis Andres Gonzalez Corzo
 */
public interface IClienteService {
    List<Cliente> findAll();
    Optional<Cliente> findById(String cedula);
    Cliente save(ClienteDTO cliente);
    Boolean delete(String cedula);
}
