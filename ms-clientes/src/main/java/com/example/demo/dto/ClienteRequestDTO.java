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

    @NotNull(message = "El RUT es obligatorio")
    private Integer rut;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;

    @Min(value = 18, message = "El cliente debe ser mayor de 18 años")
    private Integer edad;

    @NotBlank(message = "El correo es obligatorio")
    private String correo;

    @NotNull(message = "El teléfono es obligatorio")
    private Integer telefono;

}
