package com.sprint.cbm.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.sprint.cbm.entities.Employees;
import com.sprint.cbm.repository.EmployeeRepo;

@SpringBootTest
public class EmployeeServiceTest {

	@Mock
	private EmployeeRepo employeeRepo;

	@InjectMocks
	private EmployeeServices employeeServices;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	/**
	 * Test for the findById method of EmployeeServices with an existing employee
	 * ID. It verifies that the returned employee matches the expected result.
	 */

	@Test
	void testFindById_ExistingId_ReturnsEmployee() {
		// Arrange
		int employeeId = 1;
		Employees expectedEmployee = new Employees();
		expectedEmployee.setId(employeeId);

		when(employeeRepo.findById(employeeId)).thenReturn(Optional.of(expectedEmployee));

		// Act
		Employees actualEmployee = employeeServices.findById(employeeId);

		// Assert
		assertEquals(expectedEmployee, actualEmployee);
		verify(employeeRepo).findById(employeeId);
	}

	/**
	 * Test for the getAll method of EmployeeServices. It verifies that the returned
	 * list of employees matches the expected result.
	 */
	@Test
	void testGetAll_ReturnsListOfEmployees() {
		// Arrange
		List<Employees> employeesList = new ArrayList<>();
		employeesList.add(new Employees());
		employeesList.add(new Employees());

		when(employeeRepo.findAll()).thenReturn(employeesList);

		// Act
		List<Employees> result = employeeServices.getAll();

		// Assert
		assertEquals(employeesList, result);
		verify(employeeRepo).findAll();
	}

	/**
	 * Test for the getByCity method of EmployeeServices with an existing city. It
	 * verifies that the returned list of employees matches the expected result.
	 */
	@Test
	void testGetByCity_ExistingCity_ReturnsListOfEmployees() {
		// Arrange
		String city = "New York";
		List<Employees> employeesList = new ArrayList<>();
		employeesList.add(new Employees());
		employeesList.add(new Employees());

		when(employeeRepo.findByOfficeCity(city)).thenReturn(employeesList);

		// Act
		List<Employees> result = employeeServices.getByCity(city);

		// Assert
		assertEquals(employeesList, result);
		verify(employeeRepo).findByOfficeCity(city);
	}

	/**
	 * Test for the getByOfficeCode method of EmployeeServices with an existing
	 * office code. It verifies that the returned list of employees matches the
	 * expected result.
	 */

	@Test
	void testGetByOfficeCode_ExistingCode_ReturnsListOfEmployees() {
		// Arrange
		int officeCode = 1234;
		List<Employees> employeesList = new ArrayList<>();
		employeesList.add(new Employees());
		employeesList.add(new Employees());

		when(employeeRepo.findByOfficeCode(officeCode)).thenReturn(employeesList);

		// Act
		List<Employees> result = employeeServices.getByOfficeCode(officeCode);

		// Assert
		assertEquals(employeesList, result);
		verify(employeeRepo).findByOfficeCode(officeCode);
	}

	@Test
	void testCreateEmployee_ValidEmployee_ReturnsCreatedEmployee() {
		// Arrange
		Employees employee = new Employees();
		when(employeeRepo.save(employee)).thenReturn(employee);

		// Act
		Employees createdEmployee = employeeServices.createEmployee(employee);

		// Assert
		assertEquals(employee, createdEmployee);
		verify(employeeRepo).save(employee);
	}
}
