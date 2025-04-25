package com.khatabook.khatabook_backend.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.khatabook.khatabook_backend.dto.BusinessCustomerDto;
import com.khatabook.khatabook_backend.dto.BusinessDTO;
import com.khatabook.khatabook_backend.dto.BusinessResponseDTO;
import com.khatabook.khatabook_backend.dto.CustomerDto;
import com.khatabook.khatabook_backend.entity.Business;
import com.khatabook.khatabook_backend.entity.BusinessCustomer;
import com.khatabook.khatabook_backend.entity.Customer;
import com.khatabook.khatabook_backend.entity.User;
import com.khatabook.khatabook_backend.mapper.BusinessMapper;
import com.khatabook.khatabook_backend.repository.BusinessCustomerRepository;
import com.khatabook.khatabook_backend.repository.BusinessRepository;
import com.khatabook.khatabook_backend.repository.CustomerRepository;
import com.khatabook.khatabook_backend.service.BusinessService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BusinessServiceImpl implements BusinessService {

    private final BusinessRepository businessRepository;
    private final CustomerRepository customerRepository;
    private final BusinessCustomerRepository businessCustomerRepository;

    @Override
    public BusinessDTO createBusiness(User user, BusinessDTO businessDto) {
        Business business = new Business();
        business.setAddress(businessDto.getAddress());
        business.setBusinessName(businessDto.getBusinessName());
        business.setRegistrationNumber(businessDto.getRegistrationNumber());
        business.setContactInfo(businessDto.getContactInfo());
        business.setUser(user);

        Business savedBusiness = businessRepository.save(business);
        return BusinessMapper.mapToBusinessDto(savedBusiness);
    }

    public List<BusinessResponseDTO> getBusinessesForUser(User user) {
        List<Business> businesses =businessRepository.findByUser(user);
        return businesses.stream().map((business)->BusinessMapper.mapToBusinessResponseDto(business)).collect(Collectors.toList());
    }


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

    private Customer createCustomer(CustomerDto customerDto) {
        Customer newCustomer = new Customer();
        newCustomer.setName(customerDto.getName());
        newCustomer.setEmail(customerDto.getEmail());
        newCustomer.setPhoneNumber(customerDto.getPhoneNumber());
        newCustomer.setAddress(customerDto.getAddress());
        return customerRepository.save(newCustomer);
    }

   
    
}
