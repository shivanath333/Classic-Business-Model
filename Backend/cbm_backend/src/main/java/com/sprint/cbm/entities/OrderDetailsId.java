package com.sprint.cbm.entities;

import java.io.Serializable;

public class OrderDetailsId implements Serializable{

	private Product product;
	private Order orders;
	public OrderDetailsId() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order getOrder() {
		return orders;
	}
	public void setOrder(Order order) {
		this.orders = order;
	}
	
	
}