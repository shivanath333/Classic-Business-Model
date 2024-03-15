package com.sprint.cbm.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@IdClass(OrderDetailsId.class)
public class OrderDetails {

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_code")
	@JsonBackReference(value = "product-orderDetails")

	private Product product;

	@ManyToOne
	@Id
	@JoinColumn(name = "order_number")
	@JsonBackReference(value = "order-orderDetails")

	private Order orders;

	private int quantityOrdered;

	private double priceEach;

	private int orderLineNumber;

	public OrderDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderDetails(Product product, Order orders, int quantityOrdered, double priceEach, int orderLineNumber) {
		super();
		this.product = product;
		this.orders = orders;
		this.quantityOrdered = quantityOrdered;
		this.priceEach = priceEach;
		this.orderLineNumber = orderLineNumber;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrders() {
		return orders;
	}

	public void setOrders(Order orders) {
		this.orders = orders;
	}

	public int getQuantityOrdered() {
		return quantityOrdered;
	}

	public void setQuantityOrdered(int quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

	public double getPriceEach() {
		return priceEach;
	}

	public void setPriceEach(double priceEach) {
		this.priceEach = priceEach;
	}

	public int getOrderLineNumber() {
		return orderLineNumber;
	}

	public void setOrderLineNumber(int orderLineNumber) {
		this.orderLineNumber = orderLineNumber;
	}

	@Override
	public String toString() {
		return "OrderDetails [product=" + product + ", orders=" + orders + ", quantityOrdered=" + quantityOrdered
				+ ", priceEach=" + priceEach + ", orderLineNumber=" + orderLineNumber + "]";
	}

}