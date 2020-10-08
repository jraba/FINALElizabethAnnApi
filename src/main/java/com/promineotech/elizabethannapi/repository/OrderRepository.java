package com.promineotech.elizabethannapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.promineotech.elizabethannapi.entity.Orders;

public interface OrderRepository extends CrudRepository<Orders, Long>{

}
