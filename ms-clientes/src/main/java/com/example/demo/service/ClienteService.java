package com.example.demo.service;

import com.example.demo.dto.ClienteRequestDTO;
import com.example.demo.dto.ClienteResponseDTO;
import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<ClienteResponseDTO> obtenerTodos() {
        List<Cliente> clientes = clienteRepository.findAll();
        // Convertimos la lista de entidades a una lista de DTOs
        return clientes.stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
    }

    public ClienteResponseDTO obtenerPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el cliente con ID: " + id));
        return convertirAResponseDTO(cliente);
    }

    public ClienteResponseDTO guardarCliente(ClienteRequestDTO request) {
        Cliente cliente = new Cliente();
        cliente.setRut(request.getRut());
        cliente.setNombre(request.getNombre());
        cliente.setApellido(request.getApellido());
        cliente.setEdad(request.getEdad());
        cliente.setCorreo(request.getCorreo());
        cliente.setTelefono(request.getTelefono());

        Cliente clienteGuardado = clienteRepository.save(cliente);

        return convertirAResponseDTO(clienteGuardado);
    }

    public ClienteResponseDTO actualizarCliente(Long id, ClienteRequestDTO request) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede actualizar. Cliente no encontrado con ID: " + id));

        clienteExistente.setRut(request.getRut());
        clienteExistente.setNombre(request.getNombre());
        clienteExistente.setApellido(request.getApellido());
        clienteExistente.setEdad(request.getEdad());
        clienteExistente.setCorreo(request.getCorreo());
        clienteExistente.setTelefono(request.getTelefono());

        Cliente clienteActualizado = clienteRepository.save(clienteExistente);

        return convertirAResponseDTO(clienteActualizado);
    }

    public void eliminarCliente(Long id) {

        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Cliente no encontrado con ID: " + id);
        }
        clienteRepository.deleteById(id);
    }

    private ClienteResponseDTO convertirAResponseDTO(Cliente cliente) {
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getRut(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getEdad(),
                cliente.getCorreo(),
                cliente.getTelefono()
        );
    }
}
