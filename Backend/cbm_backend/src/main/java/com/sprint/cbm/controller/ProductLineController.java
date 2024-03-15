package com.sprint.cbm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.cbm.entities.ProductLine;
import com.sprint.cbm.exception.InvalidException;
import com.sprint.cbm.services.ProductLineServices;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/product_line")
public class ProductLineController {

	@Autowired
	public ProductLineServices productLineServices;

	@GetMapping("/all")

	public List<ProductLine> getAllProductLine() {
		return productLineServices.getAll();
	}

	@GetMapping("/get/{productLine}")

	public ProductLine getByName(@PathVariable String productLine) {

		return productLineServices.getById(productLine);

	}

	@PutMapping("update_description/{name}/{description}")

	public ResponseEntity<ProductLine> updateDescription(@PathVariable("name") String name,
			@RequestBody String description) {

		try {

			ProductLine productLine = productLineServices.getById(name);

			productLineServices.updateProductLine(name, description);

			return ResponseEntity.status(HttpStatus.OK).build();

		}

		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

	@PostMapping("/create/product_line")

	public ProductLine createProductLine(@RequestBody ProductLine productLine) {

		if (productLine != null) {
			return productLineServices.createProductLine(productLine);
		} else {
			throw new InvalidException("Invalid argument");
		}

	}

}
