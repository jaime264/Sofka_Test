package com.bank.cuenta.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.cuenta.model.Cuenta;
import com.bank.cuenta.model.Movimiento;
import com.bank.cuenta.model.dto.EstadoCuentaReporteDTO;
import com.bank.cuenta.model.dto.MovimientoDTO;
import com.bank.cuenta.repository.CuentaRepository;
import com.bank.cuenta.repository.MovimientoRepository;
import com.bank.cuenta.service.CuentaService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CuentaServiceImpl implements CuentaService{
	
	private final CuentaRepository cuentaRepository;
	
	private final MovimientoRepository movimientoRepository;

	@Override
	public List<Cuenta> getAll() {
		return cuentaRepository.findAll();
	}

	@Override
	public Cuenta getById(Integer id) {
		return cuentaRepository.findById(id).orElse(null);
	}

	@Override
	public Cuenta save(Cuenta cuenta) {
		cuenta.setNumeroCuenta(generarNumeroCuenta());
		return cuentaRepository.save(cuenta);
	}

	@Override
	public void delete(Integer id) {
		cuentaRepository.deleteById(id);
	}


	@Override
	public List<EstadoCuentaReporteDTO> generarEstadoCuenta(Integer clienteId, LocalDateTime fechaInicio,LocalDateTime fechaFin) {
		// TODO Auto-generated method stub
		List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);
        List<EstadoCuentaReporteDTO> reportes = new ArrayList<>();

        for (Cuenta cuenta : cuentas) {
            // Crear el DTO para cada cuenta
            EstadoCuentaReporteDTO reporte = new EstadoCuentaReporteDTO();
            reporte.setNumeroCuenta(cuenta.getNumeroCuenta());
            reporte.setSaldoActual(cuenta.getSaldoInicial()); // El saldo inicial de la cuenta
            reporte.setMovimientos(new ArrayList<>());

            // Obtener los movimientos de la cuenta dentro del rango de fechas
            List<Movimiento> movimientos = movimientoRepository.findByCuentaAndFechaBetween(cuenta, fechaInicio, fechaFin);

            // Detallar los movimientos
            for (Movimiento movimiento : movimientos) {
                MovimientoDTO movimientoDTO = new MovimientoDTO();
                movimientoDTO.setFecha(movimiento.getFecha());
                movimientoDTO.setTipoMovimiento(movimiento.getTipoMovimiento());
                movimientoDTO.setValor(movimiento.getValor());
                movimientoDTO.setSaldo(movimiento.getSaldo()); // El saldo del movimiento

                reporte.getMovimientos().add(movimientoDTO);
            }

            // Añadir el reporte de la cuenta a la lista final
            reportes.add(reporte);
        }

        return reportes;
	}
	
	
	@Override
	public Cuenta crearCuentaParaCliente(Integer clienteId) {
        String numeroCuenta = generarNumeroCuenta();
        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setNumeroCuenta(numeroCuenta);
        nuevaCuenta.setTipoCuenta("Ahorros"); // o cualquier tipo de cuenta por defecto
        nuevaCuenta.setSaldoInicial(0.0);
        nuevaCuenta.setClienteId(clienteId);
        return cuentaRepository.save(nuevaCuenta);
    }
	
	private String generarNumeroCuenta() {
        Cuenta ultimaCuenta = cuentaRepository.findTopByOrderByIdDesc();
        if (ultimaCuenta != null) {
            int ultimoNumero = Integer.parseInt(ultimaCuenta.getNumeroCuenta().substring(4)); // Obtener el número (suponiendo formato tipo 'CT0001')
            return "CT" + String.format("%04d", ultimoNumero + 1); 
        } else {
            return "CT0001";
        }
    }
}
