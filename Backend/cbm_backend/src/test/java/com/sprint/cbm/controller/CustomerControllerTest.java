package com.sprint.cbm.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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

import com.sprint.cbm.entities.Customer;
import com.sprint.cbm.exception.NotFoundException;
import com.sprint.cbm.services.CustomerServices;

/**
 * Unit tests for the CustomerController class.
 */
public class CustomerControllerTest {

	@Mock
	private CustomerServices customerServices;

	@InjectMocks
	private CustomerController customerController;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	/**
	 * Test for the readAllItems method of CustomerController. It verifies that the
	 * returned list of customers matches the expected result.
	 */

	@Test
	public void testReadAllItems() {
		// Mock data
		List<Customer> customers = Arrays.asList(new Customer(), new Customer());
		when(customerServices.getAll()).thenReturn(customers);

		// Perform the GET request
		List<Customer> result = customerController.readAllItems();

		// Verify the result
		assertEquals(customers, result);
		verify(customerServices, times(1)).getAll();
	}

	/**
	 * Test for the find method of CustomerController when the customer does not
	 * exist. It verifies that a NotFoundException is thrown.
	 */

	@Test
	public void testFindNonExistingCustomerById() {
		// Mock data
		int customerId = 1;
		when(customerServices.getById(customerId)).thenReturn(null);

		// Perform the GET request and verify the exception
		assertThrows(NotFoundException.class, () -> {
			customerController.find(customerId);
		});

		verify(customerServices, times(1)).getById(customerId);
	}

	/**
	 * Test for the addCustomer method of CustomerController. It verifies that the
	 * returned customer matches the expected result.
	 */

	@Test
	public void testAddCustomer() {
		// Mock data
		Customer customer = new Customer();
		when(customerServices.addCustomer(customer)).thenReturn(customer);

		// Perform the POST request
		Customer result = customerController.addCustomer(customer);

		// Verify the result
		assertEquals(customer, result);
		verify(customerServices, times(1)).addCustomer(customer);
	}

	/**
	 * Test for the update method of CustomerController when the customer exists. It
	 * verifies that the response status is HttpStatus.OK and the response body
	 * matches the expected result.
	 */
	@Test
	public void testUpdateExistingCustomer() {
		// Mock data
		int customerId = 1;
		Customer customer = new Customer();
		customer.setId(customerId);
		when(customerServices.getById(customerId)).thenReturn(customer);
		when(customerServices.updateDetails(eq(customerId), any(Customer.class))).thenReturn(customer);

		// Perform the PUT request
		ResponseEntity<Customer> response = customerController.update(customerId, customer);

		// Verify the response status and body
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(customer, response.getBody());
		verify(customerServices, times(1)).getById(customerId);
		verify(customerServices, times(1)).updateDetails(eq(customerId), any(Customer.class));
	}

	@Test
	public void testUpdateNonExistingCustomer() {
		// Mock data
		int customerId = 119;
		Customer customer = new Customer();
		customer.setId(customerId);
		when(customerServices.getById(customerId)).thenReturn(null);

		// Perform the PUT request and verify the exception
		assertThrows(NotFoundException.class, () -> {
			customerController.update(customerId, customer);
		});

		verify(customerServices, times(1)).getById(customerId);
		verify(customerServices, times(0)).updateDetails(eq(customerId), any(Customer.class));
	}
}
