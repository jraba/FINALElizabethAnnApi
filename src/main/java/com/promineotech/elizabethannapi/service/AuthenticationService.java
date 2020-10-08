package com.promineotech.elizabethannapi.service;

import javax.security.sasl.AuthenticationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.promineotech.elizabethannapi.entity.Customer;
import com.promineotech.elizabethannapi.repository.CustomerRepository;

@Service
public class AuthenticationService {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LogManager.getLogger(AuthenticationService.class);

	@Autowired
	private CustomerRepository customerRepo;

	public Customer register(Customer credentials) throws AuthenticationException {
		Customer customer = new Customer();
		customer.setUsername(credentials.getUsername());
		customer.setHashPassword(BCrypt.hashpw(credentials.getHashPassword(), BCrypt.gensalt()));
		try {
			customerRepo.save(customer);
			return customer;
		} catch (DataIntegrityViolationException e){
			throw new AuthenticationException("Username not available.");
			
		}
		
	}
	
	public Customer login(Customer credentials) throws AuthenticationException {
		Customer foundCustomer = customerRepo.findByUsername(credentials.getUsername());
		if (foundCustomer != null && BCrypt.checkpw(credentials.getHashPassword(), foundCustomer.getHashPassword())) {
			
		}
		throw new AuthenticationException("Incorrect Username or Password");
	}
}
