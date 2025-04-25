package com.khatabook.khatabook_backend.service.Impl;

import com.khatabook.khatabook_backend.dto.TransactionDto;
import com.khatabook.khatabook_backend.entity.BusinessCustomer;
import com.khatabook.khatabook_backend.entity.Transaction;
import com.khatabook.khatabook_backend.repository.BusinessCustomerRepository;
import com.khatabook.khatabook_backend.repository.TransactionRepository;
import com.khatabook.khatabook_backend.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final BusinessCustomerRepository businessCustomerRepository;

    @Override
    public TransactionDto createTransaction(TransactionDto dto) {
        BusinessCustomer businessCustomer = businessCustomerRepository.findById(dto.getBusinessCustomerId())
                .orElseThrow(() -> new RuntimeException("BusinessCustomer not found"));
    
        // 1. Create the transaction
        Transaction transaction = new Transaction();
        transaction.setAmount(dto.getAmount());
        transaction.setTransactionType(dto.getTransactionType());
        transaction.setTransactionDate(dto.getTransactionDate() != null ? dto.getTransactionDate() : LocalDateTime.now());
        transaction.setDescription(dto.getDescription());
        transaction.setBusinessCustomer(businessCustomer);
    
        transactionRepository.save(transaction);
    
        // 2. Update the customer's balance based on transaction type
        double updatedBalance = businessCustomer.getBalance();
    
        switch (dto.getTransactionType()) {
            case CREDIT:
                updatedBalance += dto.getAmount();  // Customer gave money to business (you owe more)
                break;
            case DEBIT:
                updatedBalance -= dto.getAmount();  // Customer paid money (you owe less)
                break;
            default:
                throw new IllegalArgumentException("Unknown Transaction Type");
        }
    
        businessCustomer.setBalance(updatedBalance);
        businessCustomerRepository.save(businessCustomer);
    
        
        dto.setId(transaction.getTransactionId());
        return dto;
    }
    
    @Override
    public List<TransactionDto> getTransactionsByBusinessCustomerId(Long businessCustomerId) {
        return transactionRepository.findByBusinessCustomerId(businessCustomerId)
                .stream()
                .map((Transaction tx) -> {
                    TransactionDto dto = new TransactionDto();
                    dto.setId(tx.getTransactionId());
                    dto.setAmount(tx.getAmount());
                    dto.setTransactionType(tx.getTransactionType());
                    dto.setTransactionDate(tx.getTransactionDate());
                    dto.setDescription(tx.getDescription());
                    dto.setBusinessCustomerId(tx.getBusinessCustomer().getId());
                    return dto;
                }).collect(Collectors.toList());
    }

} 
