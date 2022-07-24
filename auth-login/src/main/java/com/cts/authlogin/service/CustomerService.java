package com.cts.authlogin.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.authlogin.dao.CustomerRepository;
import com.cts.authlogin.entity.Customer;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private JwtUtil jwtTokenUtil;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public Customer findByEmailAndPassword(String email, String password) throws Exception {
		Customer user = customerRepository.findByEmail(email);

		if (user == null || !passwordEncoder.matches(password, user.getUser_password())) {
			throw new Exception("User not found with given details");
		}

		return user;
	}

	public String generateToken(Customer user) {
		MyUserDetails userDetails = new MyUserDetails(user.getEmail(), user.getUser_password());

		final String token = jwtTokenUtil.generateToken(userDetails);
		return token;
	}
	
	@Transactional
		public Customer register(Customer user) throws Exception {
			user.setUser_password(passwordEncoder.encode(user.getUser_password()));
			try {
				return customerRepository.save(user);
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("Invalid credientails!!!");
			}
//			System.out.println(user);
//			return user;
		}
}
