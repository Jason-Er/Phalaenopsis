package com.phalaenopsis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.phalaenopsis.model.User;
import com.phalaenopsis.repositories.UserRepository;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;	

	public User findById(Long id) {		
		return userRepository.findOne(id);
	}

	public User findBySso(String sso) {
		return userRepository.findBySsoId(sso);
	}

}
