package com.accenture.test.repository;

import com.accenture.test.modelo.Franquicia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FranquiciaRepository extends JpaRepository<Franquicia, Long> {
}