package com.sprint.cbm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.cbm.entities.Customer;
import com.sprint.cbm.entities.Payments;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	Customer getByName(String name);

	List<Customer> findByCity(String city);

	@Query("select c from Customer c where  c.employeeRep.id = :employeeRepId ") //
	List<Customer> findByEmployeeRep(int employeeRepId);

	List<Customer> findByCountry(String country);

	Customer findByPhone(String phone);

	Customer findByContactFirstName(String name);

	Customer findByCreditLimit(double credit);

	Customer findByPostalCode(String code);

	List<Customer> findByCreditLimitBetween(double minCreditLimit, double maxCreditLimit);

	List<Customer> findByNameContainingIgnoreCase(String firstName); //

	List<Customer> findByCreditLimitGreaterThan(double creditLimit);

	List<Customer> findByCreditLimitLessThan(double creditLimit);

	@Query("SELECT p FROM Customer c JOIN c.payment p WHERE c.id = :customerId")
	List<Payments> findPaymentsByCustomerId(@Param("customerId") int customerId); //

	Customer findByContactLastName(String lastName);

	 @Query("SELECT p, o FROM Customer c " +
	           "JOIN c.payment p " +
	           "JOIN c.orders o " +
	           "WHERE c.name = :name")
	    List<Object[]> findOrderAndPayment(@Param("name") String name);
	
	
	    @Query("SELECT o, od FROM Order o " +
	            "JOIN o.orderDetails od " +
	            "JOIN FETCH o.customer c " +
	            "WHERE c.id = :customerId")
	     List<Object[]> findOrderDetailsWithProductAndCustomer(@Param("customerId")Integer customerId);
	 
	     Page<Customer> findAll(Pageable pageable);
	     
	     
 
	     
}

