package com.sprint.cbm.services;

import java.util.List;
import org.springframework.data.domain.Sort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sprint.cbm.entities.Customer;
import com.sprint.cbm.entities.Employees;
import com.sprint.cbm.entities.Order;
import com.sprint.cbm.entities.Payments;
import com.sprint.cbm.repository.CustomerRepo;

/**
 * Customer Services.
 *
 * This service class provides various operations related to customers, such as
 * retrieving customer information, updating customer details, and performing
 * queries on customers and their associated entities.
 */
@Service
public class CustomerServices {

	@Autowired
	private CustomerRepo customerRepo;

	/**
	 * Retrieves all customers.
	 *
	 * @return List of all customers
	 */
	public List<Customer> getAll() {
		return customerRepo.findAll();
	}

	/**
	 * Retrieves a customer by ID.
	 *
	 * @param id The ID of the customer
	 * @return The customer with the specified ID, or null if not found
	 */
	public Customer getById(int id) {

		return customerRepo.findById(id).orElse(null);

	}

	/**
	 * Retrieves a customer by name.
	 *
	 * @param name The name of the customer
	 * @return The customer with the specified name, or null if not found
	 */

	public Customer getByName(String name) {
		return customerRepo.getByName(name);
	}

	/**
	 * Retrieves customers by city.
	 *
	 * @param city The city name
	 * @return List of customers in the specified city
	 */
	public List<Customer> getByCity(String city) {
		return customerRepo.findByCity(city);
	}

	/**
	 * Retrieves customers by sales representative.
	 *
	 * @param salesRep The ID of the sales representative
	 * @return List of customers associated with the specified sales representative
	 */
	public List<Customer> getBySalesRep(int salesRep) {
		return customerRepo.findByEmployeeRep(salesRep);
	}

	/**
	 * Retrieves customers by country.
	 *
	 * @param country The country name
	 * @return List of customers in the specified country
	 */

	public List<Customer> getByCountry(String country) {
		return customerRepo.findByCountry(country);
	}

	/**
	 * Retrieves a customer by phone number.
	 *
	 * @param phone The phone number
	 * @return The customer with the specified phone number, or null if not found
	 */

	public Customer getByPhone(String phone) {
		return customerRepo.findByPhone(phone);
	}

	/**
	 * Retrieves a customer by first name.
	 *
	 * @param name The first name
	 * @return The customer with the specified first name, or null if not found
	 */
	public Customer getByFirstName(String name) {
		return customerRepo.findByContactFirstName(name);
	}

	public Customer getByCreditLimit(double credit) {
		return customerRepo.findByCreditLimit(credit);
	}

	public Customer getByCustomerPostal(String code) {
		return customerRepo.findByPostalCode(code);
	}

	public List<Customer> getCustomersByCreditLimitRange(double minCreditLimit, double maxCreditLimit) {
		return customerRepo.findByCreditLimitBetween(minCreditLimit, maxCreditLimit);
	}

	public List<Customer> getCustomersByFirstNameLike(String firstName) {
		return customerRepo.findByNameContainingIgnoreCase(firstName);
	}

	public List<Customer> getCustomersByCreditLimitGreaterThan(double creditLimit) {
		return customerRepo.findByCreditLimitGreaterThan(creditLimit);
	}

	public List<Customer> getCustomersByCreditLimitLowerThan(double creditLimit) {
		return customerRepo.findByCreditLimitLessThan(creditLimit);
	}

	public List<Payments> getPaymentDetails(int id) {

		return customerRepo.findPaymentsByCustomerId(id);

	}

	public Customer findByContactLastName(String lastName) {
		return customerRepo.findByContactLastName(lastName);

	}

	public List<Object[]> findOrderAndPayment(String name) {
		return customerRepo.findOrderAndPayment(name);

	}

//	
	public List<Object[]> findCustomerOrderDetails(int id) {
		return customerRepo.findOrderDetailsWithProductAndCustomer(id);
	}

//

	public Customer addCustomer(Customer customer) {

		return customerRepo.save(customer);
	}

	public Customer updateName(int id, String name) {

		Customer customer = customerRepo.findById(id).orElse(null);

		customer.setName(name);

		return customerRepo.save(customer);
	}

	public Customer updateContactLastName(int id, String name) {
		Customer customer = customerRepo.findById(id).get();

		customer.setContactLastName(name);

		return customerRepo.save(customer);

	}

	public Customer updateContactFirstName(int id, String name) {
		Customer customer = customerRepo.findById(id).get();

		customer.setContactFirstName(name);

		return customerRepo.save(customer);

	}

	public Customer updateAddress(int id, String addresss) {
		Customer customer = customerRepo.findById(id).get();

		customer.setAddressLine1(addresss);
		return customerRepo.save(customer);
	}

	public Customer updateDetails(int id, Customer customer) {

		Customer customer2 = customerRepo.findById(id).orElse(null);

		customer2.setName(customer.getName());
		customer2.setContactFirstName(customer.getContactFirstName());
		customer2.setContactLastName(customer.getContactLastName());
		customer2.setAddressLine1(customer.getAddressLine1());
		customer2.setAddressLine2(customer.getAddressLine2());
		customer2.setCity(customer.getCity());
		customer2.setPhone(customer.getPhone());
		customer2.setCreditLimit(customer.getCreditLimit());
		customer2.setState(customer.getState());
		customer2.setCountry(customer.getCountry());

		customer2.setPostalCode(customer.getPostalCode());
//		customer2.setEmployeeRep(customer.getEmployeeRep());
//		customer2.setPayment(customer.getPayment());
//		customer2.setOrders(customer.getOrders());

		return customerRepo.save(customer2);

	}

	public Employees getEmployeeRep(int id) {
		Customer customer = customerRepo.findById(id).orElse(null);
		return customer.getEmployeeRep();

	}

	public List<Order> getCustomerOrder(int id) {
		Customer customer = customerRepo.findById(id).orElseGet(null);
		return customer.getOrders();

	}

	public Page<Customer> getCustomers(int pageSize, int pageNo, String sortBy, String sortDir) {
		Sort.Direction direction = sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
		PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by(direction, sortBy));
		return customerRepo.findAll(pageRequest);
	}

}
