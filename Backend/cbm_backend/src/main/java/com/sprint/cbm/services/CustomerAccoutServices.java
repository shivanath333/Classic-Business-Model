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

import com.sprint.cbm.entities.Customer;
import com.sprint.cbm.entities.CustomerAccount;
import com.sprint.cbm.exception.InvalidException;
import com.sprint.cbm.exception.NotFoundException;
import com.sprint.cbm.models.JwtRequest;
import com.sprint.cbm.models.JwtResponse;
import com.sprint.cbm.models.JwtResponseBuilder;
import com.sprint.cbm.repository.CustomerAccountRepository;
import com.sprint.cbm.security.JwtHelper;

/**
 * Customer Account Services.
 *
 * This service class provides methods for managing customer accounts, such as
 * creating an account and logging in.
 */
@Service
public class CustomerAccoutServices {

	@Autowired

	private CustomerAccountRepository customerAccountRepository;
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtHelper jwtHelper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Creates a new customer account.
	 *
	 * @param account The customer account to create
	 * @return The created customer account
	 * @throws RuntimeException if the account is null
	 */
	public CustomerAccount create(CustomerAccount account) {

		if (account != null) {

			String encryptedpassword = passwordEncoder.encode(account.getPassword());

			CustomerAccount newAccount = new CustomerAccount();

//			newAccount.setActive(true);

			newAccount.setPassword(encryptedpassword);
			newAccount.setCustomer(account.getCustomer());
			newAccount.setId(account.getId());
			newAccount.setEmail(account.getEmail());

			newAccount.setRole();

			return customerAccountRepository.save(newAccount);
		} else {
			throw new RuntimeException("Give valid accout");
		}
	}

	public ResponseEntity<JwtResponse> login(JwtRequest request) {
		authenticate(request.getEmail(), request.getPassword());

		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

		boolean isUser = userDetails.getAuthorities().stream()
				.anyMatch(authority -> authority.getAuthority().equals("ROLE_USER"));

		if (isUser) {
			String token = jwtHelper.generateToken(userDetails);
			JwtResponse response = JwtResponseBuilder.builder().jwtToken(token).username(userDetails.getUsername())
					.build();
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			throw new InvalidException("Wrong credentails");
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

	/**
	 * Logs in a customer using the provided credentials.
	 *
	 * @param request The JWT authentication request containing the email and
	 *                password
	 * @return The JWT response containing the token and username
	 * @throws InvalidException if the credentials are invalid
	 */
	public int getCustomerByEmail(String email) {

		CustomerAccount customerAccount = customerAccountRepository.findByEmail(email).orElse(null);

		Customer customer = customerAccount.getCustomer();

		int customerId = customer.getId();

		return customerId;

	}

}
