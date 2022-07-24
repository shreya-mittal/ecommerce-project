package com.cts.authlogin.model;

import com.cts.authlogin.entity.Customer;

import lombok.Getter;

@Getter
public class AuthenticationResponse {
	

    private Long id;

    private String firstName;

    private String lastName;

    private String email;
    
    private String token;
    
    public AuthenticationResponse(Customer cust, String token ) {
    	this.id = cust.getId();
    	this.firstName = cust.getFirstName();
    	this.lastName = cust.getLastName();
    	this.email = cust.getEmail();
    	this.token = token;
    	
    }
}
