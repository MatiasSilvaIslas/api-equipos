package com.matiassilva.equipos_api.service;

import com.matiassilva.equipos_api.entity.Equipo;
import com.matiassilva.equipos_api.exception.NotFoundException;
import com.matiassilva.equipos_api.repository.EquipoRepository;
import com.matiassilva.equipos_api.util.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EquipoServiceImpl implements EquipoService {

    @Autowired
    private EquipoRepository repository;

    @Override
    public List<Equipo> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Equipo buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.TEAM_NOT_FOUND));
    }

    @Override
    public List<Equipo> buscarPorNombre(String nombre) {
        List<Equipo> encontrados = repository.findByNombreContainingIgnoreCase(nombre);
        if (encontrados.isEmpty()) {
            throw new NotFoundException(ErrorMessage.NO_TEAMS_FOUND);
        }
        return encontrados;
    }

    @Override
    public Equipo crear(Equipo equipo) {
        return repository.save(equipo);
    }

    @Override
    public Equipo actualizar(Long id, Equipo equipoActualizado) {
        Equipo existente = buscarPorId(id);
        existente.setNombre(equipoActualizado.getNombre());
        existente.setLiga(equipoActualizado.getLiga());
        existente.setPais(equipoActualizado.getPais());
        return repository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        Equipo equipo = buscarPorId(id);
        repository.delete(equipo);
    }
}