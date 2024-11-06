package com.bank.cuenta.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadoCuentaReporteDTO {
	private String numeroCuenta;
	private Double saldoActual;
	private List<MovimientoDTO> movimientos;
}
