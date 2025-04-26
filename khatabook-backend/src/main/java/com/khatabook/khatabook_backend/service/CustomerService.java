package com.khatabook.khatabook_backend.service;

import com.khatabook.khatabook_backend.dto.CustomerDto;
import com.khatabook.khatabook_backend.dto.BusinessCustomerDto;

import java.util.List;

public interface CustomerService {

    BusinessCustomerDto addCustomerToBusiness(Long businessId, CustomerDto customerDto);

    List<CustomerDto> getCustomersByBusinessId(Long businessId);

    Double getCustomerBalanceForBusiness(Long businessId, Long customerId);
}
