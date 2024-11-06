package com.bank.cuenta.service;

import java.time.LocalDateTime;
import java.util.List;

import com.bank.cuenta.model.Cuenta;
import com.bank.cuenta.model.dto.EstadoCuentaReporteDTO;

public interface CuentaService {

	public List<Cuenta> getAll();

	public Cuenta getById(Integer id);

	public Cuenta save(Cuenta cuenta);

	public void delete(Integer id);

	public List<EstadoCuentaReporteDTO> generarEstadoCuenta(Integer clienteId, LocalDateTime fechaInicio,
			LocalDateTime fechaFin);

	public Cuenta crearCuentaParaCliente(Integer clienteId);

}
