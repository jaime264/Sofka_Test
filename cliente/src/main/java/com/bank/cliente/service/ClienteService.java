package com.bank.cliente.service;

import java.util.List;

import com.bank.cliente.model.Cliente;

public interface ClienteService {

	public List<Cliente> getAll();

	public Cliente getById(Integer id);

	public Cliente save(Cliente cliente);

	public void delete(Integer id);

	
}
