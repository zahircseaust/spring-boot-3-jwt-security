package com.mzi.security.auth;

import com.mzi.security.response.ResponseApi;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<ResponseApi<AuthenticationResponse>> register(
            @RequestBody RegisterRequest request
    ) {
        AuthenticationResponse authResponse = service.register(request);

        // Create ResponseApi object
        ResponseApi<AuthenticationResponse> response = ResponseApi.<AuthenticationResponse>builder()
                .success(true)
                .status(HttpStatus.OK)
                .message("Registration successful")
                .data(authResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseApi<AuthenticationResponse>> authenticate(
            @RequestBody AuthenticationRequest request) {

        // Call the authentication service
        AuthenticationResponse authResponse = service.authenticate(request);

        // Create the API response with the required details
        ResponseApi<AuthenticationResponse> response = ResponseApi.<AuthenticationResponse>builder()
                .success(true)
                .status(HttpStatus.OK)
                .message("Authentication successful")
                .data(authResponse)
                .build();

        // Return the response wrapped in ResponseEntity
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }


}
