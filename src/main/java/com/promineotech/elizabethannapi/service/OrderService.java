package com.promineotech.elizabethannapi.service;

import java.util.Calendar;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.elizabethannapi.repository.CustomerRepository;
import com.promineotech.elizabethannapi.repository.DesignRepository;
import com.promineotech.elizabethannapi.repository.OrderRepository;
import com.promineotech.elizabethannapi.repository.ProductRepository;
import com.promineotech.elizabethannapi.service.OrderService;
import com.promineotech.elizabethannapi.service.DesignService;
import com.promineotech.elizabethannapi.entity.Customer;
import com.promineotech.elizabethannapi.entity.Design;
import com.promineotech.elizabethannapi.entity.Orders;
import com.promineotech.elizabethannapi.entity.Product;
import com.promineotech.elizabethannapi.util.OrderStatus;

@Service
public class OrderService {

	private static final Logger logger = LogManager.getLogger(OrderService.class);

	@Autowired
	private OrderRepository repo;

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private DesignRepository designRepo;
	

	public Orders createNewOrder(Set<Long> productIds, Long customerId, Long designId) throws Exception {
		try {
			Customer customer = customerRepo.findOne(customerId);
			Design design = designRepo.findOne(designId);
			Orders order = loadNewOrder(productIds, customer, design);
			return repo.save(order);
		} catch (Exception e) {
			logger.error("Issue occurred while trying to create new order for customer: " + customerId, e);
			throw e;
		}
	}

	public Orders cancelOrder(Long orderId) throws Exception {
		try {
			Orders order = repo.findOne(orderId);
			order.setOrderStatus(OrderStatus.CANCELED);
			return repo.save(order);
		} catch (Exception e) {
			logger.error("Issue occurred while trying to cancel order: " + orderId, e);
			throw new Exception("Order update was unsuccessful.");

		}
	}

	public Orders completeOrder(Long orderId) throws Exception {
		try {
			Orders order = repo.findOne(orderId);
			order.setOrderStatus(OrderStatus.DELIVERED);
			return repo.save(order);
		} catch (Exception e) {
			logger.error("Issue occurred while trying to complete order: " + orderId, e);
			throw new Exception("Order update was unsuccessful.");
		}
	}

	private Orders loadNewOrder(Set<Long> productIds, Customer customer, Design design) {
		Orders order = new Orders();
		order.setProducts(convertToProductSet(productRepo.findAll(productIds)));
		order.setCustomer(customer);
		order.setDesign(design);
		order.setTotalCost(addTotalCost(order.getProducts()));
		order.setOrderStatus(OrderStatus.ORDERED);
		order.setDateOrdered(new Date(Calendar.getInstance().getTimeInMillis()));
		addOrderToProducts(order);
		return order;
	}

	private void addOrderToProducts(Orders order) {
		Set<Product> products = order.getProducts();
		for (Product product : products) {
			product.getOrders().add(order);
		}
	}

	private Set<Product> convertToProductSet(Iterable<Product> iterable) {
		Set<Product> set = new HashSet<Product>();
		for (Product product : iterable) {
			set.add(product);
		}
		return set;
	}

	private double addTotalCost(Set<Product> products) {
		double total = 0;
		for (Product product : products) {
			total += product.getPrice();
		}

		return total + total * 0.6; //tax

	}

}