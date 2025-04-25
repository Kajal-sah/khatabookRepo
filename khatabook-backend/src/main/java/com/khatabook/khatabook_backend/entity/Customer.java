package com.khatabook.khatabook_backend.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer {
    
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;
    
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private Double balance;

    @OneToMany
    @JoinColumn(name = "id")
    private List<BusinessCustomer> businessCustomers;

   
    
}
