package com.sprint.cbm.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class for application-wide beans.
 */

@Configuration
public class AppConfig {
	/**
     * Configures a BCryptPasswordEncoder bean for password encoding.
     *
     * @return an instance of BCryptPasswordEncoder
     */

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
     * Configures an AuthenticationManager bean.
     *
     * @param builder the AuthenticationConfiguration object used to retrieve the AuthenticationManager
     * @return the configured AuthenticationManager
     * @throws Exception if an error occurs during configuration
     */
	 @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
	        return builder.getAuthenticationManager();
	    }
}
