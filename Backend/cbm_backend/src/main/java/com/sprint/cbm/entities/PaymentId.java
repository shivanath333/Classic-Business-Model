package com.sprint.cbm.entities;

import java.io.Serializable;
import java.util.Objects;

public class PaymentId implements Serializable {

	private Customer customer;

	private String checkNumber;

	public PaymentId() {
		super();
		// TODO Auto-generated constructor stub
	}



	public PaymentId(Customer customer, String checkNumber) {
		super();
		this.customer = customer;
		this.checkNumber = checkNumber;
	}

	public PaymentId(int customerId, String checkNumber) {
        this.customer = new Customer();
        this.customer.setId(customerId);
        this.checkNumber = checkNumber;
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PaymentId paymentId = (PaymentId) o;
		return Objects.equals(customer, paymentId.customer) && Objects.equals(checkNumber, paymentId.checkNumber);
	}

	@Override
	public int hashCode() {
		return Objects.hash(customer, checkNumber);
	}

	@Override
	public String toString() {
		return "PaymentId [customer=" + customer + ", checkNumber=" + checkNumber + "]";
	}

}
