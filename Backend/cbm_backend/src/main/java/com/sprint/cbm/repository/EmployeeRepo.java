package com.sprint.cbm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.cbm.entities.Employees;

public interface  EmployeeRepo extends JpaRepository<Employees, Integer> {

	Employees findByFirstName(String name);
	
	
	@Query("SELECT e FROM Employees e JOIN e.offices o WHERE o.city = :city")
	List<Employees> findByOfficeCity(@Param("city")String city);

	
	@Query("select e from Employees e join e.offices o where o.code = :code")
	List<Employees> findByOfficeCode(@Param("code") int code);
	

}
