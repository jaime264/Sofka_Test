package com.bank.cuenta.repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.cuenta.model.Cuenta;
import com.bank.cuenta.model.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {

	List<Movimiento> findByCuentaAndFechaBetween(Cuenta cuenta, LocalDateTime fechaInicio, LocalDateTime fechaFin);

}
