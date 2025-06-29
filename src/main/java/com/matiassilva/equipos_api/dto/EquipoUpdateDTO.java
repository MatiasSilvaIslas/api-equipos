package com.matiassilva.equipos_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EquipoUpdateDTO {

    @JsonProperty(required = true)
    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @JsonProperty(required = true)
    @NotNull(message = "La liga no puede ser nula")
    @NotBlank(message = "La liga es obligatoria")
    private String liga;

    @JsonProperty(required = true)
    @NotNull(message = "El país no puede ser nulo")
    @NotBlank(message = "El país es obligatorio")
    private String pais;
}