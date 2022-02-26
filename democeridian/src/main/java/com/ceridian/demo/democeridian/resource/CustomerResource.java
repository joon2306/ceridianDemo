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
import com.ceridian.demo.democeridian.models.CustomerDto;
import com.ceridian.demo.democeridian.service.CustomerService;

/**
 * @author Arjoon
 *
 */
@RestController
@RequestMapping("/customer")
public class CustomerResource {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/hello")
	public String hello() {
		return "hello world";
	}

	@PostMapping("/add")
	public ResponseEntity<?> addCustomer(HttpServletRequest request, @RequestBody CustomerDto customer)
			throws DemoCeridianException {
		customerService.createCustomer(request, customer);

		return ResponseEntity.ok(customer);
	}

	@GetMapping("/all")
	public ResponseEntity<?> findAllCustomers() {
		return ResponseEntity.ok(customerService.getAllCustomers());
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateCustomerDob(HttpServletRequest request, @RequestBody CustomerDto customer)
			throws DemoCeridianException {
		customerService.updateCustomerDob(request, customer);

		return ResponseEntity.ok(customer);
	}

	@DeleteMapping("/delete/{firstName}/{lastName}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("firstName") String firstName,
			@PathVariable("lastName") String lastName) throws DemoCeridianException {
		customerService.deleteCustomer(firstName, lastName);

		return ResponseEntity.ok("Customer deleted");
	}

}
