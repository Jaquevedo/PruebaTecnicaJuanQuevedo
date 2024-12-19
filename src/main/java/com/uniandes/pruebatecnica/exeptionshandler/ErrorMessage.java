package com.uniandes.pruebatecnica.exeptionshandler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ErrorMessage {

    private String exception;
    private String message;
    private String path;

    public ErrorMessage(Exception exception, String path) {
        this.exception = exception.getClass().getSimpleName();
        this.message = exception.getMessage();
        this.path = path;
    }

    @Override
    public String toString() {
        return "ErrorMessage{"
                + "exception='" + exception + '\''
                + ", message='" + message + '\''
                + ", path='" + path + '\''
                + '}';
    }
}
