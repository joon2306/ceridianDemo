/**
 * 
 */
package com.ceridian.demo.democeridian.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Arjoon
 *
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.ceridian.demo.democeridian")).build().apiInfo(buildApiInfo());
	}

	private ApiInfo buildApiInfo() {
		return new ApiInfo("Ceridian Demo Api Application", "This is a demo for Ceridian", "1.0", null,
				new Contact("Arjoonsing Pertaub", null, "pertauba@gmail.com"), null, null, Collections.emptyList());
	}

}
