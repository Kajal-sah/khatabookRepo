package com.khatabook.khatabook_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khatabook.khatabook_backend.entity.Business;
import com.khatabook.khatabook_backend.entity.User;

public interface BusinessRepository  extends JpaRepository<Business,Long> {
    List<Business> findByUser(User user);
    
}
