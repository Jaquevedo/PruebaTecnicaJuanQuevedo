package com.uniandes.pruebatecnica.exeptionshandler;

/**
 * Excepción personalizada que representa un error de recurso no encontrado (HTTP 404).
 */
public class NotFoundException extends RuntimeException {

    /**
     * Descripción del tipo de excepción.
     */
    private static final String DESCRIPTION = "Not Found Exception (404)";

    /**
     * Constructor que inicializa la excepción con un mensaje de detalle.
     *
     * @param detail Mensaje adicional que describe el motivo del error.
     */
    public NotFoundException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
