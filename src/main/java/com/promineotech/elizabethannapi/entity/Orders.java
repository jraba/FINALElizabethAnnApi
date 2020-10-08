package com.promineotech.elizabethannapi.entity;

import java.sql.Date;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.promineotech.elizabethannapi.entity.Customer;
import com.promineotech.elizabethannapi.entity.Product;
import com.promineotech.elizabethannapi.util.OrderStatus;

@Entity
public class Orders {

	private Long id;
	private double totalCost;
	private Date dateOrdered;// 2020-09-30
	private OrderStatus orderStatus;
	private Design design;
	private Set<Product> products;

	@JsonIgnore
	private Customer customer;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	@ManyToOne
	@JoinColumn(name = "customerId")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToMany(mappedBy = "orders")
	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Date getDateOrdered() {
		return dateOrdered;
	}

	public void setDateOrdered(Date date) {
		this.dateOrdered = date;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus delivered) {
		this.orderStatus = delivered;
	}

	public void setDesign(Design design) {
		this.design = design;
	}

	@ManyToOne
	@JoinColumn(name = "designId")
	public Design getDesign() {
		return design;

	}
}
