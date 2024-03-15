package com.sprint.cbm.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

	@Id

	private int id;

	private String name;

	private String contactFirstName;

	private String contactLastName;

	private String phone;
	private String addressLine1;
	private String addressLine2;

	private String city;
	private String state;

	private String postalCode;

	private String country;
	private double creditLimit;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employeeRep")
	@JsonBackReference(value = "employees-customer")
	private Employees employeeRep;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	@JsonManagedReference(value = "customer-payment")

	private List<Payments> payment;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)

	@JsonManagedReference
	private List<Order> orders;

	@JsonIgnore
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
	private CustomerAccount customerAccount;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(int id, String name, String contactFirstName, String contactLastName, String phone,
			String addressLine1, String addressLine2, String city, String state, String postalCode, String country,
			double creditLimit, Employees employeeRep, List<Payments> payment, List<Order> orders,
			CustomerAccount customerAccount) {
		super();
		this.id = id;
		this.name = name;
		this.contactFirstName = contactFirstName;
		this.contactLastName = contactLastName;
		this.phone = phone;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.country = country;
		this.creditLimit = creditLimit;
		this.employeeRep = employeeRep;
		this.payment = payment;
		this.orders = orders;
		this.customerAccount = customerAccount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactFirstName() {
		return contactFirstName;
	}

	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}

	public String getContactLastName() {
		return contactLastName;
	}

	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}

	public Employees getEmployeeRep() {
		return employeeRep;
	}

	public void setEmployeeRep(Employees employeeRep) {
		this.employeeRep = employeeRep;
	}

	public List<Payments> getPayment() {
		return payment;
	}

	public void setPayment(List<Payments> payment) {
		this.payment = payment;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", contactFirstName=" + contactFirstName + ", contactLastName="
				+ contactLastName + ", phone=" + phone + ", addressLine1=" + addressLine1 + ", addressLine2="
				+ addressLine2 + ", city=" + city + ", state=" + state + ", postalCode=" + postalCode + ", country="
				+ country + ", creditLimit=" + creditLimit + ", employeeRep=" + employeeRep + ", payment=" + payment
				+ ", orders=" + orders + ", customerAccount=" + customerAccount + "]";
	}

}
