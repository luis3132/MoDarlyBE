package com.modarly.modarly.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modarly.modarly.domain.dto.ClienteDTO;
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
        return (List<Cliente>) clienteRepository.findAll()
                .stream()
                .filter(c -> Boolean.TRUE.equals(c.getEstado()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Cliente> findById(String cedula) {
        return clienteRepository.findById(cedula);
    }

    @Override
    public Cliente save(ClienteDTO cliente) {
        Cliente c = new Cliente();
        c.setCedula(cliente.getCedula());
        c.setNombres(cliente.getNombres());
        c.setApellidos(cliente.getApellidos());
        c.setTelefono(cliente.getTelefono());
        c.setFijo(cliente.getFijo());
        c.setDescripcion(cliente.getDescripcion());
        c.setMayorista(cliente.getMayorista());
        c.setEstado(true);
        c.setFechaCreacion(cliente.getFechaCreacion());
        return clienteRepository.save(c);
    }

    @Override
    public Boolean delete(String cedula) {
        Optional<Cliente> obj = clienteRepository.findById(cedula);
        if (obj.isPresent()) {
            obj.get().setEstado(false);
            clienteRepository.save(obj.get());
            return true;
        } else {
            return false;
        }
    }

}
