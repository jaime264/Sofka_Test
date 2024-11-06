package com.bank.cliente.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.bank.cliente.model.Cliente;

@Service
public class ClienteEventProducer {

	private static final String TOPIC = "cliente-events";

	@Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void publishClienteEvent(Object event) {
        kafkaTemplate.send(TOPIC, event);
    }
}
