package com.nisum.backendtest.exceptions;

public class GenericException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public GenericException(String message){
        super(message);
    }
}
