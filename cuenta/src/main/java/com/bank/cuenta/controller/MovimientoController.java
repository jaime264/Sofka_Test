package com.bank.cuenta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.cuenta.model.Movimiento;
import com.bank.cuenta.service.MovimientoService;


@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
	
	@Autowired
	private MovimientoService movimientoService;

	@GetMapping
    public ResponseEntity<List<Movimiento>> getAllMovimientos() {
		
		List<Movimiento> list = movimientoService.getAll();
		
		if(!list.isEmpty())
		    return new ResponseEntity<>(movimientoService.getAll(), HttpStatus.OK);

	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> getMovimientoById(@PathVariable Integer id) {
    	
    	Movimiento movimiento = movimientoService.getById(id);
    	if(movimiento != null)
        	return new ResponseEntity<>(movimientoService.getById(id), HttpStatus.OK);

    	
    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Movimiento> createMovimiento(@RequestBody Movimiento movimiento) {
    	return new ResponseEntity<>(movimientoService.save(movimiento), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Movimiento> updateMovimiento(@PathVariable Integer id, @RequestBody Movimiento movimiento) {
    	movimiento.setId(id);
    	return new ResponseEntity<>(movimientoService.save(movimiento), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimiento(@PathVariable Integer id) {
    	movimientoService.delete(id);
    	return new ResponseEntity<>(HttpStatus.OK);
    }
}
