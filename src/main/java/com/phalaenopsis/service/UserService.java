package com.phalaenopsis.service;

import java.util.List;

import com.phalaenopsis.model.User;

public interface UserService {

	User findById(Long id);
	
	User findBySso(String sso);
	
	void saveUser(User user);

	void updateUser(User user);

	void deleteUserById(Long id);

	void deleteAllUsers();

	List<User> findAllUsers();

	boolean isUserExist(User user);
}