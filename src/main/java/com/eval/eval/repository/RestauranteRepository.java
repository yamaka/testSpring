package com.eval.eval.repository;

import com.eval.eval.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    @Query("SELECT r FROM Restaurante r WHERE r.nombre = ?1")
    Optional<Restaurante> findByName(String nombre);
}
