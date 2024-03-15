package com.sprint.cbm.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.sprint.cbm.security.JwtAuthenticationEntryPoint;
import com.sprint.cbm.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	/**
	 * Configuration class for Spring Security.
	 */

	@Autowired
	private JwtAuthenticationEntryPoint point;

	@Autowired
	private JwtAuthenticationFilter filter;

	@Autowired
	private UserDetailsService customUserDetailService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Configures the security filter chain.
	 *
	 * @param http the HttpSecurity object to configure
	 * @return the configured SecurityFilterChain object
	 * @throws Exception if an error occurs during configuration
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).cors(cors -> cors.configurationSource(request -> {
			CorsConfiguration config = new CorsConfiguration();
			config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
			config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
			config.setAllowedHeaders(Arrays.asList("*"));
			config.setExposedHeaders(Arrays.asList("Authorization"));
			config.setAllowCredentials(true);
			return config;
		})).authorizeHttpRequests(auth -> auth.requestMatchers("/home").authenticated()
				.requestMatchers("/api/v1/admin/login").permitAll().requestMatchers("/api/v1/admin/add").permitAll()
				.requestMatchers("/api/v1/user/add").permitAll().requestMatchers("/api/v1/user/login").permitAll()
				.requestMatchers("http://localhost:9090/swagger-ui/index.html").permitAll()
				.requestMatchers("/api/v1/customer/add").permitAll().requestMatchers("/api/v1/office").permitAll()
				.requestMatchers("/api/v1/product_line/all").permitAll().requestMatchers("/api/v1/products").permitAll()
				.anyRequest().authenticated()).exceptionHandling(ex -> ex.authenticationEntryPoint(point))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	/**
	 * Configures the DaoAuthenticationProvider.
	 *
	 * @return the configured DaoAuthenticationProvider
	 */

	@Bean
	public DaoAuthenticationProvider doDaoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

		daoAuthenticationProvider.setUserDetailsService(customUserDetailService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		return daoAuthenticationProvider;
	}
}
