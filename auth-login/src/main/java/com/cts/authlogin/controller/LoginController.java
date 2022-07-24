package com.cts.authlogin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.authlogin.entity.Customer;
import com.cts.authlogin.model.AuthenticationRequest;
import com.cts.authlogin.model.AuthenticationResponse;
import com.cts.authlogin.service.CustomerService;

@RestController
public class LoginController {

	@Autowired
	private CustomerService service;

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) throws Exception {
		try {
			Customer user = service.findByEmailAndPassword(request.getEmail(), request.getPassword());
			final String token = service.generateToken(user);

			AuthenticationResponse response = new AuthenticationResponse(user,token);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody Customer user) {
		try {

			Customer newUser = service.register(user);
			final String token = service.generateToken(newUser);

			AuthenticationResponse response = new AuthenticationResponse(user,token);

			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

}
