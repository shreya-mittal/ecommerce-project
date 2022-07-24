package com.cts.authlogin.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.authlogin.entity.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByEmail(String theEmail);
}
