/**
 * 
 */
package com.ceridian.demo.democeridian.service;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ceridian.demo.democeridian.entity.CustomerEntity;
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

	}

	private void validateCustomer(CustomerDto customerDto) throws DemoCeridianException {
		if (customerDto == null || StringUtils.isBlank(customerDto.getFirstName())
				|| StringUtils.isBlank(customerDto.getLastName()) || StringUtils.isBlank(customerDto.getDob())) {
			throw new DemoCeridianException(CUSTOMER_INVALID);
		}
	}

}
