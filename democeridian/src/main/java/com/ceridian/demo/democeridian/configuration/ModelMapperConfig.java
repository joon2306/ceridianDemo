/**
 * 
 */
package com.ceridian.demo.democeridian.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Arjoon
 *
 */
@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper buildMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(AccessLevel.PRIVATE);

		return modelMapper;
	}

}
