package com.bank.cuenta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Cuenta")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false, length = 50)
    private String numeroCuenta;

    @Column(nullable = false, length = 50)
    private String tipoCuenta;

    @Column(nullable = false)
    private Double saldoInicial;

    @Column(nullable = false)
    private Boolean estado = true;
    
    @Column(nullable = false)
    private Integer clienteId;

}
