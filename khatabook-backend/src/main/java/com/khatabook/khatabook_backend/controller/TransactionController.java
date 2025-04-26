package com.khatabook.khatabook_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khatabook.khatabook_backend.dto.TransactionDto;
import com.khatabook.khatabook_backend.service.CustomerService;
import com.khatabook.khatabook_backend.service.TransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto transactionDto) {
        TransactionDto createdTransaction = transactionService.createTransaction(transactionDto);
        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
    }

    @GetMapping("/business-customer/{id}")
    public ResponseEntity<List<TransactionDto>> getTransactionsByBusinessCustomerId(@PathVariable("id") Long businessCustomerId) {
        List<TransactionDto> transactions = transactionService.getTransactionsByBusinessCustomerId(businessCustomerId);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/business/{businessId}/customer/{customerId}")
    public Double getCustomerBalance(@PathVariable Long businessId, @PathVariable Long customerId) {
        return customerService.getCustomerBalanceForBusiness(businessId, customerId);
    }


   // @GetMapping
}

