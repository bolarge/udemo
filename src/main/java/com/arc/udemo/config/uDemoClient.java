package com.arc.udemo.config;

import com.arc.udemo.domain.users.User;
import org.springframework.web.client.RestTemplate;

public class uDemoClient {

	private static final String UDemo_URI_V1 = "http://localhost:8085/v1/users";

	private RestTemplate restTemplate = new RestTemplate();

	public User getUserById(Integer userId) {
		return restTemplate.getForObject(UDemo_URI_V1 + "/{userId}", User.class, userId);
	}


	public static void main(String[] args) {
		uDemoClient client = new uDemoClient();

		// Test Get User API
		User user = client.getUserById(8);
		System.out.println(user);
	}

}
