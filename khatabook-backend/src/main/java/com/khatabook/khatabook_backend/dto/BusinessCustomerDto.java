package com.khatabook.khatabook_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessCustomerDto {

    private Long id;              
    private String customerName;  
    private String businessName;  
    private Double balance;       

}
    

