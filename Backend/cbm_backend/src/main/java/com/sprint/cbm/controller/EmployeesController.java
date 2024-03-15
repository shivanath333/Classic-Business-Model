package com.sprint.cbm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.cbm.entities.Employees;
import com.sprint.cbm.exception.NotFoundException;
import com.sprint.cbm.services.EmployeeServices;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controller class for handling Employee-related endpoints.
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeesController {

	@Autowired
	private EmployeeServices employeeServices;

	/**
	 * Retrieves all employees.
	 *
	 * @return List of all employees
	 */

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("")
	public List<Employees> getAll() {
		return employeeServices.getAll();
	}

	/**
	 * Retrieves an employee by ID.
	 *
	 * @param id The ID of the employee to retrieve
	 * @return The employee with the specified ID
	 * @throws NotFoundException if the employee is not found
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{id}")

	public Employees getById(@PathVariable int id) {
		if (employeeServices.findById(id) != null) {
			return employeeServices.findById(id);
		} else {
			throw new NotFoundException("Employee not found !!!!");
		}

	}

	/**
	 * Retrieves employees by city.
	 *
	 * @param city The city to search for
	 * @return List of employees in the specified city
	 * @throws NotFoundException if no employees are found
	 */

	@GetMapping("/bycity/{city}")
	public List<Employees> getByCity(@PathVariable String city) {

		if (employeeServices.getByCity(city).size() > 0) {
			return employeeServices.getByCity(city);
		}
		throw new NotFoundException("Employee not found,");
	}

	/**
	 * Retrieves employees by office code.
	 *
	 * @param code The office code to search for
	 * @return List of employees in the specified office
	 * @throws NotFoundException if no employees are found
	 */

	@GetMapping("/bycode/{code}")
	public List<Employees> getByOfficeCode(@PathVariable int code) {
		if (employeeServices.getByOfficeCode(code).size() > 0) {

			return employeeServices.getByOfficeCode(code);
		} else {
			throw new NotFoundException("Employees not found,check office code");
		}
	}

	/**
	 * Creates a new employee.
	 *
	 * @param employee The employee to create
	 * @return The created employee
	 */
	@PreAuthorize("hasRole('ADMIN')")

	@PostMapping(path = "/employee/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Employees createEmployee(@RequestBody Employees employee) {

		return employeeServices.createEmployee(employee);
	}

//	@PutMapping("/{employe_no}/reports_to/{new_employee_no}")
//	
//	public Employees updateReportsTo(@PathVariable int employe_no, @RequestBody int new_employee_no ) {
//		
//		return employeeServices.updateReporting(employe_no, new_employee_no);
//		
//	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update_role/{empId}/{role}")

	public Employees updateRole(@PathVariable int empId, @RequestBody String role) {

		return employeeServices.updateRole(empId, role);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update_employee/{id}")
	@Operation(summary = "Update Employee based on Employee Id")
	public ResponseEntity<Employees> update(@PathVariable int id, @RequestBody Employees employee) {
		Employees employeeNew = employeeServices.findById(id);
		if (employeeNew != null) {
			employeeServices.updateDetails(id, employee);
			return ResponseEntity.ok(employee);
		} else {
			throw new NotFoundException("Employee not found");
		}
	}

}

//	 
//	
//	
