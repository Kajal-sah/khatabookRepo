package com.khatabook.khatabook_backend.mapper;

import com.khatabook.khatabook_backend.dto.CustomerDto;
import com.khatabook.khatabook_backend.entity.Customer;

public class CustomerMapper {

    // public static CustomerDto maptoCustomerDto(Customer customer){
    //     CustomerDto customerDto = new CustomerDto(
    //                 customer.getCustomerId(),
    //                 customer.getName(),
    //                 customer.getEmail(),
    //                 customer.getPhoneNumber(),
    //                 customer.getAddress(),
    //                 customer.getBalance()
                   
    //     );
    //     return customerDto;
    // }

    public static Customer mapToCustomer(CustomerDto customerDto){
        Customer customer = new Customer();
        //customer.setCustomerId(customerDto.getCustomerId());
        customer.setAddress(customerDto.getAddress());
        customer.setEmail(customerDto.getEmail());
        customer.setName(customerDto.getName());
        customer.setPhoneNumber(customerDto.getPhoneNumber());


        return customer;
    }
    
}
