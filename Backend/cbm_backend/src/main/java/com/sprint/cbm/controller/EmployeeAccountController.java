package com.sprint.cbm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.cbm.entities.EmployeeAccount;
import com.sprint.cbm.models.JwtRequest;
import com.sprint.cbm.models.JwtResponse;
import com.sprint.cbm.services.EmployeeAccountServices;

import jakarta.annotation.security.PermitAll;

@CrossOrigin(origins = "http://localhost:4200")
@PermitAll
@RestController
@RequestMapping("api/v1/admin")
public class EmployeeAccountController {

	@Autowired
	private EmployeeAccountServices employeeAccountServices;

	
	/**
	 * Endpoint for creating a admin account.
	 *
	 * @param customerAccount The EmployeeAccount object containing the customer's
	 *                        details
	 * @return The created EmployeeAccount object
	 */

	
	@PermitAll
	@PostMapping("/add")
	public EmployeeAccount create(@RequestBody EmployeeAccount employeeAccount) {
		System.out.println(employeeAccount);
		return employeeAccountServices.create(employeeAccount);
	}
	
	
	@PermitAll
	@PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        return employeeAccountServices.login(request);
    }

}