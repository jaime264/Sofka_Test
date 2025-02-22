package com.bank.cuenta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.cuenta.model.Cuenta;
import com.bank.cuenta.model.Movimiento;
import com.bank.cuenta.repository.MovimientoRepository;
import com.bank.cuenta.service.MovimientoService;

@Service
public class MovimientoServiceImpl implements MovimientoService {

	@Autowired
    private MovimientoRepository movimientoRepository;

	@Override
	public List<Movimiento> getAll() {
		return movimientoRepository.findAll();
	}

	@Override
	public Movimiento getById(Integer id) {
		return movimientoRepository.findById(id).orElse(null);
	}

	@Override
	public Movimiento save(Movimiento movimiento) {
		// Obtener la cuenta asociada al movimiento
	    Cuenta cuenta = movimiento.getCuenta();
	    if (cuenta == null) {
	        throw new IllegalArgumentException("La cuenta asociada no puede ser nula.");
	    }

	    // Si es el primer movimiento, validamos contra el saldo inicial
	    if (movimiento.getSaldo() == null) {
	        if (cuenta.getSaldoInicial() + movimiento.getValor() < 0) {
	            throw new IllegalArgumentException("Saldo no disponible");
	        }
	        // Establecer el saldo del primer movimiento con el saldo inicial
	        movimiento.setSaldo(cuenta.getSaldoInicial() + movimiento.getValor());
	    } else {
	        // Para movimientos posteriores, tomamos el saldo del movimiento anterior
	        if (movimiento.getSaldo() + movimiento.getValor() < 0) {
	            throw new IllegalArgumentException("Saldo no disponible");
	        }
	        // Actualizar el saldo en el movimiento con el valor calculado
	        movimiento.setSaldo(movimiento.getSaldo() + movimiento.getValor());
	    }

	    // Guardar el movimiento en la base de datos
	    return movimientoRepository.save(movimiento);
	}

	@Override
	public void delete(Integer id) {
		movimientoRepository.deleteById(id);
	}

    
    
}
