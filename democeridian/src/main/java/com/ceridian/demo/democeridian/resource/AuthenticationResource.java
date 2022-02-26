/**
 * 
 */
package com.ceridian.demo.democeridian.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ceridian.demo.democeridian.exception.DemoCeridianException;
import com.ceridian.demo.democeridian.models.AuthenticationRequest;
import com.ceridian.demo.democeridian.models.AuthenticationResponse;
import com.ceridian.demo.democeridian.service.JwtUtils;
import com.ceridian.demo.democeridian.service.MyUserDetailsService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Arjoon
 *
 */
@RestController
public class AuthenticationResource {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private JwtUtils jwtUtils;

	/**
	 * Generate JWT token from username and password.
	 * 
	 * @param authenticationRequest authenticationRequest to set.
	 * 
	 * @return JWT token.
	 * @throws DemoCeridianException
	 */
	@ApiOperation(value = "API to generate JWT token")
	@ApiResponses({ @ApiResponse(code = 200, message = "Token generated", response = AuthenticationResponse.class),
			@ApiResponse(code = 400, message = "Incorrect Username or password") })
	@PostMapping("/authenticate")
	public ResponseEntity<?> authentication(
			@ApiParam(value = "name and password of person") @RequestBody AuthenticationRequest authenticationRequest)
			throws DemoCeridianException {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new DemoCeridianException("Incorrect Username or Password");
		}

		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtUtils.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
