package com.khatabook.khatabook_backend.mapper;

import com.khatabook.khatabook_backend.dto.BusinessDTO;
import com.khatabook.khatabook_backend.dto.BusinessResponseDTO;
import com.khatabook.khatabook_backend.entity.Business;

public class BusinessMapper {

      public static BusinessDTO mapToBusinessDto(Business business){

        BusinessDTO businessDTO = new BusinessDTO(
               business.getBusinessName(),
               business.getRegistrationNumber(),
               business.getAddress(),
               business.getContactInfo(),
               business.getBusinessId()
        );

        return businessDTO;
        
      }

      public static BusinessResponseDTO mapToBusinessResponseDto(Business business){

        BusinessResponseDTO businessResponseDTO = new BusinessResponseDTO(
               business.getBusinessId(),
               business.getBusinessName(),
               business.getRegistrationNumber(),
               business.getAddress(),
               business.getContactInfo()
        );

        return businessResponseDTO;
        
      }

      //public static Business 
    
}
