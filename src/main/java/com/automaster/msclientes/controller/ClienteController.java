package com.automaster.msclientes.controller;

import com.automaster.msclientes.dto.ClienteRequestDTO;
import com.automaster.msclientes.dto.ClienteResponseDTO;
import com.automaster.msclientes.service.ClienteServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteServiceImpl clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> crearCliente(@Valid @RequestBody ClienteRequestDTO request) {
        log.info("Petición REST POST entrante para crear cliente");
        ClienteResponseDTO response = clienteService.registrarCliente(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<ClienteResponseDTO> obtenerClientePorRut(@PathVariable String rut) {
        log.info("Petición REST GET entrante para buscar cliente por RUT");
        ClienteResponseDTO response = clienteService.buscarPorRut(rut);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}