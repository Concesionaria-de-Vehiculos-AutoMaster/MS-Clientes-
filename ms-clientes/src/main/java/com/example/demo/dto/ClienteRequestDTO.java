package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ClienteRequestDTO {

    @NotBlank(message = "El RUT es obligatorio")
    @Size(min = 7, max = 15, message = "El rut debe tener entre 7 y 15 caracteres")
    private String rut;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;

    @Min(value = 18, message = "El cliente debe ser mayor de 18 años")
    private Integer edad;

    @NotBlank(message = "El correo es obligatorio")
    private String correo;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(min = 7, max = 20, message = "El numero debe tener entre 7 y 20 caracteres")
    private String telefono;
}
