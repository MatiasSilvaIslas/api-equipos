package com.matiassilva.equipos_api.exception;

public class ErrorResponse {
    private final String mensaje;
    private final int codigo;

    public ErrorResponse(String mensaje, int codigo) {
        this.mensaje = mensaje;
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public int getCodigo() {
        return codigo;
    }
}