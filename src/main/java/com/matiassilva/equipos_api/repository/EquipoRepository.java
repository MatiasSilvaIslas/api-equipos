package com.matiassilva.equipos_api.repository;

import com.matiassilva.equipos_api.entity.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    List<Equipo> findByNombreContainingIgnoreCase(String nombre);
}

