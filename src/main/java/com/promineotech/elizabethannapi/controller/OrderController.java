package com.promineotech.elizabethannapi.controller;

import java.util.Set;

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

import com.promineotech.elizabethannapi.entity.Orders;
import com.promineotech.elizabethannapi.service.OrderService;
import com.promineotech.elizabethannapi.util.OrderStatus;

@RestController
@RequestMapping("customer/{customerId}/design/{designId}/order")
public class OrderController {
	
	private static final Logger logger = LogManager.getLogger(OrderController.class);

	@Autowired 
	public OrderService service;

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> createOrder(@RequestBody Set<Long> productIds, @PathVariable Long customerId, @PathVariable Long designId) {
		try {
			return new ResponseEntity<Object>(service.createNewOrder(productIds, customerId, designId), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value="/{orderId}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateOrder(@RequestBody Orders order, @PathVariable Long orderId) {
		try {
			if (order.getOrderStatus().equals(OrderStatus.CANCELED)) {
				return new ResponseEntity<Object>(service.cancelOrder(orderId), HttpStatus.OK);
			} else if (order.getOrderStatus().equals(OrderStatus.DELIVERED)) {
				return new ResponseEntity<Object>(service.completeOrder(orderId), HttpStatus.OK);
			}
			return new ResponseEntity<Object>("Invalid update request.", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}