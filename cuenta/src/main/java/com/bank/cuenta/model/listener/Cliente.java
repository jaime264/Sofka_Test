package com.bank.cuenta.model.listener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {
	
	private Integer id;
    private String contrasena;
    private Boolean estado;

}