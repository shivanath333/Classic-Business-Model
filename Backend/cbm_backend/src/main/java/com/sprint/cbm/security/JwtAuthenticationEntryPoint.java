package com.sprint.cbm.security;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Custom authentication entry point for JWT authentication.
 * 
 * This class handles the unauthorized access scenario, where a user tries to
 * access a protected resource without valid authentication. It implements the
 * Spring Security's AuthenticationEntryPoint interface. When an unauthorized
 * access occurs, it sets the HTTP response status to 401 (Unauthorized) and
 * writes an error message to the response.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	/**
	 * Handles the unauthorized access scenario.
	 *
	 * @param request       The HTTP servlet request
	 * @param response      The HTTP servlet response
	 * @param authException The authentication exception that occurred
	 * @throws IOException      If an I/O error occurs while writing the response
	 * @throws ServletException If a servlet-specific error occurs while handling
	 *                          the request
	 */

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		PrintWriter writer = response.getWriter();
		writer.println("Access Denied !! " + authException.getMessage());

	}

}