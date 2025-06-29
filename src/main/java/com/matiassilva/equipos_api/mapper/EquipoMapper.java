package com.matiassilva.equipos_api.mapper;

import com.matiassilva.equipos_api.dto.EquipoCreateDTO;
import com.matiassilva.equipos_api.dto.EquipoResponseDTO;
import com.matiassilva.equipos_api.dto.EquipoUpdateDTO;
import com.matiassilva.equipos_api.entity.Equipo;
import org.springframework.stereotype.Component;

@Component
public class EquipoMapper {

    public EquipoResponseDTO toResponseDTO(Equipo equipo) {
        EquipoResponseDTO dto = new EquipoResponseDTO();
        dto.setId(equipo.getId());
        dto.setNombre(equipo.getNombre());
        dto.setLiga(equipo.getLiga());
        dto.setPais(equipo.getPais());
        return dto;
    }

    public Equipo toEntity(EquipoCreateDTO createDTO) {
        Equipo equipo = new Equipo();
        equipo.setNombre(createDTO.getNombre());
        equipo.setLiga(createDTO.getLiga());
        equipo.setPais(createDTO.getPais());
        return equipo;
    }

    public Equipo toEntity(EquipoUpdateDTO updateDTO) {
        Equipo equipo = new Equipo();
        equipo.setNombre(updateDTO.getNombre());
        equipo.setLiga(updateDTO.getLiga());
        equipo.setPais(updateDTO.getPais());
        return equipo;
    }
}