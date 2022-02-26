/**
 * 
 */
package com.ceridian.demo.democeridian.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceridian.demo.democeridian.entity.CustomerEntity;

/**
 * @author Arjoon
 *
 */
public interface CustomerRepo extends JpaRepository<CustomerEntity, Integer>{

}
