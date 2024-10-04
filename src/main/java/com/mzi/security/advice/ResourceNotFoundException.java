package com.mzi.security.advice;

public class ResourceNotFoundException extends RuntimeException  {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
