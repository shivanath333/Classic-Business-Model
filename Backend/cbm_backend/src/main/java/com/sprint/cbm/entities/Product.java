package com.sprint.cbm.entities;

import java.util.List;

import org.hibernate.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Product  {
	@Id
	@Column(name = "product_code")
	private String code;

	private String name;

	@ManyToOne(fetch = FetchType.EAGER)

	@JoinColumn(name = "product_line")
	@JsonBackReference
//	@JsonIgnore
	private ProductLine productLine;

	private String productScale;

	private String productVendor;

	@Column(length=1000)
	private String description;

	private int quantityInStock;

	private double buyPrice;

	private double msrp;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	@JsonManagedReference(value = "product-orderDetails")
//	@JsonIgnore
	private List<OrderDetails> details;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(String code, String name, ProductLine productLine, String productScale, String productVendor,
			String description, int quantityInStock, double buyPrice, double msrp, List<OrderDetails> details) {
		super();
		this.code = code;
		this.name = name;
		this.productLine = productLine;
		this.productScale = productScale;
		this.productVendor = productVendor;
		this.description = description;
		this.quantityInStock = quantityInStock;
		this.buyPrice = buyPrice;
		this.msrp = msrp;
		this.details = details;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductLine getProductLine() {
		return productLine;
	}

	public void setProductLine(ProductLine productLine) {
		this.productLine = productLine;
	}

	public String getProductScale() {
		return productScale;
	}

	public void setProductScale(String productScale) {
		this.productScale = productScale;
	}

	public String getProductVendor() {
		return productVendor;
	}

	public void setProductVendor(String productVendor) {
		this.productVendor = productVendor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public double getMsrp() {
		return msrp;
	}

	public void setMsrp(double msrp) {
		this.msrp = msrp;
	}

	public List<OrderDetails> getDetails() {
		return details;
	}

	public void setDetails(List<OrderDetails> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "Product [code=" + code + ", name=" + name + ", productLine=" + productLine + ", productScale="
				+ productScale + ", productVendor=" + productVendor + ", description=" + description
				+ ", quantityInStock=" + quantityInStock + ", buyPrice=" + buyPrice + ", msrp=" + msrp + ", details="
				+ details + "]";
	}

}
