/**
 * 
 */
package com.ceridian.demo.democeridian.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceridian.demo.democeridian.entity.CustomerEntity;
import com.ceridian.demo.democeridian.entity.CustomerId;

/**
 * @author Arjoon
 *
 */
public interface CustomerRepo extends JpaRepository<CustomerEntity, CustomerId>{

}
