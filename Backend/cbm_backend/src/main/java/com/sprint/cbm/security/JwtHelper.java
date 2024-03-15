package com.sprint.cbm.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * JWT Helper class.
 *
 * This class provides utility methods for working with JWT tokens, such as
 * generating tokens, retrieving information from tokens, and validating tokens.
 */
@Component
public class JwtHelper {

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	// public static final long JWT_TOKEN_VALIDITY = 60;
	private String secret = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";

	/**
	 * Retrieves the username from the JWT token.
	 *
	 * @param token The JWT token
	 * @return The username extracted from the token
	 */

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	/**
	 * Retrieves the expiration date from the JWT token.
	 *
	 * @param token The JWT token
	 * @return The expiration date of the token
	 */
	public Date getExpirationDateFromToken(String token) {
		return (Date) getClaimFromToken(token, Claims::getExpiration);
	}

	/**
	 * Retrieves a claim from the JWT token.
	 *
	 * @param token          The JWT token
	 * @param claimsResolver The function to resolve the desired claim from the
	 *                       token's claims
	 * @param <T>            The type of the claim
	 * @return The resolved claim from the token
	 */

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	/**
	 * Retrieves all claims from the JWT token.
	 *
	 * @param token The JWT token
	 * @return All claims from the token
	 */

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	/**
	 * Checks if the JWT token is expired.
	 *
	 * @param token The JWT token
	 * @return True if the token is expired, false otherwise
	 */

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	/**
	 * Generates a JWT token for the specified user.
	 *
	 * @param userDetails The UserDetails object representing the authenticated user
	 * @return The generated JWT token
	 */
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();

		return doGenerateToken(claims, userDetails.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	// validate token
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}