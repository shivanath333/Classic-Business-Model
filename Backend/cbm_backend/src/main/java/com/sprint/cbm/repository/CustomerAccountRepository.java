package com.sprint.cbm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.cbm.entities.CustomerAccount;

public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Integer>{

Optional<CustomerAccount> findByEmail(String email);

	Optional<CustomerAccount> findById(Long id);
	

}
