/**
 * 
 */
package com.ceridian.demo.democeridian.resource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceridian.demo.democeridian.exception.DemoCeridianException;
import com.ceridian.demo.democeridian.models.AuthenticationResponse;
import com.ceridian.demo.democeridian.models.CustomerDto;
import com.ceridian.demo.democeridian.service.CustomerService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Arjoon
 *
 */
@RestController
@RequestMapping("/customer")
public class CustomerResource {

	@Autowired
	private CustomerService customerService;

	/**
	 * Add a customer to the database.
	 * 
	 * @param request
	 * @param customer
	 * @return
	 * @throws DemoCeridianException
	 */
	@ApiOperation(value = "API to add a customer in database. A JWT token is required.")
	@ApiResponses({ @ApiResponse(code = 200, message = "Customer Added", response = AuthenticationResponse.class),
			@ApiResponse(code = 400, message = "Customer information is invalid") })
	@PostMapping("/add")
	public ResponseEntity<?> addCustomer(HttpServletRequest request, @RequestBody CustomerDto customer)
			throws DemoCeridianException {
		customerService.createCustomer(request, customer);

		return ResponseEntity.ok(customer);
	}

	/**
	 * List all customers in database.
	 * 
	 * @return list of customers.
	 */
	@ApiOperation(value = "API to list all customers in database. A JWT token is required.")
	@GetMapping("/all")
	public ResponseEntity<?> findAllCustomers() {
		return ResponseEntity.ok(customerService.getAllCustomers());
	}

	/**
	 * Update a customer dob in database.
	 * 
	 * @param request
	 * @param customer
	 * @return
	 * @throws DemoCeridianException
	 */
	@ApiOperation(value = "API to update a customer in database. A JWT token is required.")
	@ApiResponses({ @ApiResponse(code = 200, message = "Customer updated", response = AuthenticationResponse.class),
			@ApiResponse(code = 400, message = "Customer information is invalid") })
	@PutMapping("/update")
	public ResponseEntity<?> updateCustomerDob(HttpServletRequest request, @RequestBody CustomerDto customer)
			throws DemoCeridianException {
		customerService.updateCustomerDob(request, customer);

		return ResponseEntity.ok(customer);
	}

	/**
	 * Delete a customer in database.
	 * 
	 * @param firstName
	 * @param lastName
	 * @return
	 * @throws DemoCeridianException
	 */
	@ApiOperation(value = "API to delete a customer in database. A JWT token is required.")
	@ApiResponses({ @ApiResponse(code = 200, message = "Customer deleted", response = AuthenticationResponse.class),
			@ApiResponse(code = 400, message = "Customer not present in DB") })
	@DeleteMapping("/delete/{firstName}/{lastName}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("firstName") String firstName,
			@PathVariable("lastName") String lastName) throws DemoCeridianException {
		customerService.deleteCustomer(firstName, lastName);

		return ResponseEntity.ok("Customer deleted");
	}

}
