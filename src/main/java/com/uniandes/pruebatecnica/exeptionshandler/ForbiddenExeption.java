package com.uniandes.pruebatecnica.exeptionshandler;

/**
 * Excepción personalizada que representa un error de acceso prohibido (HTTP 403).
 */
public class ForbiddenExeption extends RuntimeException {

    /**
     * Descripción del tipo de excepción.
     */
    private static final String DESCRIPTION = "Forbidden Exception (403)";

    /**
     * Constructor que inicializa la excepción con un mensaje de detalle.
     *
     * @param detail Mensaje adicional que describe el motivo del error.
     */
    public ForbiddenExeption(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
