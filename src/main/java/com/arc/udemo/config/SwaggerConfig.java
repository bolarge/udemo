package com.arc.udemo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * Java config for Springfox swagger documentation plugin
 *
 * @author Bolaji Salau
 *
 */
@Configuration
@EnableSwagger2
@ComponentScan(basePackages="com.arc.udemo")
public class SwaggerConfig {
	@Bean
	public Docket customDocket(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo());
	}

	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"uDemo API Documentation",
				"This is REST API documentation of the uDemo backend. If authentication is enabled, when calling the APIs use admin/admin",
				"1.0",
				"uDemo terms of service",
				new Contact(
						"Bolaji Salau",
						"https://github.com/bolarge/udemo",
						"bolaji.salau@gmail.com"),
				"Apache 2.0",
				"http://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
	}
}
