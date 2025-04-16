package com.mngs.countries.countries_backend.repositories;

import com.mngs.countries.countries_backend.entities.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaisRepository extends JpaRepository<Pais, Long> {
    List<Pais> findByNomeContainingIgnoreCase(String nome);
}