package com.automaster.msclientes.dto;

import lombok.Data;

@Data
public class ClienteResponseDTO {
    private Long id;
    private String rut;
    private String nombreCompleto; // Uniremos nombre y apellido
    private String email;
    private String telefono;
}