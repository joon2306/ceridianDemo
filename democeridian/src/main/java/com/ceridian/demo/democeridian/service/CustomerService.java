/**
 * 
 */
package com.ceridian.demo.democeridian.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ceridian.demo.democeridian.entity.CustomerEntity;
import com.ceridian.demo.democeridian.entity.CustomerId;
import com.ceridian.demo.democeridian.exception.DemoCeridianException;
import com.ceridian.demo.democeridian.models.CustomerDto;
import com.ceridian.demo.democeridian.repo.CustomerRepo;
import com.ceridian.demo.democeridian.utils.DateUtils;

/**
 * @author Arjoon
 *
 */
@Service
public class CustomerService {

	private final CustomerRepo customerRepo;

	private final ModelMapper mapper;

	private final JwtUtils jwtUtils;

	private final String CUSTOMER_INVALID = "Customer information is invalid";

	private final String CUSTOMER_NOT_DB = "Customer not present in DB";

	private Logger logger = LoggerFactory.getLogger(CustomerService.class);

	public CustomerService(CustomerRepo customerRepo, ModelMapper mapper, JwtUtils jwtUtils) {
		this.customerRepo = customerRepo;
		this.mapper = mapper;
		this.jwtUtils = jwtUtils;
	}

	public void createCustomer(HttpServletRequest request, CustomerDto customerDto) throws DemoCeridianException {
		validateCustomer(customerDto);

		CustomerEntity customer = mapper.map(customerDto, CustomerEntity.class);
		customer.setDob(DateUtils.stringToDate(customerDto.getDob()));
		customer.setCreatedBy(jwtUtils.getUsernameFromRequest(request));
		customer.setCreatedOn(LocalDateTime.now());

		customerRepo.save(customer);

		logger.info("customer with name {} created", customerDto.getFirstName());

	}

	public List<CustomerEntity> getAllCustomers() {
		return customerRepo.findAll();
	}

	public void updateCustomerDob(HttpServletRequest request, CustomerDto customerDto) throws DemoCeridianException {

		validateCustomer(customerDto);
		CustomerId customerId = mapper.map(customerDto, CustomerId.class);
		CustomerEntity customer = customerRepo.findById(customerId)
				.orElseThrow(() -> new DemoCeridianException(CUSTOMER_NOT_DB));

		deleteCustomerEntity(customer);

		logger.debug("Customer deleted");

		customer.setDob(DateUtils.stringToDate(customerDto.getDob()));
		customer.setUpdatedBy(jwtUtils.getUsernameFromRequest(request));
		customer.setUpdatedOn(LocalDateTime.now());

		customerRepo.save(customer);

		logger.debug("customer updated");

		logger.info("customer with name {} updated", customerDto.getFirstName());

	}

	public void deleteCustomer(String firstName, String lastName) throws DemoCeridianException {
		CustomerId customerId = new CustomerId(firstName, lastName);
		CustomerEntity customer = customerRepo.findById(customerId)
				.orElseThrow(() -> new DemoCeridianException(CUSTOMER_NOT_DB));
		deleteCustomerEntity(customer);

		logger.info("customer with name {} deleted", firstName);
	}

	private void validateCustomer(CustomerDto customerDto) throws DemoCeridianException {
		if (customerDto == null || StringUtils.isBlank(customerDto.getFirstName())
				|| StringUtils.isBlank(customerDto.getLastName()) || StringUtils.isBlank(customerDto.getDob())) {
			throw new DemoCeridianException(CUSTOMER_INVALID);
		}
	}

	private void deleteCustomerEntity(CustomerEntity customer) {
		customerRepo.delete(customer);
	}

}
