package com.accenture.test.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NombreDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
}