package com.khatabook.khatabook_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khatabook.khatabook_backend.dto.BusinessDTO;
import com.khatabook.khatabook_backend.dto.BusinessResponseDTO;
import com.khatabook.khatabook_backend.entity.User;
import com.khatabook.khatabook_backend.service.BusinessService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/businesses")
@RequiredArgsConstructor
public class BusinessController {

    private final BusinessService businessService;

    @PostMapping
    public ResponseEntity<BusinessDTO> createBusiness(@RequestBody BusinessDTO business,
                                                   @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(businessService.createBusiness(user, business));
    }


    @GetMapping
    public ResponseEntity<List<BusinessResponseDTO>> getUserBusinesses(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(businessService.getBusinessesForUser(user));
    }

}    