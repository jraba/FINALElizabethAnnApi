package com.promineotech.elizabethannapi.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.elizabethannapi.service.CustomerService;
import com.promineotech.elizabethannapi.entity.Customer;
import com.promineotech.elizabethannapi.repository.CustomerRepository;

@Service
public class CustomerService {
	
	private static final Logger logger = LogManager.getLogger(CustomerService.class);

	@Autowired
	private CustomerRepository repo;
	
	public Customer getCustomerById(Long id) throws Exception {
		try {
			return repo.findOne(id);
		} catch (Exception e) {
			logger.error("Issue occurred while trying to retrieve customer: " + id, e);
			throw e;
		}
	}

	public Iterable<Customer> getCustomers() {
		return repo.findAll();
	}

	public Customer createCustomer(Customer customer) {
		return repo.save(customer);
	}

	public Customer updateCustomer(Customer customer, Long id) throws Exception {
		try {
			Customer oldCustomer = repo.findOne(id);
			oldCustomer.setEmail(customer.getEmail());
			oldCustomer.setFirstName(customer.getFirstName());
			oldCustomer.setLastName(customer.getLastName());
			oldCustomer.setAddress(customer.getAddress());
			oldCustomer.setHashPassword(customer.getHashPassword());
			oldCustomer.setNewsletterSubscriber(customer.getNewsletterSubscriber());
			return repo.save(oldCustomer);
		} catch (Exception e) {
			logger.error("Issue occured while trying to update customer: " + id, e);
			throw new Exception("Customer update unsuccessful.");
		}
	}

	public void deleteCustomer(Long id) throws Exception {
		try {
			repo.delete(id);
		} catch (Exception e) {
			logger.error("Issue occured while trying to delete customer: " + id, e);
			throw new Exception("Customer deletion unsuccessful.");
		}
	}
}
