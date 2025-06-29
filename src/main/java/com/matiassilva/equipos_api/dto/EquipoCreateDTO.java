package com.matiassilva.equipos_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipoCreateDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @NotBlank(message = "La liga es obligatoria")
    private String liga;
    @NotBlank(message = "El país es obligatorio")
    private String pais;

}