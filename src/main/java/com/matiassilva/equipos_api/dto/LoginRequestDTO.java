package com.matiassilva.equipos_api.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String username;
    private String password;
}