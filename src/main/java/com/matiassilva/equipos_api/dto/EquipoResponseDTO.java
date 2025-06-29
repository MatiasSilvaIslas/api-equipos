package com.matiassilva.equipos_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipoResponseDTO {
    private Long id;
    private String nombre;
    private String liga;
    private String pais;

}
