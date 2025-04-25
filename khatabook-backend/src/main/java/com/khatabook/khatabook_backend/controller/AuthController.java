package com.khatabook.khatabook_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khatabook.khatabook_backend.dto.AuthRequest;
import com.khatabook.khatabook_backend.dto.AuthResponse;
import com.khatabook.khatabook_backend.dto.RegisterRequest;
import com.khatabook.khatabook_backend.service.AuthService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {


     
   private  AuthService authService;
    
   @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest){
        String response = authService.register(registerRequest);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }


     @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        // Call the AuthService to handle the login logic
        AuthResponse authResponse = authService.login(request);

        // Return the AuthResponse containing the JWT token
        return ResponseEntity.ok(authResponse);
    }

    
}
