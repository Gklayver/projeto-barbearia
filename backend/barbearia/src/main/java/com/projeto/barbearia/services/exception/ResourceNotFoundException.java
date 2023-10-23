package com.projeto.barbearia.services.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String msg ) {
        super(msg);
    }
}

