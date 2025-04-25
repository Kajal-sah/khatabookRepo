package com.khatabook.khatabook_backend.service;

import java.util.List;

import com.khatabook.khatabook_backend.dto.TransactionDto;

public interface TransactionService {
    TransactionDto createTransaction(TransactionDto transactionDTO);
    List<TransactionDto> getTransactionsByBusinessCustomerId(Long businessCustomerId);
}