package com.phalaenopsis.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.phalaenopsis.model.User;
import com.phalaenopsis.model.UserState;
import com.phalaenopsis.service.UserService;

@Component
public class DataInitialization implements CommandLineRunner {

	@Autowired
	UserService userService;

	@Override
	public void run(String... arg0) throws Exception {
		
		User user = new User();
		user.setName("Jack");
		user.setEmail("Bauer@gmail.com");
		user.setPassword("123");
		user.setState(UserState.ACTIVE);
		userService.saveUser(user);
		

		// fetch all users
		System.out.println("User found with findAll():");
		System.out.println("-------------------------------");
		for (User user1 : userService.findAllUsers()) {
			System.out.println(user1);
		}
		
	}

}
