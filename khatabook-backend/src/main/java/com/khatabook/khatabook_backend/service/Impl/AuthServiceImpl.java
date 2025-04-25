package com.khatabook.khatabook_backend.service.Impl;


import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.khatabook.khatabook_backend.dto.AuthRequest;
import com.khatabook.khatabook_backend.dto.AuthResponse;
import com.khatabook.khatabook_backend.dto.RegisterRequest;
import com.khatabook.khatabook_backend.entity.User;
import com.khatabook.khatabook_backend.exception.UserNotFoundException;
import com.khatabook.khatabook_backend.repository.UserRepository;
import com.khatabook.khatabook_backend.security.JwtUtil;
import com.khatabook.khatabook_backend.service.AuthService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

     private UserRepository userRepository;
     private PasswordEncoder passwordEncoder;

     private JwtUtil jwtUtil;
     private AuthenticationManager authManager;
     private CustomUserDetailsService userDetailsService;

    @Override
    public String register(RegisterRequest request) {
        
        if(userRepository.existsByUsername(request.getUsername())){
            throw new UserNotFoundException(HttpStatus.BAD_REQUEST ,"User already exist");
    
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        
      return "User Registered Successfully";                  
        
    }

    @Override
    public AuthResponse login(AuthRequest authRequest) {
         //  Authenticate the user
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        //  Load user details after successful authentication
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

        // Generate JWT token
        final String jwt = jwtUtil.generateToken(userDetails);

        //  Return AuthResponse with JWT token
        return new AuthResponse(jwt);
    }




    
}
