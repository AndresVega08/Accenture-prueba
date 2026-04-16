package com.accenture.test.controller;

import com.accenture.test.dto.*;
import com.accenture.test.modelo.Franquicia;
import com.accenture.test.modelo.Producto;
import com.accenture.test.modelo.Sucursal;
import com.accenture.test.service.FranquiciaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/franquicias")
public class FranquiciaController {

    private final FranquiciaService service;

    public FranquiciaController(FranquiciaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseDTO<Franquicia> crear(@Valid @RequestBody NombreDTO request) {

        Franquicia franquicia = new Franquicia();
        franquicia.setNombre(request.getNombre());

        return service.crearFranquicia(franquicia);
    }

    @PostMapping("/{id}/sucursales")
    public ResponseDTO<SucursalDTO> agregarSucursal(@PathVariable Long id,
                                                    @RequestBody Sucursal sucursal) {
        return service.agregarSucursal(id, sucursal);
    }

    @PostMapping("/sucursales/{id}/productos")
    public ResponseDTO<ProductoDTO> agregarProducto(@PathVariable Long id,
                                                    @RequestBody Producto producto) {
        return service.agregarProducto(id, producto);
    }

    @GetMapping("/sucursales/{id}/producto-mayor-stock")
    public ResponseDTO<ProductoDTO> obtenerProductoMayorStock(@PathVariable Long id) {
        return service.obtenerProductoConMasStock(id);
    }

    @GetMapping("/{id}/productos-mayor-stock")
    public ResponseDTO<List<ProductoPorSucursalDTO>> obtenerMayorStockPorFranquicia(@PathVariable Long id) {
        return service.obtenerMayorStockPorFranquicia(id);
    }

    @PutMapping("/productos/{id}/stock")
    public ResponseDTO<ProductoDTO> actualizarStock(@PathVariable Long id,
                                                    @Valid @RequestBody StockUpdateDTO request) {
        return service.actualizarStock(id, request.getStock());
    }

    @PutMapping("/{id}")
    public ResponseDTO<Franquicia> actualizarFranquicia(@PathVariable Long id,
                                                        @Valid @RequestBody NombreDTO request) {
        return service.actualizarNombreFranquicia(id, request.getNombre());
    }

    @PutMapping("/sucursales/{id}")
    public ResponseDTO<SucursalDTO> actualizarSucursal(@PathVariable Long id,
                                                       @Valid @RequestBody NombreDTO request) {
        return service.actualizarNombreSucursal(id, request.getNombre());
    }

    @PutMapping("/productos/{id}")
    public ResponseDTO<ProductoDTO> actualizarProducto(@PathVariable Long id,
                                                       @Valid @RequestBody NombreDTO request) {
        return service.actualizarNombreProducto(id, request.getNombre());
    }

    @DeleteMapping("/productos/{id}")
    public ResponseDTO<Void> eliminarProducto(@PathVariable Long id) {
        return service.eliminarProducto(id);
    }
}