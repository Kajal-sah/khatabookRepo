package com.khatabook.khatabook_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khatabook.khatabook_backend.dto.BusinessCustomerDto;
import com.khatabook.khatabook_backend.dto.CustomerDto;
import com.khatabook.khatabook_backend.service.CustomerService;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // Endpoint to add an existing customer to a business (or create a new customer if not found)
    @PostMapping("/{businessId}")
    public ResponseEntity<BusinessCustomerDto> addCustomerToBusiness(
            @RequestBody CustomerDto customerDto,
            @PathVariable Long businessId) {
        BusinessCustomerDto createdBusinessCustomer = customerService.addCustomerToBusiness(businessId, customerDto);
        return ResponseEntity.ok(createdBusinessCustomer);
    }

    // Endpoint to get all customers for a specific business
    @GetMapping("/{businessId}/customers")
    public ResponseEntity<List<CustomerDto>> getCustomersByBusiness(@PathVariable Long businessId) {
        List<CustomerDto> customers = customerService.getCustomersByBusinessId(businessId);
        return ResponseEntity.ok(customers);
    }

   
}