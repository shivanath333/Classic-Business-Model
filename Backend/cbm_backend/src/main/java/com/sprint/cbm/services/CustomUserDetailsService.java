package com.sprint.cbm.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sprint.cbm.entities.CustomerAccount;
import com.sprint.cbm.entities.EmployeeAccount;
import com.sprint.cbm.repository.CustomerAccountRepository;
import com.sprint.cbm.repository.EmployeeAccountRepository;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private EmployeeAccountRepository employeeAccountRepository;

	@Autowired
	private CustomerAccountRepository customerAccountRepository;

	/**
	 * Loads user details based on the provided username (email).
	 *
	 * @param email The email of the user
	 * @return User details for the specified user
	 * @throws UsernameNotFoundException if the user is not found
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// Find the user by email in the respective repositories
		Optional<EmployeeAccount> employeeOptional = employeeAccountRepository.findByEmail(email);
		Optional<CustomerAccount> customerOptional = customerAccountRepository.findByEmail(email);

		if (employeeOptional.isPresent()) {
			EmployeeAccount employee = employeeOptional.get();

			// Check if the user is an admin
			if (employee.getRole().equals("ADMIN")) {
				return new User(employee.getEmail(), employee.getPassword(), getAuthorities("ADMIN"));
			}
		} else if (customerOptional.isPresent()) {

			CustomerAccount customer = customerOptional.get();

			if (customer.getRole().endsWith("USER")) {

				return new User(customer.getEmail(), customer.getPassword(), getAuthorities("USER"));
			}
		}

		throw new UsernameNotFoundException("User not found with email: " + email);
	}

	/**
	 * Retrieves the authorities (roles) for a user.
	 *
	 * @param role The role of the user
	 * @return The granted authorities for the user
	 */
	private Collection<? extends GrantedAuthority> getAuthorities(String role) {
		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
	}
}
