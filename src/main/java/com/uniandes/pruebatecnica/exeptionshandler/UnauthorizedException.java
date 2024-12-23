package com.uniandes.pruebatecnica.exeptionshandler;

/**
 * Excepción personalizada que representa un error de no autorizado (HTTP 401).
 */
public class UnauthorizedException extends RuntimeException {

    /**
     * Descripción del tipo de excepción.
     */
    private static final String DESCRIPTION = "Unauthorized Exception (401)";

    /**
     * Constructor que inicializa la excepción con un mensaje de detalle.
     *
     * @param detail Mensaje adicional que describe el motivo del error.
     */
    public UnauthorizedException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
