/**
 * 
 */
package com.ceridian.demo.democeridian.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Arjoon
 *
 */
public class CustomerId implements Serializable {

	/**
	 * Generated Serial Version Id.
	 */
	private static final long serialVersionUID = -6738943561262225322L;

	private String firstName;

	private String lastName;

	public CustomerId(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public CustomerId() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerId other = (CustomerId) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName);
	}

}
