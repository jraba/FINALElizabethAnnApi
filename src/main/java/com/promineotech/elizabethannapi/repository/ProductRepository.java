package com.promineotech.elizabethannapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.promineotech.elizabethannapi.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
