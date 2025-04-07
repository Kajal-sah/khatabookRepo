package com.khatabook.khatabook_backend.dto;

import java.util.List;

import com.khatabook.khatabook_backend.entity.Business;
import com.khatabook.khatabook_backend.entity.Transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDto {
    

    private Long supplierId;
    private String name;
    private String phoneNumer;
    private String email;
    private String address;
    private Double balance;

    private Business business;
    private List<Transaction> transactions;
}
