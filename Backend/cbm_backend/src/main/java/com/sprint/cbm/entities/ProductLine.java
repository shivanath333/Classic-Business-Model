package com.sprint.cbm.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class ProductLine{
	 @Id
	
	    @Column(name = "product_line")
	    private String productLine;

	 @Column(length=1000)
	    private String description;

	    
	    @OneToMany(mappedBy = "productLine",fetch = FetchType.LAZY)
	    @JsonManagedReference
//	    @JsonIgnore
	    private List<Product> products;


		public ProductLine() {
			super();
			// TODO Auto-generated constructor stub
		}


		public ProductLine(String productLine, String description, List<Product> products) {
			super();
			this.productLine = productLine;
			this.description = description;
			this.products = products;
		}
		


		public ProductLine(String productLine, String description) {
			super();
			this.productLine = productLine;
			this.description = description;
		}


		public String getProductLine() {
			return productLine;
		}


		public void setProductLine(String productLine) {
			this.productLine = productLine;
		}


		public String getDescription() {
			return description;
		}


		public void setDescription(String description) {
			this.description = description;
		}


		public List<Product> getProducts() {
			return (List<Product>) products;
		}


		public void setProducts(List<Product> products) {
			this.products = products;
		}


		@Override
		public String toString() {
			return "ProductLine [productLine=" + productLine + ", description=" + description + ", products=" + products
					+ "]";
		}
	
	    
	    

}
