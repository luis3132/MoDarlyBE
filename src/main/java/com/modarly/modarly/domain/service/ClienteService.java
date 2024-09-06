package com.modarly.modarly.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modarly.modarly.persistence.entity.Cliente;
import com.modarly.modarly.persistence.repository.ClienteRepository;

import jakarta.transaction.Transactional;

/**
 * 
 * @author Luis Andres Gonzalez Corzo
 */
@Service
@Transactional
public class ClienteService implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAll() {
        return (List<Cliente>) clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> findById(String cedula) {
        return clienteRepository.findById(cedula);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Boolean delete(String cedula) {
        if (clienteRepository.existsById(cedula)) {
            clienteRepository.deleteById(cedula);
            return true;
        } else {
            return false;
        }
    }
    
}
