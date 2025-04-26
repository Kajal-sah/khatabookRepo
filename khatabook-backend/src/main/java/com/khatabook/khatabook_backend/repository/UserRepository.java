package com.khatabook.khatabook_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khatabook.khatabook_backend.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

     Optional<User>findByEmail(String email);
     boolean existsByUsername(String username);
    
}
