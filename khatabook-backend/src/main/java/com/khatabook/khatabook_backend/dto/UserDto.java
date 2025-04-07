package com.khatabook.khatabook_backend.dto;

import java.util.List;

import com.khatabook.khatabook_backend.entity.Business;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    
    private Long userId;
    private String username;
    private String gmail;
    private String address;
    private String password;
    private List<Business> businesses;

}
