package com.uniandes.pruebatecnica.exeptionshandler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * Controlador de manejo global de excepciones para los endpoints de la aplicación.
 * Proporciona respuestas personalizadas para diferentes tipos de excepciones.
 */
@RestControllerAdvice
public class ApiExceptionHandler {

    /**
     * Maneja excepciones relacionadas con solicitudes mal formadas.
     * 
     * @param request   la solicitud HTTP en la que ocurrió la excepción.
     * @param exception la excepción lanzada.
     * @return un objeto {@link ErrorMessage} con los detalles de la excepción.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
        BadRequestException.class,
        NullPointerException.class,
        HttpMessageNotReadableException.class,
        HttpClientErrorException.BadRequest.class,
        IllegalArgumentException.class
    })
    @ResponseBody
    public ErrorMessage badRequest(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }

    /**
     * Maneja excepciones relacionadas con accesos no permitidos.
     * 
     * @param request   la solicitud HTTP en la que ocurrió la excepción.
     * @param exception la excepción lanzada.
     * @return un objeto {@link ErrorMessage} con los detalles de la excepción.
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({
        ForbiddenExeption.class,
        AccessDeniedException.class
    })
    @ResponseBody
    public ErrorMessage forbiddenRequest(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }

    /**
     * Maneja excepciones relacionadas con la autenticación no autorizada.
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({
        UnauthorizedException.class,
        BadCredentialsException.class
    })
    public void unauthorizedRequest() {
        // No se retorna un cuerpo en la respuesta.
    }

    /**
     * Maneja excepciones relacionadas con recursos no encontrados.
     * 
     * @param request   la solicitud HTTP en la que ocurrió la excepción.
     * @param exception la excepción lanzada.
     * @return un objeto {@link ErrorMessage} con los detalles de la excepción.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
        NotFoundException.class,
        NoResourceFoundException.class,
        HttpClientErrorException.NotFound.class
    })
    @ResponseBody
    public ErrorMessage notFoundRequest(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }

    /**
     * Maneja excepciones inesperadas relacionadas con errores internos del servidor.
     * 
     * @param request   la solicitud HTTP en la que ocurrió la excepción.
     * @param exception la excepción lanzada.
     * @return un objeto {@link ErrorMessage} con los detalles de la excepción.
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
        Exception.class,
        HttpServerErrorException.class
    })
    @ResponseBody
    public ErrorMessage fatalErrorUnexpectedException(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }
}

