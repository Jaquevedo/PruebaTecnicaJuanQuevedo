package com.uniandes.pruebatecnica.exeptionshandler;

/**
 * Excepción personalizada para manejar errores de solicitudes mal formadas (HTTP 400).
 */
public class BadRequestException extends RuntimeException {

    /**
     * Descripción del tipo de excepción.
     */
    private static final String DESCRIPTION = "Bad Request Exception (400)";

    /**
     * Constructor que inicializa la excepción con un mensaje detallado.
     * 
     * @param detail Detalle adicional sobre la causa del error.
     */
    public BadRequestException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
