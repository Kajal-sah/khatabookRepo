package com.khatabook.khatabook_backend.service.Impl;

import com.khatabook.khatabook_backend.dto.CustomerDto;
import com.khatabook.khatabook_backend.dto.BusinessCustomerDto;
import com.khatabook.khatabook_backend.entity.Business;
import com.khatabook.khatabook_backend.entity.Customer;
import com.khatabook.khatabook_backend.entity.BusinessCustomer;
import com.khatabook.khatabook_backend.repository.BusinessRepository;
import com.khatabook.khatabook_backend.repository.CustomerRepository;
import com.khatabook.khatabook_backend.repository.BusinessCustomerRepository;
import com.khatabook.khatabook_backend.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final BusinessRepository businessRepository;
    private final CustomerRepository customerRepository;
    private final BusinessCustomerRepository businessCustomerRepository;

    @Override
    public BusinessCustomerDto addCustomerToBusiness(Long businessId, CustomerDto customerDto) {
        Business business = businessRepository.findById(businessId)
                .orElseThrow(() -> new RuntimeException("Business not found"));

        Customer customer = customerRepository.findByEmail(customerDto.getEmail())
                .orElseGet(() -> createCustomer(customerDto));

        BusinessCustomer businessCustomer = new BusinessCustomer();
        businessCustomer.setBusiness(business);
        businessCustomer.setCustomer(customer);
        businessCustomer.setBalance(0.0); // Initialize balance to 0

        BusinessCustomer savedBusinessCustomer = businessCustomerRepository.save(businessCustomer);

        return new BusinessCustomerDto(savedBusinessCustomer.getId(), customer.getName(), business.getBusinessName(), savedBusinessCustomer.getBalance());
    }

    @Override
    public List<CustomerDto> getCustomersByBusinessId(Long businessId) {
        List<BusinessCustomer> businessCustomers = businessCustomerRepository.findByBusinessId(businessId);
        return businessCustomers.stream()
                .map(businessCustomer -> new CustomerDto(
                        //businessCustomer.getCustomer().getCustomerId(),
                        businessCustomer.getCustomer().getName(),
                        businessCustomer.getCustomer().getEmail(),
                        businessCustomer.getCustomer().getPhoneNumber(),
                        businessCustomer.getCustomer().getAddress()
                 
                ))
                .collect(Collectors.toList());
    }

    private Customer createCustomer(CustomerDto customerDto) {
        Customer newCustomer = new Customer();
        newCustomer.setName(customerDto.getName());
        newCustomer.setEmail(customerDto.getEmail());
        newCustomer.setPhoneNumber(customerDto.getPhoneNumber());
        newCustomer.setAddress(customerDto.getAddress());
        return customerRepository.save(newCustomer);
    }
}
