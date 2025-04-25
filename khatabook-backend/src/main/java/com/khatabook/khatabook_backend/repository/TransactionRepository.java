package com.khatabook.khatabook_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khatabook.khatabook_backend.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findByBusinessCustomerId(Long businessCustomerId);
}
