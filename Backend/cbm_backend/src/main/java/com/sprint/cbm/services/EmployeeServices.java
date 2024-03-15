package com.sprint.cbm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.cbm.entities.EmployeeAccount;
import com.sprint.cbm.entities.Employees;
import com.sprint.cbm.repository.EmployeeRepo;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServices {

	@Autowired
	private EmployeeRepo employeeRepo;

	/**
	 * Employee Services.
	 *
	 * This service class provides various operations related to employees, such as
	 * retrieving employee information, updating employee details, and performing
	 * queries on employees based on different criteria.
	 */
	// finding employee based on id
	public Employees findById(int id) {
		return employeeRepo.findById(id).orElse(null);
	}

	// get all employee

	/**
	 * Retrieves an employee by ID.
	 *
	 * @param id The ID of the employee
	 * @return The employee with the specified ID, or null if not found
	 */
	public List<Employees> getAll() {
		return employeeRepo.findAll();
	}

	/**
	 * Retrieves all employees.
	 *
	 * @return List of all employees
	 */

	// getting employees based on office city
	public List<Employees> getByCity(String city) {
		return employeeRepo.findByOfficeCity(city);
	}

	/**
	 * Retrieves employees by office city.
	 *
	 * @param city The city name
	 * @return List of employees in the specified city
	 */
	// getting employee based on office code

	public List<Employees> getByOfficeCode(int code) {
		return employeeRepo.findByOfficeCode(code);
	}

	/**
	 * Retrieves employees by office code.
	 *
	 * @param code The office code
	 * @return List of employees in the specified office
	 */
	public Employees createEmployee(Employees employee) {

		return employeeRepo.save(employee);
	}

	public Employees updateReporting(int empId, int empNewNo) {

		Employees employees = employeeRepo.findById(empId).get();
		Employees newEmployees = employeeRepo.findById(empNewNo).get();
//		if(employees != null && newEmployees !=null) {
		employees.setReportsTo(newEmployees);
		employeeRepo.save(employees);
		return employees;
	}

	public Employees updateRole(int empId, String role) {

		Employees employees = employeeRepo.findById(empId).get();

		employees.setJobTitle(role);
		employeeRepo.save(employees);
		return employees;

	}

	public Employees updateDetails(int id, Employees employee) {

		Employees employee2 = employeeRepo.findById(id).orElse(null);

		employee2.setLastName(employee.getLastName());
		employee2.setFirstName(employee.getFirstName());
		employee2.setExtension(employee.getExtension());
		employee2.setEmail(employee.getEmail());
		employee2.setJobTitle(employee.getJobTitle());

		return employeeRepo.save(employee2);

	}

}
