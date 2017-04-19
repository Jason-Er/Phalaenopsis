package com.phalaenopsis.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.phalaenopsis.model.Role;
import com.phalaenopsis.model.User;
import com.phalaenopsis.model.UserState;
import com.phalaenopsis.repository.UserRepository;

@Service("customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	public static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private UserService userService;

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		User user = userService.findByName(name);
		logger.info("User : " + user + "  name: " + name);
		if (user == null) {
			logger.info("User not found");
			throw new UsernameNotFoundException("Username not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
				user.getState()==UserState.ACTIVE, true, true, true, getGrantedAuthorities(user));
	}

	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (Role role : user.getRoles()) {
			logger.info("UserProfile : " + role);
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getType()));
		}
		logger.info("authorities :" + authorities);
		return authorities;
	}

}