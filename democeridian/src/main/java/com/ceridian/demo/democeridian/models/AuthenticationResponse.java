/**
 * 
 */
package com.ceridian.demo.democeridian.models;

/**
 * @author Arjoon
 *
 */
public class AuthenticationResponse {

	private final String jwt;

	public String getJwt() {
		return this.jwt;
	}

	public AuthenticationResponse(String jwt) {
		super();
		this.jwt = jwt;
	}

}
