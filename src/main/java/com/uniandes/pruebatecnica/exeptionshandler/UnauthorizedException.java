package com.uniandes.pruebatecnica.exeptionshandler;

public class UnauthorizedException extends RuntimeException{
    
    private static final String DESCRIPTION = "Unauthorized Exception (401)";

    public UnauthorizedException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
