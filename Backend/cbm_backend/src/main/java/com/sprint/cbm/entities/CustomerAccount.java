package com.sprint.cbm.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_table")
public class CustomerAccount {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;
    


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @Column(nullable = false)
    private String password;
    
   private boolean isActive;
   
   @Column(nullable = false)
   private String role;

public CustomerAccount() {
	super();
	// TODO Auto-generated constructor stub
}

public CustomerAccount(Long id, String email, Customer customer, String password, boolean isActive, String role) {
	super();
	this.id = id;
	this.email = email;
	this.customer = customer;
	this.password = password;
	this.isActive = isActive;
	this.role = role;
}

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

public Customer getCustomer() {
	return customer;
}

public void setCustomer(Customer customer) {
	this.customer = customer;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public boolean isActive() {
	return isActive;
}

public void setActive(boolean isActive) {
	this.isActive = isActive;
}

public String getRole() {
	return role;
}

public void setRole() {
	this.role = "USER";
}

@Override
public String toString() {
	return "CustomerAccount [id=" + id + ", email=" + email + ", customer=" + customer + ", password=" + password
			+ ", isActive=" + isActive + ", role=" + role + "]";
}

   
   
   
}
