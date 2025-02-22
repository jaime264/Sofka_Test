package com.bank.cliente.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.cliente.model.Cliente;
import com.bank.cliente.producer.ClienteEventProducer;
import com.bank.cliente.repository.ClienteRepository;
import com.bank.cliente.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private ClienteEventProducer clienteEventProducer;

	@Override
	public List<Cliente> getAll() {
        return clienteRepository.findAll();
	}

	@Override
	public Cliente getById(Integer id) {
        return clienteRepository.findById(id).orElse(null);
	}

	@Override
	public Cliente save(Cliente cliente) {
		Cliente savedCliente =  clienteRepository.save(cliente);
        clienteEventProducer.publishClienteEvent(savedCliente.getId().toString());
        return savedCliente;
	}

	@Override
	public void delete(Integer id) {
        clienteRepository.deleteById(id);		
	}

}
