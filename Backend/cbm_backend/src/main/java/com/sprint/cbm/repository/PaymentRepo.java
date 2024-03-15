package com.sprint.cbm.repository;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.cbm.entities.Customer;
import com.sprint.cbm.entities.Office;
import com.sprint.cbm.entities.PaymentId;
import com.sprint.cbm.entities.Payments;

public interface PaymentRepo extends JpaRepository<Payments, PaymentId> {

	List<Payments> findByCustomerId(int customerId);

	List<Payments> findByPaymentDate(Date date);

	List<Payments> findByCheckNumber(String check);

	@Query("select count(p) from Payments p join p.customer c where c.id =:id")
	int countByCustomerId(@Param("id") int id);

	@Query("select p.customer from Payments p where p.checkNumber = :num")
	Customer getByCheckNumber(@Param("num") String num);

	@Query("select p.customer from Payments p where p.amount = (select max(p.amount) from Payments p) ")
	Customer customerWithMaxAmount();

	
	@Query("select  p.customer from Payments p where p.paymentDate between :startDate And :endDate")
	List<Customer> paymentDoneByCustomersInDateBetween(@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

	
	
	@Query("select  p.customer from Payments p where p.paymentDate = :paymentDate")
	List<Customer> cutomersPaymentOn(@Param("paymentDate") Date paymentDate);

	
	
	
	@Query("SELECT e.offices.code AS officeCode, SUM(p.amount) AS paymentAmount " + "FROM Payments p "
			+ "JOIN p.customer c " + "JOIN c.employeeRep e " + "GROUP BY e.offices.code "
			+ "ORDER BY paymentAmount DESC")
	List<Map<String, Double>> getOfficePaymentAmounts();

}
