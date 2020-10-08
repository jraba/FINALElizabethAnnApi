package com.promineotech.elizabethannapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.promineotech.elizabethannapi.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	public Customer findByUsername (String username);
}
