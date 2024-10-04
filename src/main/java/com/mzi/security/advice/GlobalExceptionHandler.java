package com.mzi.security.advice;

import com.mzi.security.response.ResponseApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseApi<String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        ResponseApi<String> response = new ResponseApi<>(
                false,
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Handle any other unhandled exceptions (fallback)
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ResponseApi<String>> handleBookNotFoundException(BookNotFoundException ex) {
        ResponseApi<String> response = new ResponseApi<>(
                false,
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    // Generic Exception Handler (For other unexpected exceptions)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseApi<String>> handleGlobalException(Exception ex) {
        // Use the error constructor for ResponseApi
        ResponseApi<String> response = new ResponseApi<>(
                false,                     // success is false
                HttpStatus.INTERNAL_SERVER_ERROR,  // 500 Internal Server Error
                "An unexpected error occurred: " + ex.getMessage() // Custom message
        );

        // Return the response wrapped in a ResponseEntity with 500 status
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    // Handle ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseApi<String>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        // Create error response using the constructor without data
        ResponseApi<String> response = new ResponseApi<>(
                false,
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );

        // Return ResponseEntity with 404 status
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}

