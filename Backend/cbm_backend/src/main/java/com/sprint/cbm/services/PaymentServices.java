package com.sprint.cbm.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.cbm.entities.Customer;
import com.sprint.cbm.entities.PaymentId;
import com.sprint.cbm.entities.Payments;
import com.sprint.cbm.exception.NotFoundException;
import com.sprint.cbm.repository.PaymentRepo;

@Service
public class PaymentServices {

	@Autowired
	private PaymentRepo paymentRepo;

	/**
	 * Retrieves all payments.
	 * 
	 * @return List of Payments
	 */
	public List<Payments> getAll() {
		return paymentRepo.findAll();
	}

	/**
	 * Retrieves payment details of a specific customer.
	 * 
	 * @param id Customer ID
	 * @return List of Payments
	 */

	public List<Payments> paymentDetailsOfCustomer(int id) {
		return paymentRepo.findByCustomerId(id);
	}

	/**
	 * Retrieves payments by date.
	 * 
	 * @param date Payment date
	 * @return List of Payments
	 */
	public List<Payments> paymentByDate(Date date) {
		return paymentRepo.findByPaymentDate(date);

	}

	/**
	 * Retrieves payments by check number.
	 * 
	 * @param check Check number
	 * @return List of Payments
	 */

	public List<Payments> paymentByCheckNumebr(String check) {
		return paymentRepo.findByCheckNumber(check);

	}

	/**
	 * Retrieves the total number of payments by customer ID.
	 * 
	 * @param id Customer ID
	 * @return Total number of payments
	 */
	public int totalPaymentBy(int id) {
		return paymentRepo.countByCustomerId(id);
	}

	/**
	 * Retrieves the customer by check number.
	 * 
	 * @param id Check number
	 * @return Customer object
	 */
	public Customer getByCheckNumber(String id) {
		return paymentRepo.getByCheckNumber(id);
	}

	/**
	 * Retrieves the customer with the maximum amount.
	 * 
	 * @return Customer object with maximum amount
	 */
	public Customer getByMaxAmount() {
		return paymentRepo.customerWithMaxAmount();
	}

	/**
	 * Retrieves customers with payments within the specified date range.
	 * 
	 * @param start Start date
	 * @param end   End date
	 * @return List of Customers
	 */
	public List<Customer> getCustomerByPayment(Date start, Date end) {

		System.out.println("");
		return paymentRepo.paymentDoneByCustomersInDateBetween(start, end);

	}

	/**
	 * Retrieves customers who made payments on the specified date.
	 * 
	 * @param date Payment date
	 * @return List of Customers
	 */

	public List<Customer> customersPaymentOn(Date date) {
		return paymentRepo.cutomersPaymentOn(date);
	}

	/**
	 * Retrieves office payments.
	 * 
	 * @return List of office payments
	 */
	public List<Map<String, Double>> getOfficeAndPayment() {
		return paymentRepo.getOfficePaymentAmounts();
	}

	/**
	 * Adds a payment to the customer's payments list.
	 * 
	 * @param id       Customer ID
	 * @param payments Payment object
	 * @return Updated list of Payments
	 */
	public List<Payments> addPayments(int id, Payments payments) {

		List<Payments> payments2 = paymentRepo.findByCustomerId(id);

		payments2.add(payments);
		paymentRepo.save(payments);
		return payments2;
	}

	/**
	 * Creates a new payment.
	 * 
	 * @param payments Payment object
	 * @return Created payment object
	 */
	public Payments create(Payments payments) {
		return paymentRepo.save(payments);
	}

	public void updateCheckNumber(int customerId, String checkNumber, String newCheckNumber) {
		Customer customer = new Customer();
		customer.setId(customerId);

		PaymentId paymentId = new PaymentId(customer, checkNumber);
		Payments payment = paymentRepo.findById(paymentId)
				.orElseThrow(() -> new NotFoundException("Payment not found"));

		payment.setCheckNumber(newCheckNumber);
		paymentRepo.save(payment);
	}

}
