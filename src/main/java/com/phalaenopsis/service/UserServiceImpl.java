package com.phalaenopsis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.phalaenopsis.model.User;
import com.phalaenopsis.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	public User findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByName(String name) {		
		return userRepository.findByName(name);
	}

	@Override
	public void saveUser(User user) {		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUserById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllUsers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> findAllUsers() {		
		return userRepository.findAll();
	}

	@Override
	public Page<User> findByPage(Pageable var1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUserExist(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
