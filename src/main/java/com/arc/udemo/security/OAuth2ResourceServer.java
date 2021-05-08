package com.arc.udemo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServer extends ResourceServerConfigurerAdapter {
	//Implement with a standalone db
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {

		resources.resourceId("uDemo_Resources");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
        http
            .requestMatchers().antMatchers("/oauth2/v1/users/**")
            .and()
            .authorizeRequests().antMatchers("/oauth2/v1/users/**").authenticated();
	}

}
