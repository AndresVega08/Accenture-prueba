package com.accenture.test.service;

import com.accenture.test.dto.ProductoDTO;
import com.accenture.test.dto.ProductoPorSucursalDTO;
import com.accenture.test.dto.ResponseDTO;
import com.accenture.test.dto.SucursalDTO;
import com.accenture.test.modelo.Franquicia;
import com.accenture.test.modelo.Producto;
import com.accenture.test.modelo.Sucursal;
import com.accenture.test.repository.FranquiciaRepository;
import com.accenture.test.repository.ProductoRepository;
import com.accenture.test.repository.SucursalRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FranquiciaService {

    private final FranquiciaRepository repository;
    private final SucursalRepository sucursalRepository;
    private final ProductoRepository productoRepository;

    public FranquiciaService(FranquiciaRepository repository,
                             SucursalRepository sucursalRepository,
                             ProductoRepository productoRepository) {
        this.repository = repository;
        this.sucursalRepository = sucursalRepository;
        this.productoRepository = productoRepository;
    }

    public ResponseDTO<Franquicia> crearFranquicia(Franquicia franquicia) {
        try {
            Franquicia saved = repository.save(franquicia);
            return new ResponseDTO<>(200, "Franquicia creada correctamente", saved);
        } catch (Exception e) {
            return new ResponseDTO<>(500, "Error al crear franquicia", null);
        }
    }

    public ResponseDTO<Franquicia> actualizarNombreFranquicia(Long id, String nombre) {
        try {
            Franquicia franquicia = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Franquicia no encontrada"));

            franquicia.setNombre(nombre);

            Franquicia saved = repository.save(franquicia);

            return new ResponseDTO<>(200, "Franquicia actualizada correctamente", saved);

        } catch (RuntimeException e) {
            return new ResponseDTO<>(404, e.getMessage(), null);
        } catch (Exception e) {
            return new ResponseDTO<>(500, "Error interno del servidor", null);
        }
    }

    public ResponseDTO<SucursalDTO> agregarSucursal(Long franquiciaId, Sucursal sucursal) {

        try {
            Franquicia franquicia = repository.findById(franquiciaId)
                    .orElseThrow(() -> new RuntimeException("Franquicia no encontrada"));

            sucursal.setFranquicia(franquicia);

            Sucursal saved = sucursalRepository.save(sucursal);

            SucursalDTO dto = new SucursalDTO(
                    saved.getId(),
                    saved.getNombre(),
                    franquicia.getId(),
                    franquicia.getNombre()
            );

            return new ResponseDTO<>(200, "Sucursal creada correctamente", dto);

        } catch (RuntimeException e) {
            return new ResponseDTO<>(404, e.getMessage(), null);
        } catch (Exception e) {
            return new ResponseDTO<>(500, "Error interno del servidor", null);
        }
    }

    public ResponseDTO<SucursalDTO> actualizarNombreSucursal(Long id, String nombre) {

        try {
            Sucursal sucursal = sucursalRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

            sucursal.setNombre(nombre);

            Sucursal saved = sucursalRepository.save(sucursal);

            SucursalDTO dto = new SucursalDTO(
                    saved.getId(),
                    saved.getNombre(),
                    saved.getFranquicia().getId(),
                    saved.getFranquicia().getNombre()
            );

            return new ResponseDTO<>(200, "Sucursal actualizada correctamente", dto);

        } catch (RuntimeException e) {
            return new ResponseDTO<>(404, e.getMessage(), null);
        } catch (Exception e) {
            return new ResponseDTO<>(500, "Error interno del servidor", null);
        }
    }

    public ResponseDTO<ProductoDTO> agregarProducto(Long sucursalId, Producto producto) {

        try {
            Sucursal sucursal = sucursalRepository.findById(sucursalId)
                    .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

            producto.setSucursal(sucursal);

            Producto saved = productoRepository.save(producto);

            ProductoDTO dto = new ProductoDTO(
                    saved.getId(),
                    saved.getNombre(),
                    saved.getStock(),
                    sucursal.getId(),
                    sucursal.getNombre()
            );

            return new ResponseDTO<>(200, "Producto creado correctamente", dto);

        } catch (RuntimeException e) {
            return new ResponseDTO<>(404, e.getMessage(), null);
        } catch (Exception e) {
            return new ResponseDTO<>(500, "Error interno del servidor", null);
        }
    }

    public ResponseDTO<ProductoDTO> actualizarNombreProducto(Long id, String nombre) {

        try {
            Producto producto = productoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            producto.setNombre(nombre);

            Producto saved = productoRepository.save(producto);

            Sucursal sucursal = saved.getSucursal();

            ProductoDTO dto = new ProductoDTO(
                    saved.getId(),
                    saved.getNombre(),
                    saved.getStock(),
                    sucursal != null ? sucursal.getId() : null,
                    sucursal != null ? sucursal.getNombre() : null
            );

            return new ResponseDTO<>(200, "Producto actualizado correctamente", dto);

        } catch (RuntimeException e) {
            return new ResponseDTO<>(404, e.getMessage(), null);
        } catch (Exception e) {
            return new ResponseDTO<>(500, "Error interno del servidor", null);
        }
    }

    public ResponseDTO<ProductoDTO> actualizarStock(Long productoId, Integer nuevoStock) {

        try {
            Producto producto = productoRepository.findById(productoId)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            producto.setStock(nuevoStock);

            Producto saved = productoRepository.save(producto);

            Sucursal sucursal = saved.getSucursal();

            ProductoDTO dto = new ProductoDTO(
                    saved.getId(),
                    saved.getNombre(),
                    saved.getStock(),
                    sucursal != null ? sucursal.getId() : null,
                    sucursal != null ? sucursal.getNombre() : null
            );

            return new ResponseDTO<>(200, "Stock actualizado correctamente", dto);

        } catch (RuntimeException e) {
            return new ResponseDTO<>(404, e.getMessage(), null);
        } catch (Exception e) {
            return new ResponseDTO<>(500, "Error interno del servidor", null);
        }
    }

    public ResponseDTO<Void> eliminarProducto(Long productoId) {

        try {
            Producto producto = productoRepository.findById(productoId)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            productoRepository.delete(producto);

            return new ResponseDTO<>(200, "Producto eliminado correctamente", null);

        } catch (RuntimeException e) {
            return new ResponseDTO<>(404, e.getMessage(), null);
        } catch (Exception e) {
            return new ResponseDTO<>(500, "Error interno del servidor", null);
        }
    }

    public ResponseDTO<ProductoDTO> obtenerProductoConMasStock(Long sucursalId) {

        try {
            Producto producto = productoRepository
                    .findTopBySucursalIdOrderByStockDesc(sucursalId)
                    .orElseThrow(() -> new RuntimeException("No hay productos en esta sucursal"));

            Sucursal sucursal = producto.getSucursal();

            ProductoDTO dto = new ProductoDTO(
                    producto.getId(),
                    producto.getNombre(),
                    producto.getStock(),
                    sucursal != null ? sucursal.getId() : null,
                    sucursal != null ? sucursal.getNombre() : null
            );

            return new ResponseDTO<>(200, "Producto encontrado", dto);

        } catch (RuntimeException e) {
            return new ResponseDTO<>(404, e.getMessage(), null);
        } catch (Exception e) {
            return new ResponseDTO<>(500, "Error interno del servidor", null);
        }
    }

    public ResponseDTO<List<ProductoPorSucursalDTO>> obtenerMayorStockPorFranquicia(Long franquiciaId) {

        try {

            Franquicia franquicia = repository.findById(franquiciaId)
                    .orElseThrow(() -> new RuntimeException("Franquicia no encontrada"));

            List<ProductoPorSucursalDTO> resultado = new ArrayList<>();

            for (Sucursal sucursal : franquicia.getSucursales()) {

                Optional<Producto> productoOpt =
                        productoRepository.findTopBySucursalIdOrderByStockDesc(sucursal.getId());

                productoOpt.ifPresent(p -> resultado.add(
                        new ProductoPorSucursalDTO(
                                sucursal.getNombre(),
                                p.getNombre(),
                                p.getStock()
                        )
                ));
            }

            return new ResponseDTO<>(200, "Consulta exitosa", resultado);

        } catch (RuntimeException e) {
            return new ResponseDTO<>(404, e.getMessage(), null);
        } catch (Exception e) {
            return new ResponseDTO<>(500, "Error interno del servidor", null);
        }
    }
}