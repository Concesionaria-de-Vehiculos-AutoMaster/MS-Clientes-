package com.automaster.msclientes.service;

import com.automaster.msclientes.dto.ClienteRequestDTO;
import com.automaster.msclientes.dto.ClienteResponseDTO;

public interface ClienteService {
    ClienteResponseDTO registrarCliente(ClienteRequestDTO request);
    ClienteResponseDTO buscarPorRut(String rut);
    ClienteResponseDTO buscarPorId(Long id);
}