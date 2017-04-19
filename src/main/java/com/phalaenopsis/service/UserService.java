package com.phalaenopsis.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.phalaenopsis.model.User;

public interface UserService {
	User findById(Long id);

	User findByName(String name);

	void saveUser(User user);

	void updateUser(User user);

	void deleteUserById(Long id);

	void deleteAllUsers();

	List<User> findAllUsers();

	Page<User> findByPage(Pageable var1);

	boolean isUserExist(User user);
}
