package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ClienteResponseDTO {

    private Long id;
    private String rut;
    private String nombre;
    private String apellido;
    private Integer edad;
    private String correo;
    private String telefono;

}
