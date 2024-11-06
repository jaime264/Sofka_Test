package com.bank.cuenta.controller;

import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.cuenta.model.Cuenta;
import com.bank.cuenta.model.dto.EstadoCuentaReporteDTO;
import com.bank.cuenta.service.CuentaService;


@RestController
@RequestMapping("/cuentas")
public class CuentaController {
	
	@Autowired
	private CuentaService cuentaService;

	@GetMapping
    public ResponseEntity<List<Cuenta>> getAllCuentas() {
		
		List<Cuenta> list = cuentaService.getAll();
		
		if(!list.isEmpty())
		    return new ResponseEntity<>(cuentaService.getAll(), HttpStatus.OK);

	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> getCuentaById(@PathVariable Integer id) {
    	
    	Cuenta cuenta = cuentaService.getById(id);
    	if(cuenta != null)
        	return new ResponseEntity<>(cuentaService.getById(id), HttpStatus.OK);

    	
    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Cuenta> createCuenta(@RequestBody Cuenta cuenta) {
    	return new ResponseEntity<>(cuentaService.save(cuenta), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Cuenta> updateCuenta(@PathVariable Integer id, @RequestBody Cuenta cuenta) {
    	cuenta.setId(id);
    	return new ResponseEntity<>(cuentaService.save(cuenta), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable Integer id) {
    	return new ResponseEntity<>( HttpStatus.OK);
    }
    
    @GetMapping("/reportes")
    public ResponseEntity<List<EstadoCuentaReporteDTO>> generarReporte(
            @RequestParam Integer clienteId,
            @RequestParam LocalDateTime fechaInicio,
            @RequestParam LocalDateTime fechaFin) {

        List<EstadoCuentaReporteDTO> reporte = cuentaService.generarEstadoCuenta(clienteId, fechaInicio, fechaFin);
        return ResponseEntity.ok(reporte);
    }

}
