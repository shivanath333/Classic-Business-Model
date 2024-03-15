package com.sprint.cbm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sprint.cbm.entities.EmployeeAccount;
import com.sprint.cbm.exception.InvalidException;
import com.sprint.cbm.models.JwtRequest;
import com.sprint.cbm.models.JwtResponse;
import com.sprint.cbm.models.JwtResponseBuilder;
import com.sprint.cbm.repository.EmployeeAccountRepository;
import com.sprint.cbm.security.JwtHelper;

/**
 * Employee Account Services.
 *
 * This service class provides methods for managing employee accounts, such as
 * creating an account and logging in.
 */
@Service
public class EmployeeAccountServices {

	@Autowired
	private EmployeeAccountRepository employeeAccountRepository;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtHelper jwtHelper;

	@Autowired
	private PasswordEncoder passwordEncoder;



	public EmployeeAccount create(EmployeeAccount account) {

		if (account != null) {

			String encryptedpassword = passwordEncoder.encode(account.getPassword());

			EmployeeAccount newAccount = new EmployeeAccount();

//			newAccount.setActive(true);

			newAccount.setPassword(encryptedpassword);
			newAccount.setEmployee(account.getEmployee());
			newAccount.setId(account.getId());
			newAccount.setEmail(account.getEmail());

			newAccount.setRole();

			return employeeAccountRepository.save(newAccount);
		} else {
			throw new RuntimeException("Give valid accout");
		}

	}

	public ResponseEntity<JwtResponse> login(JwtRequest request) {
		authenticate(request.getEmail(), request.getPassword());

		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

		boolean isAdmin = userDetails.getAuthorities().stream()
				.anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

		if (isAdmin) {
			String token = jwtHelper.generateToken(userDetails);
			JwtResponse response = JwtResponseBuilder.builder().jwtToken(token).username(userDetails.getUsername())
					.build();
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			throw new InvalidException("Wrong password");
		}
	}

	private void authenticate(String email, String password) {
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
		try {
			authenticationManager.authenticate(authentication);
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Invalid Username or Password!!");
		}
	}

	@ExceptionHandler(BadCredentialsException.class)
	public String exceptionHandler() {
		return "Credentials Invalid !!";
	}

}
