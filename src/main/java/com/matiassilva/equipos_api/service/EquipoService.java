package com.matiassilva.equipos_api.service;

import com.matiassilva.equipos_api.entity.Equipo;

import java.util.List;
import java.util.Optional;

public interface EquipoService {

    List<Equipo> listarTodos();

    Equipo buscarPorId(Long id);

    List<Equipo> buscarPorNombre(String nombre);

    Equipo crear(Equipo equipo);

    Equipo actualizar(Long id, Equipo equipoActualizado);

    void eliminar(Long id);
}
