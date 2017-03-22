package com.phalaenopsis.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{
	
	List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String ssoId) throws UsernameNotFoundException {
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		return new org.springframework.security.core.userdetails.User("admin", "password", true, true, true, true,
				authorities);
	}
	
}