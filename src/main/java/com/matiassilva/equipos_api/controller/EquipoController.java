package com.matiassilva.equipos_api.controller;

import com.matiassilva.equipos_api.dto.EquipoCreateDTO;
import com.matiassilva.equipos_api.dto.EquipoResponseDTO;
import com.matiassilva.equipos_api.dto.EquipoUpdateDTO;
import com.matiassilva.equipos_api.entity.Equipo;
import com.matiassilva.equipos_api.exception.NotFoundException;
import com.matiassilva.equipos_api.mapper.EquipoMapper;
import com.matiassilva.equipos_api.service.EquipoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/equipos")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @Autowired
    private EquipoMapper equipoMapper;

    @GetMapping
    public ResponseEntity<List<EquipoResponseDTO>> listarTodos() {
        List<Equipo> equipos = equipoService.listarTodos();
        List<EquipoResponseDTO> dtos = equipos.stream()
                .map(equipoMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipoResponseDTO> buscarPorId(@PathVariable Long id) {
        Equipo equipo = equipoService.buscarPorId(id);
        return ResponseEntity.ok(equipoMapper.toResponseDTO(equipo));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<EquipoResponseDTO>> buscarPorNombre(@RequestParam String nombre) {
        List<Equipo> equipos = equipoService.buscarPorNombre(nombre);
        if (equipos.isEmpty()) {
            throw new NotFoundException("No se encontró ningún equipo.");
        }
        List<EquipoResponseDTO> dtos = equipos.stream()
                .map(equipoMapper::toResponseDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<EquipoResponseDTO> crear(@Valid @RequestBody EquipoCreateDTO equipoDTO) {
        Equipo equipo = equipoMapper.toEntity(equipoDTO);
        Equipo creado = equipoService.crear(equipo);
        return new ResponseEntity<>(equipoMapper.toResponseDTO(creado), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipoResponseDTO> actualizar(@PathVariable Long id,
                                                        @Valid @RequestBody EquipoUpdateDTO equipoDTO) {
        Equipo equipo = equipoMapper.toEntity(equipoDTO);
        Equipo actualizado = equipoService.actualizar(id, equipo);
        return ResponseEntity.ok(equipoMapper.toResponseDTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        equipoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}