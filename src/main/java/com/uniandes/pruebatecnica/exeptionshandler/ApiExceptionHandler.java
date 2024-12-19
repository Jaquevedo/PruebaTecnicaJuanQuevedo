package com.uniandes.pruebatecnica.exeptionshandler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class ApiExceptionHandler {
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
        BadRequestException.class,
        NullPointerException.class,
        HttpMessageNotReadableException.class,
        HttpClientErrorException.BadRequest.class,
        IllegalArgumentException.class
    })
    @ResponseBody
    public ErrorMessage badRequest (HttpServletRequest request, Exception exception){
        return new ErrorMessage(exception, request.getRequestURI());
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
        NotFoundException.class,
        NoResourceFoundException.class,
        HttpClientErrorException.NotFound.class
    })
    @ResponseBody
    public ErrorMessage notFoundRequest (HttpServletRequest request, Exception exception){
        return new ErrorMessage(exception, request.getRequestURI());
    }
    
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
        Exception.class,
        HttpServerErrorException.class
    })
    @ResponseBody
    public ErrorMessage fatalErrorUnexpectedException (HttpServletRequest request, Exception exception){
        return new ErrorMessage(exception, request.getRequestURI());
    }
}
