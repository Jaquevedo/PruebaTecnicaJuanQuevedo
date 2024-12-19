package com.uniandes.pruebatecnica.exeptionshandler;

public class ForbiddenExeption extends RuntimeException{
    
    private static final String DESCRIPTION = "Forbidden Exception (403)";

    public ForbiddenExeption(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
