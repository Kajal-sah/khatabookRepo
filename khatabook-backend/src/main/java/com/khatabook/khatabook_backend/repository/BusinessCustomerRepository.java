package com.khatabook.khatabook_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.khatabook.khatabook_backend.entity.Business;
import com.khatabook.khatabook_backend.entity.BusinessCustomer;
import com.khatabook.khatabook_backend.entity.Customer;

public interface BusinessCustomerRepository extends JpaRepository<BusinessCustomer,Long> {

    @Query("SELECT bc FROM BusinessCustomer bc WHERE bc.business.businessId = :businessId")
    List<BusinessCustomer> findByBusinessId(@Param("businessId") Long businessId);

    Optional<BusinessCustomer> findByBusinessAndCustomer(Business business, Customer customer);
    
    List<BusinessCustomer> findByCustomer(Customer customer);

    @Query("SELECT bc.balance FROM BusinessCustomer bc WHERE bc.business.businessId = :businessId AND bc.customer.customerId = :customerId")
    Double findBalanceByBusinessIdAndCustomerId(@Param("businessId") Long businessId, @Param("customerId") Long customerId);

    
}
