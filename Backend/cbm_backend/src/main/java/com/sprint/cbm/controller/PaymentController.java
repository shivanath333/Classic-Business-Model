package com.sprint.cbm.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.cbm.entities.Customer;
import com.sprint.cbm.entities.Payments;
import com.sprint.cbm.exception.NotFoundException;
import com.sprint.cbm.services.PaymentServices;

/**
 * Controller class for managing payments.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController

@RequestMapping("api/v1/payments")
public class PaymentController {

	@Autowired
	private PaymentServices paymentServices;

	/**
	 * Retrieves all payments.
	 * 
	 * @return List of Payments
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("")
	public List<Payments> getAll() {
		return paymentServices.getAll();
	}

	/**
	 * Retrieves payment details of a specific customer.
	 * 
	 * @param id Customer ID
	 * @return List of Payments
	 * @throws NotFoundException if no details found for the customer with the given
	 *                           ID
	 */

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/details_of/{id}")

	public List<Payments> paymentDetailsOf(@PathVariable int id) {
		if (paymentServices.paymentDetailsOfCustomer(id).size() != 0) {
			return paymentServices.paymentDetailsOfCustomer(id);
		} else {
			throw new NotFoundException("No details found for customer with id " + id);
		}
	}

	/**
	 * Retrieves payment details by date.
	 * 
	 * @param date Payment date
	 * @return List of Payments
	 * @throws NotFoundException if no details found for the given date
	 */

	@GetMapping("/payment_date/{date}")

	public List<Payments> paymentDetailsOfDate(@PathVariable Date date) {
		if (paymentServices.paymentByDate(date).size() > 0) {

			return paymentServices.paymentByDate(date);
		} else {
			throw new NotFoundException("No details found for date  " + date);

		}

	}

	/**
	 * Retrieves payments by check number.
	 * 
	 * @param check Check number
	 * @return List of Payments
	 * @throws NotFoundException if no details found for the given check number
	 */

	@GetMapping("/check_number/{check}")

	public List<Payments> getByCheckNumber(@PathVariable String check) {
		if (paymentServices.paymentByCheckNumebr(check).size() > 0) {

			return paymentServices.paymentByCheckNumebr(check);
		}

		else {
			throw new NotFoundException("No details found for check number  " + check);

		}
	}

	/**
	 * Retrieves the number of payments by customer ID.
	 * 
	 * @param id Customer ID
	 * @return Number of payments
	 * @throws NotFoundException if no payment found for the given customer ID
	 */

	@GetMapping("/number_of_payments/{id}")

	public int numberOfPaymentsBy(@PathVariable int id) {
		if (paymentServices.totalPaymentBy(id) != 0) {
			return paymentServices.totalPaymentBy(id);
		} else {
			throw new NotFoundException("No payment for id  " + id);
		}

	}

	/**
	 * Retrieves the customer by check number.
	 * 
	 * @param num Check number
	 * @return Customer object
	 * @throws NotFoundException if no customer found for the given check number
	 */

	@GetMapping("/customer_by_check/{num}")
	public Customer getCustomerByCheck(@PathVariable String num) {
		if (paymentServices.getByCheckNumber(num) != null) {
			return paymentServices.getByCheckNumber(num);
		} else {
			throw new NotFoundException("no customer found for given check number");
		}
	}

	/**
	 * Retrieves the customer with the maximum amount.
	 * 
	 * @return Customer object with maximum amount
	 */

	@GetMapping("/with_max_amount")
	public Customer maxAmountCustomer() {
		return paymentServices.getByMaxAmount();

	}

	/**
	 * Retrieves customers with payments within the specified date range.
	 * 
	 * @param start Start date
	 * @param end   End date
	 * @return List of Customers
	 * @throws NotFoundException if no payment found between the given dates
	 */

	@GetMapping("/within/{start}/{end}")

	public List<Customer> paymentWithin(@PathVariable Date start, @PathVariable Date end) {
		if (paymentServices.getCustomerByPayment(start, end).size() > 0) {

			return paymentServices.getCustomerByPayment(start, end);
		} else {
			throw new NotFoundException("no payment found between given date " + start + " and " + end);
		}

	}

	/**
	 * Retrieves customers who made payments on the specified date.
	 * 
	 * @param date Payment date
	 * @return List of Customers
	 * @throws NotFoundException if no customer found for the given date
	 */

	@GetMapping("/customerPayment/{date}")

	public List<Customer> customerPaidOn(@PathVariable Date date) {
		if (paymentServices.customersPaymentOn(date).size() > 0) {

			return paymentServices.customersPaymentOn(date);
		} else {
			throw new NotFoundException("No customer found for date " + date);
		}
	}

	/**
	 * Creates a new payment.
	 * 
	 * @param payments Payment object
	 * @return Created payment object
	 */
	@GetMapping("/officePayment")

	public List<Map<String, Double>> getOfficepayment() {
		return paymentServices.getOfficeAndPayment();
	}

	/**
	 * Updates the check number of a payment.
	 * 
	 * @param customerId     Customer ID
	 * @param checkNumber    Current check number
	 * @param newCheckNumber New check number
	 * @return ResponseEntity indicating the success or failure of the update
	 */

	@PreAuthorize("hasRole('USER')")
	@PostMapping("/add")

	public Payments addPayment(@RequestBody Payments payments) {

		return paymentServices.create(payments);

	}

	@PutMapping("/update/{customerId}/{checkNumber}")
	public ResponseEntity<Void> updateCheckNumber(@PathVariable("customerId") int customerId,
			@PathVariable("checkNumber") String checkNumber, @RequestBody String newCheckNumber) {
		paymentServices.updateCheckNumber(customerId, checkNumber, newCheckNumber);
		return ResponseEntity.noContent().build();
	}

}
