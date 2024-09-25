package com.modarly.modarly.persistence.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.modarly.modarly.persistence.entity.Venta;

/**
 * 
 * @autor Luis Andres Gonzalez Corzo
 */
public interface VentaRepository extends JpaRepository<Venta, Long> {

    @Query("SELECT COUNT(*) FROM Venta v WHERE v.cliente.cedula = :cliente")
    Integer countVentasByCliente(@Param("cliente") String cliente);

    @Query("SELECT v FROM Venta v WHERE v.fecha = CURDATE() AND v.cliente.cedula = :cliente")
    List<Venta> findVentasByHoyCliente(@Param("cliente") String cliente);

    @Query("SELECT v FROM Venta v WHERE v.fecha BETWEEN :inicio AND :fin AND v.cliente.cedula = :cliente")
    List<Venta> findVentasByRangoCliente(@Param("inicio") Date inicio, @Param("fin") Date fin, @Param("cliente") String cliente);

    @Query("SELECT v FROM Venta v WHERE MONTH(v.fecha) = MONTH(:inicio) AND v.cliente.cedula = :cliente")
    List<Venta> findVentasByMesCliente(@Param("inicio") Date inicio, @Param("cliente") String cliente);

    @Query("SELECT v FROM Venta v WHERE YEAR(v.fecha) = YEAR(:inicio) AND v.cliente.cedula = :cliente")
    List<Venta> findVentasByAnioCliente(@Param("inicio") Date inicio, @Param("cliente") String cliente);

    @Query("SELECT v FROM Venta v WHERE v.fecha = CURDATE()")
    List<Venta> findVentasByHoy();

    @Query("SELECT v FROM Venta v WHERE v.fecha BETWEEN :inicio AND :fin")
    List<Venta> findVentasByRango(@Param("inicio") Date inicio, @Param("fin") Date fin);

    @Query("SELECT v FROM Venta v WHERE MONTH(v.fecha) = MONTH(:inicio)")
    List<Venta> findVentasByMes(@Param("inicio") Date inicio);

    @Query("SELECT v FROM Venta v WHERE YEAR(v.fecha) = YEAR(:inicio)")
    List<Venta> findVentasByAnio(@Param("inicio") Date inicio);
}
