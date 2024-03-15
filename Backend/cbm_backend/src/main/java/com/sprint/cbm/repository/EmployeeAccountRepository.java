package com.sprint.cbm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.cbm.entities.EmployeeAccount;

public interface EmployeeAccountRepository extends JpaRepository<EmployeeAccount, Integer> {

//	EmployeeAccount findByEmail(String email);

	Optional<EmployeeAccount> findById(Long long1);

	Optional<EmployeeAccount> findByEmail(String email);
}
