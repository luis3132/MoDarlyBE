package com.modarly.modarly.domain.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modarly.modarly.domain.dto.VentaTallaVenttallDTO;
import com.modarly.modarly.domain.service.VentaService;
import com.modarly.modarly.persistence.entity.Venta;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/venta")
public class VentasController {
    
    @Autowired
    private VentaService ventaService;

    @PostMapping("/new")
    public ResponseEntity<Long> createVenta(@RequestBody VentaTallaVenttallDTO venta) {
        Long id = ventaService.save(venta);
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping("/count/{cliente}")
    public ResponseEntity<Integer> countVentasByCliente(@PathVariable("cliente") String cliente) {
        return new ResponseEntity<>(ventaService.countVentasByCliente(cliente), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Venta> findVentaById(@PathVariable("id") Long id) {
        Optional<Venta> venta = ventaService.findById(id);
        if (venta.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(venta.get(), HttpStatus.OK);
    }
    

    @GetMapping("/list/hoy")
    public ResponseEntity<List<Venta>> findVentaByHoy() {
        return new ResponseEntity<>(ventaService.findVentasByHoy(null), HttpStatus.OK);
    }

    @GetMapping("/list/hoy/{cliente}")
    public ResponseEntity<List<Venta>> findVentaByHoyCliente(String cliente) {
        return new ResponseEntity<>(ventaService.findVentasByHoy(cliente), HttpStatus.OK);
    }

    @GetMapping("/list/semana/{inicio}/{fin}")
    public ResponseEntity<List<Venta>> findVentaBySemana(@PathVariable("inicio") Date inicio, @PathVariable("fin") Date fin) {
        return new ResponseEntity<>(ventaService.findVentasBySemana(inicio, fin, null), HttpStatus.OK);
    }

    @GetMapping("/list/semana/{inicio}/{fin}/{cliente}")
    public ResponseEntity<List<Venta>> findVentaBySemanaCliente(@PathVariable("inicio") Date inicio, @PathVariable("fin") Date fin, @PathVariable("cliente") String cliente) {
        return new ResponseEntity<>(ventaService.findVentasBySemana(inicio, fin, cliente), HttpStatus.OK);
    }

    @GetMapping("/list/mes/{inicio}")
    public ResponseEntity<List<Venta>> findVentaByMes(@PathVariable("inicio") Date inicio) {
        return new ResponseEntity<>(ventaService.findVentasByMes(inicio, null), HttpStatus.OK);
    }

    @GetMapping("/list/mes/{inicio}/{cliente}")
    public ResponseEntity<List<Venta>> findVentaByMesCliente(@PathVariable("inicio") Date inicio, @PathVariable("cliente") String cliente) {
        return new ResponseEntity<>(ventaService.findVentasByMes(inicio, cliente), HttpStatus.OK);
    }

    @GetMapping("/list/anio/{inicio}")
    public ResponseEntity<List<Venta>> findVentaByAnio(@PathVariable("inicio") Date inicio) {
        return new ResponseEntity<>(ventaService.findVentasByAnio(inicio, null), HttpStatus.OK);
    }

    @GetMapping("/list/anio/{inicio}/{cliente}")
    public ResponseEntity<List<Venta>> findVentaByAnioCliente(@PathVariable("inicio") Date inicio, @PathVariable("cliente") String cliente) {
        return new ResponseEntity<>(ventaService.findVentasByAnio(inicio, cliente), HttpStatus.OK);
    }

    @GetMapping("/list/rango/{inicio}/{fin}")
    public ResponseEntity<List<Venta>> findVentaByRango(@PathVariable("inicio") Date inicio, @PathVariable("fin") Date fin) {
        return new ResponseEntity<>(ventaService.findVentasByRango(inicio, fin, null), HttpStatus.OK);
    }

    @GetMapping("/list/rango/{inicio}/{fin}/{cliente}")
    public ResponseEntity<List<Venta>> findVentaByRangoCliente(@PathVariable("inicio") Date inicio, @PathVariable("fin") Date fin, @PathVariable("cliente") String cliente) {
        return new ResponseEntity<>(ventaService.findVentasByRango(inicio, fin, cliente), HttpStatus.OK);
    }
}
