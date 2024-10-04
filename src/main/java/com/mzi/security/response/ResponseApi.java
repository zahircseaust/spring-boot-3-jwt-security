package com.mzi.security.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseApi<T> {
    private boolean success;
    private HttpStatus status;
    private String message;
    private T data;

    // Constructor for success response with data
    public ResponseApi(boolean success, HttpStatus status, T data, String message) {
        this.success = success;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // Constructor for error response without data
    public ResponseApi(boolean success, HttpStatus status, String message) {
        this.success = success;
        this.status = status;
        this.message = message;
        this.data = null; // No data in case of error
    }

}

