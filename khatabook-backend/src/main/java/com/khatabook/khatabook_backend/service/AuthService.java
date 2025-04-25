package com.khatabook.khatabook_backend.service;



import com.khatabook.khatabook_backend.dto.AuthRequest;
import com.khatabook.khatabook_backend.dto.AuthResponse;
import com.khatabook.khatabook_backend.dto.RegisterRequest;

public interface AuthService {

    String register(RegisterRequest request);
    AuthResponse login(AuthRequest authRequest);
    
    
}
