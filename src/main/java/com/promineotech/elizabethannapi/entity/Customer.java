package com.promineotech.elizabethannapi.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.promineotech.elizabethannapi.entity.Orders;
import com.promineotech.elizabethannapi.util.NewsletterSubscriber;
import com.promineotech.elizabethannapi.entity.Address;

@Entity
public class Customer {

	private Long id;
	private String email;
	private String firstName;
	private String lastName;
	private String username;
	private String hashPassword;
	private Address address;
	private NewsletterSubscriber newsletterSubscriber;
	private Set<Orders> orders;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getHashPassword() {
		return hashPassword;
	}

	public void setHashPassword(String hashPassword) {
		this.hashPassword = hashPassword;

	}

	@OneToMany(mappedBy = "customer")
	public Set<Orders> getOrders() {
		return orders;
	}

	public void setOrders(Set<Orders> orders) {
		this.orders = orders;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;

	}

	public NewsletterSubscriber getNewsletterSubscriber() {
		return newsletterSubscriber;
	}

	public void setNewsletterSubscriber(NewsletterSubscriber newsletterSubscriber) {
		this.newsletterSubscriber = newsletterSubscriber;
	}

	@Column(unique = true)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}