package com.bank.cuenta.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.bank.cuenta.model.Cuenta;
import com.bank.cuenta.service.CuentaService;

@Service
public class ClienteEventListener {

	@Autowired
	private CuentaService cuentaService;
	
	@KafkaListener(topics = "cliente-events", groupId = "cuenta-movimientos-group")
    public void handleClienteEvent(String clienteId) {
        // Con el id del cliente, creamos una nueva cuenta
        Cuenta cuenta = cuentaService.crearCuentaParaCliente(Integer.parseInt(clienteId));

        // Imprimir o loguear información si es necesario
        System.out.println("Cuenta creada para el cliente con id: " + clienteId + " con número: " + cuenta.getNumeroCuenta());
    }
}
