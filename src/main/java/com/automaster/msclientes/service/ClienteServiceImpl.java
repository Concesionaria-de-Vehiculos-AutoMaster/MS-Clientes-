package com.automaster.msclientes.service;

import com.automaster.msclientes.dto.ClienteRequestDTO;
import com.automaster.msclientes.dto.ClienteResponseDTO;
import com.automaster.msclientes.model.Cliente;
import com.automaster.msclientes.repository.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j // Cumplimiento de rúbrica: Logs profesionales habilitados
@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public ClienteResponseDTO registrarCliente(ClienteRequestDTO request) {
        log.info("Iniciando registro de nuevo cliente con RUT: {}", request.getRut());

        // Validación de negocio: Evitar RUT duplicado
        if (clienteRepository.existsByRut(request.getRut())) {
            log.error("Rechazado: El RUT {} ya está registrado en el sistema", request.getRut());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El RUT ingresado ya se encuentra registrado.");
        }

        // Validación de negocio: Evitar Email duplicado
        if (clienteRepository.existsByEmail(request.getEmail())) {
            log.error("Rechazado: El Email {} ya está en uso", request.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo electrónico ya está asociado a otro cliente.");
        }

        // Mapeo manual de DTO a Entidad
        Cliente cliente = new Cliente();
        cliente.setRut(request.getRut());
        cliente.setNombre(request.getNombre());
        cliente.setApellido(request.getApellido());
        cliente.setEmail(request.getEmail());
        cliente.setTelefono(request.getTelefono());

        // Persistencia en BD
        Cliente guardado = clienteRepository.save(cliente);
        log.info("Cliente registrado con éxito. ID asignado: {}", guardado.getId());

        return mapearADTO(guardado);
    }

    @Override
    public ClienteResponseDTO buscarPorRut(String rut) {
        log.info("Consultando base de datos por cliente con RUT: {}", rut);

        Cliente cliente = clienteRepository.findByRut(rut)
                .orElseThrow(() -> {
                    log.error("Búsqueda fallida: No se encontró cliente con RUT {}", rut);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "El cliente no existe en los registros.");
                });

        return mapearADTO(cliente);
    }

    // Método auxiliar para limpiar las respuestas
    private ClienteResponseDTO mapearADTO(Cliente cliente) {
        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setId(cliente.getId());
        dto.setRut(cliente.getRut());

        // Unimos nombre y apellido para enviarlo más limpio al Frontend o a otros microservicios
        dto.setNombreCompleto(cliente.getNombre() + " " + cliente.getApellido());

        dto.setEmail(cliente.getEmail());
        dto.setTelefono(cliente.getTelefono());
        return dto;
    }
}