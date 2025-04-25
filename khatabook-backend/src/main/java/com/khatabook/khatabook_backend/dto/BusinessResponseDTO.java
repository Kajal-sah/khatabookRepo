package com.khatabook.khatabook_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessResponseDTO {
    private Long  businessId;
    private String businessName;
    private String registrationNumber;
    private String address;
    private String contactInfo;

}
