package com.khatabook.khatabook_backend.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.khatabook.khatabook_backend.entity.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    
    private Long id;
    private Double amount;
    private TransactionType transactionType;
    private LocalDateTime transactionDate;
    private String description;
    private Long businessCustomerId;
  
}
