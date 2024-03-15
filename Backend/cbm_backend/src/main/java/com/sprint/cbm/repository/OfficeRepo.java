package com.sprint.cbm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sprint.cbm.entities.Customer;
import com.sprint.cbm.entities.Office;

@Repository
public interface OfficeRepo extends JpaRepository<Office, Integer>{

	Office findByCode(int id);

	List<Office> findByCityIn(List<String> cities);
	
	
	@Query("select c from Customer c join c.employeeRep e join e.offices o where o.code = :id")
	
	List<Customer> getCustomersByOfficeCode(@Param("id") int id);
	
	
	
	
	

	


	
	
	
	
	

}
