package com.phalaenopsis.service;

import com.phalaenopsis.model.User;

public interface UserService {

	User findById(Long id);
	
	User findBySso(String sso);
	
}