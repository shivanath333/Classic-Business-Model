package com.sprint.cbm.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.cbm.entities.Product;
import com.sprint.cbm.entities.ProductLine;
import com.sprint.cbm.repository.ProductRepo;

/**
 * Product Services.
 *
 * This service class provides various operations related to products, such as retrieving product information,
 * updating product details, and performing queries on products based on different criteria.
 */

@Service
public class ProductServices {

	@Autowired
	private ProductRepo productRepo;

	/**
	 * Retrieves all products.
	 *
	 * @return List of all products
	 */
	public List<Product> getAllProducts() {

		return productRepo.findAll();

	}
	/**
	 * Retrieves a product by name.
	 *
	 * @param name The name of the product
	 * @return The product with the specified name, or null if not found
	 */

	public Product getByName(String name) {
		return productRepo.findByName(name);
	}
	/**
	 * Retrieves a product by ID.
	 *
	 * @param id The ID of the product
	 * @return The product with the specified ID, or null if not found
	 */

	public Product getById(String id) {
		return productRepo.findByCode(id).orElse(null);
	}


	/**
	 * Retrieves a product by product name.
	 *
	 * @param productName The product name
	 * @return The product with the specified product name, or null if not found
	 */
	public Product findByProductName(String productName) {
		return productRepo.findByName(productName);
	}
	/**
	 * Retrieves products by product scale.
	 *
	 * @param productScale The product scale
	 * @return List of products with the specified product scale
	 */

	public List<Product> findByProductScale(String productScale) {
		return productRepo.findByProductScale(productScale);
	}

	/**
	 * Retrieves products by product vendor.
	 *
	 * @param productVendor The product vendor
	 * @return List of products with the specified product vendor
	 */
	public List<Product> findByProductVendor(String productVendor) {
		return productRepo.findByProductVendor(productVendor);
	}

	public double findTotalOrdered(String id) {
		return productRepo.findByTotalOrdered(id);
	}

	public double findTotalSale(String id) {
		return productRepo.findByTotalSale(id);
	}
	
	

	public LinkedList<Map<String, Double>> findTotalSaleOfProduct() {
		return productRepo.findByTotalSale();
	}

	public List<Product> findByProductDetails() {
		return productRepo.findByProductDetails();
	}
	/////////////////////////////////////////////////////////////////
	
	public Product createProduct(Product product) {
		return productRepo.save(product);
	}
	
	public List<Product> getByLine(String line) {
		return productRepo.findByProductLineProductLine(line);
		
	}
	
	public Product updateDetails(String id, Product product) {

        Product product2 = productRepo.findByCode(id).get();

        product2.setName(product.getName());
        product2.setBuyPrice(product.getBuyPrice());
        product2.setDescription(product.getDescription());
        product2.setProductScale(product.getProductScale());
        product2.setProductVendor(product.getProductVendor());
        product2.setQuantityInStock(product.getQuantityInStock());
        product2.setMsrp(product.getMsrp());

        return productRepo.save(product);
    }

	
	

}
