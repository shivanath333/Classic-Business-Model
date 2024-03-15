package com.sprint.cbm.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.cbm.entities.Customer;
import com.sprint.cbm.entities.Employees;
import com.sprint.cbm.entities.Order;
import com.sprint.cbm.entities.Payments;
import com.sprint.cbm.exception.InvalidException;
import com.sprint.cbm.exception.NotFoundException;
import com.sprint.cbm.services.CustomerServices;
import com.sprint.cbm.services.EmployeeServices;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

	@Autowired
	private CustomerServices customerServices;

	/**
	 * Controller class for handling Customer-related endpoints.
	 */

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("")
	public List<Customer> readAllItems() {

		return customerServices.getAll();
	}

	/**
	 * Retrieves all customers.
	 *
	 * @return List of all customers
	 */
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/{id}")

	public Customer find(@PathVariable int id) {
		if (customerServices.getById(id) != null) {
			return customerServices.getById(id);
		} else {
			throw new NotFoundException("Customer Not Found!!");
		}
	}

	/**
	 * Retrieves a customer by ID.
	 *
	 * @param id The ID of the customer to retrieve
	 * @return The customer with the specified ID
	 * @throws NotFoundException if the customer is not found
	 */

	@GetMapping("find/{name}")

	public Customer findByName(@PathVariable String name) {
		if (customerServices.getByName(name) != null) {
			return customerServices.getByName(name);
		} else {
			throw new NotFoundException("Customer Not Found!!");
		}
	}

	/**
	 * Retrieves a customer by name.
	 *
	 * @param name The name of the customer to retrieve
	 * @return The customer with the specified name
	 * @throws NotFoundException if the customer is not found
	 */

	@GetMapping("/city/{city}")

	public List<Customer> findByCity(@PathVariable String city) {
		if (customerServices.getByCity(city) != null) {
			return customerServices.getByCity(city);
		} else {
			throw new NotFoundException("Customer not Found !!");
		}
	}

	/**
	 * Retrieves customers by sales representative ID.
	 *
	 * @param id The ID of the sales representative
	 * @return List of customers associated with the specified sales representative
	 * @throws NotFoundException if no customers are found
	 */
	@GetMapping("/employee_rep/{id}")

	public List<Customer> findByEmpRep(@PathVariable int id) {

		if (customerServices.getBySalesRep(id) != null) {
			return customerServices.getBySalesRep(id);
		} else {
			throw new NotFoundException("Customer not Found !!");
		}
	}

	/**
	 * Retrieves customers by country.
	 *
	 * @param country The country to search for
	 * @return List of customers in the specified country
	 * @throws NotFoundException if no customers are found
	 */
	@GetMapping("/country/{country}")

	public List<Customer> findByCountry(@PathVariable String country) {
		if (customerServices.getByCountry(country) != null) {
			return customerServices.getByCountry(country);
		} else {
			throw new NotFoundException("Customer not Found !!");
		}

	}

	/**
	 * Retrieves a customer by phone number.
	 *
	 * @param phone The phone number to search for
	 * @return The customer with the specified phone number
	 * @throws NotFoundException if the customer is not found
	 */

	@GetMapping("/phone/{phone}")

	public Customer findByPhone(@PathVariable String phone) {
		if (customerServices.getByPhone(phone) != null) {
			return customerServices.getByPhone(phone);
		} else {
			throw new NotFoundException("Customer not Found !!");
		}
	}

	@GetMapping("/contact_first/{name}")

	public Customer findByContactFirstName(@PathVariable String name) {
		if (customerServices.getByFirstName(name) != null) {
			return customerServices.getByFirstName(name);
		} else {
			throw new NotFoundException("Customer not Found !!");
		}

	}

	/**
	 * Retrieves a customer by credit limit.
	 *
	 * @param credit The credit limit to search for
	 * @return The customer with the specified credit limit
	 * @throws NotFoundException if the customer is not found
	 */

	@GetMapping("/credit_limit/{credit}")

	public Customer findByCreditLimt(@PathVariable double credit) {
		if (customerServices.getByCreditLimit(credit) != null) {
			return customerServices.getByCreditLimit(credit);
		} else {
			throw new NotFoundException("Customer not Found !!");
		}
	}
	
	/**
     * Retrieves a customer by postal code.
     *
     * @param code The postal code to search for
     * @return The customer with the specified postal code
     * @throws NotFoundException if the customer
     **/

	@GetMapping("/postal_code/{code}")

	public Customer findByPostalCode(@PathVariable String code) {
		if (customerServices.getByCustomerPostal(code) != null) {
			return customerServices.getByCustomerPostal(code);
		} else {
			throw new NotFoundException("Customer not Found !!");
		}
	}

	@GetMapping("/credit_range/{start}/{end}")

	public List<Customer> creditLimit(@PathVariable("start") double start, @PathVariable("end") double end) {

		return customerServices.getCustomersByCreditLimitRange(start, end);
	}

	@GetMapping("/getbyNamelike/{fn}")

	public List<Customer> byNameAlike(@PathVariable("fn") String name) {
		if (customerServices.getCustomersByFirstNameLike(name) != null) {
			return customerServices.getCustomersByFirstNameLike(name);
		} else {
			throw new NotFoundException("Customer not Found !!");
		}

	}

	@GetMapping("/credit_limit/gt/{creditLimit}")
	public List<Customer> getCustomersByCreditLimitGreaterThan(@PathVariable("creditLimit") double creditLimit) {

		return customerServices.getCustomersByCreditLimitGreaterThan(creditLimit);
	}

	@GetMapping("/credit_limit/ld/{creditLimit}")
	public List<Customer> getCustomersByCreditLimitLowerThan(@PathVariable("creditLimit") double creditLimit) {

		return customerServices.getCustomersByCreditLimitLowerThan(creditLimit);
	}

	@GetMapping("/{customer_id}/paymentdetails")

	public List<Payments> getPaymentDetails(@PathVariable("customer_id") int id) {
		if (customerServices.getPaymentDetails(id).size() > 0) {

			return customerServices.getPaymentDetails(id);
		} else {
			throw new NotFoundException("customer not found!!!");
		}
	}

	@GetMapping("/contact_last/{name}")

	public Customer getByContactLastName(@PathVariable String name) {
		if (customerServices.findByContactLastName(name) != null) {
			return customerServices.findByContactLastName(name);
		} else {
			throw new NotFoundException("Customer not found");
		}

	}

	@GetMapping("/{name}/order_payment_details")

	public List<Object[]> getCustomerOrder(@PathVariable String name) {
		if (!(customerServices.findOrderAndPayment(name).isEmpty())) {
			return customerServices.findOrderAndPayment(name);
		} else {
			throw new NotFoundException("Customer not found");
		}
	}

	@GetMapping("/{id}/order_product_staffdetails")

	public List<Object[]> getCustomerOrderDetails(@PathVariable int id) {
		if (customerServices.findCustomerOrderDetails(id).size() > 0) {

			System.out.println(id);
			return customerServices.findCustomerOrderDetails(id);
		} else {
			throw new NotFoundException("Customer not found");
		}
	}

	@PostMapping(value = "/add", consumes = "application/json")

	public Customer addCustomer(@RequestBody Customer customer) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		if(customer != null) {
			return customerServices.addCustomer(customer);
		}
		else {
			throw new InvalidException("Invalid inpur please provide proper input");
		}

		

	}

	@PutMapping("/update_name/{id}/{name}")

	public Customer customerUpdateName(@PathVariable int id, @RequestBody String name) {

		return customerServices.updateName(id, name);

	}

	@PutMapping("/update_contact_last/{id}/{name}")

	public Customer customerUpdateContactLastName(@PathVariable int id, @RequestBody String name) {

		return customerServices.updateContactLastName(id, name);

	}

	@PutMapping("/update_contact_first/{id}/{name}")

	public Customer customerUpdateContactFisrtName(@PathVariable int id, @RequestBody String name) {

		return customerServices.updateContactFirstName(id, name);

	}

	@PutMapping("/update_address/{id}/{address}")

	public Customer customerUpdateAdderess(@PathVariable int id, @RequestBody String name) {

		return customerServices.updateAddress(id, name);

	}

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PutMapping("/update_details/{id}")
	public ResponseEntity<Customer> update(@PathVariable int id, @RequestBody Customer customer) {
		Customer customerNew = customerServices.getById(id);

		if (customerNew != null) {
			customerServices.updateDetails(id, customer);

			return ResponseEntity.ok(customer);

		} else {
			throw new NotFoundException("Customer not found");
		}

	}

	@GetMapping("/employee_detail/{id}")

	public ResponseEntity<Employees> getEmployeeDetail(@PathVariable int id) {
		Customer customer = customerServices.getById(id);
		if (customer != null) {
			Employees employee = customer.getEmployeeRep();
			return ResponseEntity.ok(employee);

		} else {
			throw new NotFoundException("employee details not found for customer id : " + id);

		}

	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("order/{id}")
	public List<Order> getOrder(@PathVariable int id) {
		Customer customer = customerServices.getById(id);
		if (customer != null) {
			List<Order> order = customer.getOrders();
			return order;

		} else {
			throw new NotFoundException("Order not found");

		}
	}

	@GetMapping("/{pageSize}/{pageNo}/{sortBy}/{sortDir}")
	public Page<Customer> getCustomers(@PathVariable int pageSize, @PathVariable int pageNo,
			@PathVariable String sortBy, @PathVariable String sortDir) {
		return customerServices.getCustomers(pageSize, pageNo, sortBy, sortDir);
	}

}
