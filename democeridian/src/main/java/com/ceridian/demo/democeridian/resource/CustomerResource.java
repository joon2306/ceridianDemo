/**
 * 
 */
package com.ceridian.demo.democeridian.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Arjoon
 *
 */
@RestController
public class CustomerResource {
	
	@GetMapping("/hello")
	public String hello() {
		return "hello world";
	}

}
