package com.matiassilva.equipos_api.controller;

import com.matiassilva.equipos_api.dto.LoginRequestDTO;
import com.matiassilva.equipos_api.dto.LoginResponseDTO;
import com.matiassilva.equipos_api.exception.ErrorResponse;
import com.matiassilva.equipos_api.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        // Usuario y contraseña hardcodeados:
        if ("test".equals(request.getUsername()) && "12345".equals(request.getPassword())) {
            String token = jwtUtil.generateToken(request.getUsername());
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Autenticación fallida", 401));
        }
    }
}