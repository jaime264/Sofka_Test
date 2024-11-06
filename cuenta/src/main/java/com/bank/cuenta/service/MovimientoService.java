package com.bank.cuenta.service;

import java.util.List;

import com.bank.cuenta.model.Movimiento;

public interface MovimientoService {

	public List<Movimiento> getAll();

	public Movimiento getById(Integer id);

	public Movimiento save(Movimiento movimiento);

	public void delete(Integer id);
	

}
