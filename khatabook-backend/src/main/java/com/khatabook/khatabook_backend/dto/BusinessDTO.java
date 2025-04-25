package com.khatabook.khatabook_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessDTO {
    private String businessName;
    private String registrationNumber;
    private String address;
    private String contactInfo;
    private Long businessId;
}