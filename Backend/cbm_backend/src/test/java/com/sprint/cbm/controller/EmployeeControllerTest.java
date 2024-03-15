package com.sprint.cbm.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sprint.cbm.entities.Employees;
import com.sprint.cbm.exception.NotFoundException;
import com.sprint.cbm.services.EmployeeServices;

public class EmployeeControllerTest {

	@Mock
	private EmployeeServices employeeServices;

	@InjectMocks
	private EmployeesController employeesController;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllEmployees() {
		// Mock data
		List<Employees> employees = Arrays.asList(new Employees(), new Employees());
		when(employeeServices.getAll()).thenReturn(employees);

		// Perform the GET request
		List<Employees> result = employeesController.getAll();

		// Verify the result
		assertEquals(employees, result);
		verify(employeeServices, times(1)).getAll();
	}

	@Test
	public void testGetNonExistingEmployeeById() {
		// Mock data
		int employeeId = 1;
		when(employeeServices.findById(employeeId)).thenReturn(null);

		// Perform the GET request and verify the exception
		assertThrows(NotFoundException.class, () -> {
			employeesController.getById(employeeId);
		});

		verify(employeeServices, times(1)).findById(employeeId);
	}

	@Test
	public void testCreateEmployee() {
		// Mock data
		Employees employee = new Employees();
		when(employeeServices.createEmployee(employee)).thenReturn(employee);

		// Perform the POST request
		Employees result = employeesController.createEmployee(employee);

		// Verify the result
		assertEquals(employee, result);
		verify(employeeServices, times(1)).createEmployee(employee);
	}

	@Test
	public void testUpdateRole() {
		// Mock data
		int employeeId = 1;
		String role = "Manager";
		Employees employee = new Employees();
		employee.setId(employeeId);
		when(employeeServices.updateRole(employeeId, role)).thenReturn(employee);

		// Perform the PUT request
		Employees result = employeesController.updateRole(employeeId, role);

		// Verify the result
		assertEquals(employee, result);
		verify(employeeServices, times(1)).updateRole(employeeId, role);
	}
//


}
