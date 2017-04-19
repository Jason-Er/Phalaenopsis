package com.phalaenopsis.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.phalaenopsis.model.User;
import com.phalaenopsis.model.UserState;
import com.phalaenopsis.repository.UserRepository;

@Component
public class DataInitialization implements CommandLineRunner {

	@Autowired
	UserRepository repository;

	@Override
	public void run(String... arg0) throws Exception {
		//
		User user = new User();
		user.setName("Jack");
		user.setEmail("Bauer@gmail.com");
		user.setPassword("123");
		user.setState(UserState.ACTIVE);
		repository.save(user);
		

		// fetch all users
		System.out.println("User found with findAll():");
		System.out.println("-------------------------------");
		for (User user1 : repository.findAll()) {
			System.out.println(user1);
		}

	}

}
