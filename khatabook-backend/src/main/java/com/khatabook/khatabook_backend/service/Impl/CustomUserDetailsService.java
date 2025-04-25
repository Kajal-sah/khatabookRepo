package com.khatabook.khatabook_backend.service.Impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.khatabook.khatabook_backend.repository.UserRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

     private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username){

       return userRepository.findByUsername(username)
                            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    

    
}
