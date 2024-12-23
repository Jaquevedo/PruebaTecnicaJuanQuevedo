package com.uniandes.pruebatecnica.exeptionshandler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa un mensaje de error estándar para manejar excepciones en la aplicación.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

    /**
     * Nombre de la excepción que se produjo.
     */
    private String exception;

    /**
     * Mensaje descriptivo del error.
     */
    private String message;

    /**
     * Ruta de la solicitud donde ocurrió el error.
     */
    private String path;

    /**
     * Constructor que inicializa el mensaje de error a partir de una excepción y la ruta afectada.
     *
     * @param exception La excepción que se produjo.
     * @param path La ruta de la solicitud donde ocurrió el error.
     */
    public ErrorMessage(Exception exception, String path) {
        this.exception = exception.getClass().getSimpleName();
        this.message = exception.getMessage();
        this.path = path;
    }

    /**
     * Método sobrescrito para representar el objeto `ErrorMessage` como una cadena.
     *
     * @return Representación en formato de texto del mensaje de error.
     */
    @Override
    public String toString() {
        return "ErrorMessage{"
                + "exception='" + exception + '\''
                + ", message='" + message + '\''
                + ", path='" + path + '\''
                + '}';
    }
}
