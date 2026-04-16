package com.accenture.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductoPorSucursalDTO {

    private String sucursalNombre;
    private String productoNombre;
    private Integer stock;
}