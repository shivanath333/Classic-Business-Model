package com.sprint.cbm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.cbm.entities.CustomerAccount;
import com.sprint.cbm.exception.NotFoundException;
import com.sprint.cbm.models.JwtRequest;
import com.sprint.cbm.models.JwtResponse;
import com.sprint.cbm.services.CustomerAccoutServices;

import jakarta.annotation.security.PermitAll;

/**
 * Controller class for handling CustomerAccount-related endpoints.
 */

@CrossOrigin("http://localhost:4200")
@PermitAll
@RestController
@RequestMapping("/api/v1/user")
public class CustomerAccountController {

	@Autowired

	private CustomerAccoutServices customerAccoutServices;

	/**
	 * Endpoint for creating a customer account.
	 *
	 * @param customerAccount The CustomerAccount object containing the customer's
	 *                        details
	 * @return The created CustomerAccount object
	 */

	@PermitAll
	@PostMapping("/add")
	public CustomerAccount create(@RequestBody CustomerAccount customerAccount) {
		System.out.println(customerAccount);
		return customerAccoutServices.create(customerAccount);
	}

	/**
	 * Endpoint for customer login.
	 *
	 * @param request The JwtRequest object containing the login credentials
	 * @return ResponseEntity containing the JwtResponse object with the JWT token
	 */

	@PermitAll
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
		return customerAccoutServices.login(request);
	}

	@PreAuthorize("hasRole('USER')")

	@GetMapping("/customer/{email}")

	public int getCustomer(@PathVariable String email) {

		return customerAccoutServices.getCustomerByEmail(email);

	}

}
