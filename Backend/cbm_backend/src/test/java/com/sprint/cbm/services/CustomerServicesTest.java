package com.sprint.cbm.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.sprint.cbm.entities.Customer;
import com.sprint.cbm.repository.CustomerRepo;

@SpringBootTest
public class CustomerServicesTest {

	@Mock
	private CustomerRepo customerRepo;

	@InjectMocks
	private CustomerServices customerServices;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}
	/**
	 * Test for the getAll method of CustomerServices.
	 * It verifies that the returned list of customers matches the expected result.
	 */

	@Test
	void testGetAll() {
		// Arrange
		List<Customer> customers = new ArrayList<>();

		when(customerRepo.findAll()).thenReturn(customers);

		// Act
		List<Customer> result = customerServices.getAll();

		// Assert
		assertEquals(customers, result);
		verify(customerRepo).findAll();
	}


	/**
	 * Test for the getById method of CustomerServices with a valid customer ID.
	 * It verifies that the returned customer matches the expected result.
	 */
	@Test
	void testGetById_ValidId_ReturnsCustomer() {
		// Arrange
		int customerId = 1;
		Customer expectedCustomer = new Customer();
		when(customerRepo.findById(customerId)).thenReturn(java.util.Optional.of(expectedCustomer));

		// Act
		Customer actualCustomer = customerServices.getById(customerId);

		// Assert
		assertEquals(expectedCustomer, actualCustomer);
		verify(customerRepo).findById(customerId);
	}
	
	/**
	 * Test for the getByName method of CustomerServices.
	 * It verifies that the returned customer matches the expected result.
	 */

	@Test
	void testGetByName() {
		// Arrange
		String name = "Signal Gift Stores";
		Customer customer = new Customer();
		
		when(customerRepo.getByName(name)).thenReturn(customer);

		// Act
		Customer result = customerServices.getByName(name);

		// Assert
		assertEquals(customer, result);
		verify(customerRepo).getByName(name);
	}
	
	/**
	 * Test for the getByCity method of CustomerServices.
	 * It verifies that the returned list of customers matches the expected result.
	 */

	@Test
	void testGetByCity() {
		// Arrange
		String city = "Nantes";
		List<Customer> customer = new ArrayList<>();
		customer.add(new Customer(103,"Atelier graphique","Schmitt","Carine","40.32.2555","54, rue Royale",null,"Nantes",null,"44000","France",21000.00, null, null, null, null));
		System.out.println(customer);
		
		when(customerRepo.findByCity(city)).thenReturn(customer);

		// Act
		List<Customer> result = customerServices.getByCity(city);
		System.out.println(result);

		// Assert
		assertEquals(customer, result);
		verify(customerRepo).findByCity(city);
	}

	@Test
	void testUpdateName_ValidId_ReturnsUpdatedCustomer() {
		// Arrange
		int customerId = 1;
		String newName = "John Doe";

		Customer customer = new Customer();
		customer.setId(customerId);
		customer.setName("Atelier graphique");

		when(customerRepo.findById(customerId)).thenReturn(java.util.Optional.of(customer));
		when(customerRepo.save(customer)).thenReturn(customer);

		// Act
		Customer updatedCustomer = customerServices.updateName(customerId, newName);

		// Assert
		assertEquals(newName, updatedCustomer.getName());
		verify(customerRepo).findById(customerId);
		verify(customerRepo).save(customer);
	}

	@Test
	void testUpdateContactLastName_ValidId_ReturnsUpdatedCustomer() {
		// Arrange
		int customerId = 1;
		String newLastName = "Doe";

		Customer customer = new Customer();
		customer.setId(customerId);
		customer.setContactLastName("Schmitt");

		when(customerRepo.findById(customerId)).thenReturn(java.util.Optional.of(customer));
		when(customerRepo.save(customer)).thenReturn(customer);

		// Act
		Customer updatedCustomer = customerServices.updateContactLastName(customerId, newLastName);

		// Assert
		assertEquals(newLastName, updatedCustomer.getContactLastName());
		verify(customerRepo).findById(customerId);
		verify(customerRepo).save(customer);
	}

}
