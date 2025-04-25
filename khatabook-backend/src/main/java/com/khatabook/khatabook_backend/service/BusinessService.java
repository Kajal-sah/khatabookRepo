package com.khatabook.khatabook_backend.service;

import java.util.List;

import com.khatabook.khatabook_backend.dto.BusinessCustomerDto;
import com.khatabook.khatabook_backend.dto.BusinessDTO;
import com.khatabook.khatabook_backend.dto.BusinessResponseDTO;
import com.khatabook.khatabook_backend.dto.CustomerDto;
import com.khatabook.khatabook_backend.entity.User;

public interface BusinessService {

    BusinessDTO createBusiness(User user, BusinessDTO businessDto);

    List<BusinessResponseDTO> getBusinessesForUser(User user);

    BusinessCustomerDto addCustomerToBusiness(Long businessId, CustomerDto customerDto);
    
}
