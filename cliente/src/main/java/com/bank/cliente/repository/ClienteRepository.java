package com.bank.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.cliente.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}