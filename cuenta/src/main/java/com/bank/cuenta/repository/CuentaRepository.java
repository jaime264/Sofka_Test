package com.bank.cuenta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.cuenta.model.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {

	List<Cuenta> findByClienteId(Integer clienteId);

	Cuenta findTopByOrderByIdDesc();

}
