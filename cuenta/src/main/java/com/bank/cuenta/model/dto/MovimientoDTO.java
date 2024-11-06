package com.bank.cuenta.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoDTO {
	private LocalDateTime fecha;
	private String tipoMovimiento;
	private Double valor;
	private Double saldo;
}
