package com.sprint.cbm.entities;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@IdClass(PaymentId.class)
public class Payments {
	@Id

	@ManyToOne
	@JoinColumn(name = "customer_id")
	@JsonBackReference(value = "customer-payment")
//	@JsonIgnore
	private Customer customer;

	@Id
	private String checkNumber;

	@Temporal(TemporalType.DATE)

	private Date paymentDate;

	private double amount;

	public Payments() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Payments [customer=" + customer + ", checkNumber=" + checkNumber + ", paymentDate=" + paymentDate
				+ ", amount=" + amount + "]";
	}
}