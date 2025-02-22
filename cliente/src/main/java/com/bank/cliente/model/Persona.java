package com.bank.cliente.model;

import java.io.Serializable;

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
@Table(name = "Persona")
public class Persona implements Serializable{

    private static final long serialVersionUID = 1L; 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(nullable = false, length = 1)
    private Character genero;

    @Column(nullable = false)
    private Integer edad;

    @Column(unique = true, nullable = false, length = 50)
    private String identificacion;

    @Column(length = 255)
    private String direccion;

    @Column(nullable = false, length = 15)
    private String telefono;

}
