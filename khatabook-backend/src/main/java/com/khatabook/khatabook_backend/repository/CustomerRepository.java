package com.khatabook.khatabook_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khatabook.khatabook_backend.entity.Business;
import com.khatabook.khatabook_backend.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    //List<Customer> findByBusiness(Business business);
    Optional<Customer> findByEmail(String email);
    
}
