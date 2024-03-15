package com.sprint.cbm.controller;

import java.util.LinkedList;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.cbm.entities.Product;
import com.sprint.cbm.entities.ProductLine;
import com.sprint.cbm.exception.NotFoundException;
import com.sprint.cbm.services.ProductServices;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

	@Autowired
	private ProductServices productServices;

	/**
	 * Retrieves all products.
	 *
	 * @return List of all products
	 */
	@GetMapping("")

	public List<Product> getAll() {
		return productServices.getAllProducts();

	}

	/**
	 * Retrieves a product by its name.
	 *
	 * @param name The name of the product
	 * @return The product with the specified name
	 * @throws NotFoundException if the product is not found
	 */

	@GetMapping("/get/{name}")

	public Product getByName(@PathVariable String name) {
		if (productServices.getByName(name) != null) {
			return productServices.getByName(name);
		} else {
			throw new NotFoundException("Product not found with given +" + name);
		}
	}

	/**
	 * Retrieves a product by its code.
	 *
	 * @param code The code of the product
	 * @return The product with the specified code
	 * @throws NotFoundException if the product is not found
	 */

	@GetMapping("/get_code/{code}")

	public Product getByCode(@PathVariable String code) {
//		if(productServices.getById(code)!=null) {
		return productServices.getById(code);
//		}
//		else {
//			throw new NotFoundException("Product not found with given id "+code);

	}
//	}

	/**
	 * Retrieves products by scale.
	 *
	 * @param productScale The scale of the products to retrieve
	 * @return List of products with the specified scale
	 * @throws NotFoundException if no products are found
	 */

	@GetMapping("scale/{productScale}")
	public List<Product> getByScale(@PathVariable String productScale) {
		if (productServices.findByProductScale(productScale).size() > 0) {
			return productServices.findByProductScale(productScale);
		} else {
			throw new NotFoundException("No product found with scale " + productScale);
		}
	}
	

    /**
     * Retrieves products by vendor.
     *
     * @param productVendor The vendor of the products to retrieve
     * @return List of products with the specified vendor
     * @throws NotFoundException if no products are found
     */

	@GetMapping("vendor/{productVendor}")
	public List<Product> getByVendor(@PathVariable String productVendor) {
		if (productServices.findByProductVendor(productVendor).size() != 0) {
			return productServices.findByProductVendor(productVendor);
		} else
			throw new NotFoundException("No vendor found for vendor " + productVendor);
	}

	@GetMapping("/get/{id}/totalOrdered")
	public double getTotal(@PathVariable String id) {
		if (productServices.findTotalOrdered(id) != 0) {
			return productServices.findTotalOrdered(id);
		} else {
			throw new NotFoundException("no total found for  " + id);
		}
	}

	@GetMapping("/get/{id}/totalSale")
	public double getTotalSale(@PathVariable String id) {
		if (productServices.findTotalSale(id) != 0) {
			return productServices.findTotalSale(id);
		} else {
			throw new NotFoundException("no total sale found for  " + id);
		}
	}

	@GetMapping("/totalSale")
	public LinkedList<Map<String, Double>> getTotalProduct() {
		return productServices.findTotalSaleOfProduct();
	}

	@GetMapping("/productDetails")
	public List<Product> getProductDetails() {
		return productServices.findByProductDetails();
	}

	@PostMapping(value = "/create/product", consumes = { "application/json" })

	public ResponseEntity<ProductLine> createProductLine(@RequestBody Product product) {

		productServices.createProduct(product);

		System.out.println(product);

		return ResponseEntity.status(HttpStatus.ACCEPTED).build();

	}

	@GetMapping("/by_line/{line}")
	public List<Product> getProduct(@PathVariable String line) {
		List<Product> product = productServices.getByLine(line);
		if (product.size() > 0) {
			return product;
		} else {
			throw new NotFoundException("No products found for the given line");
		}

	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{code}")
	@Operation(summary = "Update Product by ProductCode")
	public ResponseEntity<Product> update(@PathVariable String code, @RequestBody Product product) {
		Product productNew = productServices.getById(code);
		if (productNew != null) {
			productServices.updateDetails(code, product);
			return ResponseEntity.ok(product);
		} else {
			throw new NotFoundException("Product not found");
		}
	}

}
