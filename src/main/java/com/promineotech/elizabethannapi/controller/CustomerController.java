package com.promineotech.elizabethannapi.controller;

import javax.security.sasl.AuthenticationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.elizabethannapi.service.AuthenticationService;
import com.promineotech.elizabethannapi.service.CustomerService;
import com.promineotech.elizabethannapi.service.OrderService;
import com.promineotech.elizabethannapi.entity.Customer;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	private static final Logger logger = LogManager.getLogger(CustomerController.class);

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private CustomerService service;

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public ResponseEntity<Object> register(@RequestBody Customer credentials) {
		try {
			return new ResponseEntity<Object>(authenticationService.register(credentials), HttpStatus.CREATED);
		} catch (AuthenticationException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Object> login(@RequestBody Customer credentials) {
		try {
			return new ResponseEntity<Object>(authenticationService.login(credentials), HttpStatus.OK);
		} catch (AuthenticationException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getCustomer(@PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.getCustomerById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getCustomers() {
		return new ResponseEntity<Object>(service.getCustomers(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> createCustomer(@RequestBody Customer customer) {
		return new ResponseEntity<Object>(service.createCustomer(customer), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateCustomer(@RequestBody Customer customer, @PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.updateCustomer(customer, id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteCustomer(@PathVariable Long id) {
		try {
			service.deleteCustomer(id);
			return new ResponseEntity<Object>("Successfully deleted customer: " + id, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);

		}

	}
}