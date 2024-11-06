package com.bank.cliente.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.bank.cliente.model.Cliente;
import com.bank.cliente.service.ClienteService;

@RunWith(SpringRunner.class)
public class ClienteControllerTest {

    @Mock
    private ClienteService clienteServiceMock;

    @InjectMocks
    private ClienteController clienteController;

    @Test
    public void testGetAllClientes_WithClientes_ReturnsOk() throws Exception {
        // Mock ClienteService behavior
        List<Cliente> clientes = Arrays.asList(new Cliente(), new Cliente());
        Mockito.when(clienteServiceMock.getAll()).thenReturn(clientes);

        // Call the controller method
        ResponseEntity<List<Cliente>> response = clienteController.getAllClientes();

        // Assert response status and content
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testGetAllClientes_WithNoClientes_ReturnsNoContent() throws Exception {
        // Mock ClienteService behavior
        Mockito.when(clienteServiceMock.getAll()).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<Cliente>> response = clienteController.getAllClientes();

        // Assert response status and content
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }
    
    @Test
    public void testGetClienteById_WithExistingId_ReturnsOk() throws Exception {
        // Mock ClienteService behavior
        Cliente cliente = new Cliente();
        Mockito.when(clienteServiceMock.getById(1)).thenReturn(cliente);

        // Call the controller method
        ResponseEntity<Cliente> response = clienteController.getClienteById(1);

        // Assert response status and content
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetClienteById_WithNonExistingId_ReturnsNotFound() throws Exception {
        // Mock ClienteService behavior
        Mockito.when(clienteServiceMock.getById(2)).thenReturn(null);

        // Call the controller method
        ResponseEntity<Cliente> response = clienteController.getClienteById(2);

        // Assert response status and content
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
    
    @Test
    public void testUpdateCliente_WithValidCliente_ReturnsAccepted() throws Exception {
        // Mock ClienteService behavior
        Cliente cliente = new Cliente();
        cliente.setId(1);
        Mockito.when(clienteServiceMock.save(cliente)).thenReturn(cliente);

        // Call the controller method
        ResponseEntity<Cliente> response = clienteController.updateCliente(1, cliente);

        // Assert response status and content
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertNotNull(response.getBody());
    }
    
    @Test
    public void testDeleteCliente_WithExistingId_ReturnsOk() throws Exception {
        // Call the controller method (Mockito verifies the service call)
        clienteController.deleteCliente(1);

        // Verify ClienteService interaction
        Mockito.verify(clienteServiceMock).delete(1);
    }
    
}
