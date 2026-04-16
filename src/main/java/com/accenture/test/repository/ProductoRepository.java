package com.accenture.test.repository;

import com.accenture.test.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findTopBySucursalIdOrderByStockDesc(Long sucursalId);
}