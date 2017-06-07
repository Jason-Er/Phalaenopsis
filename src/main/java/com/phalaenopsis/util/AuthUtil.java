package com.phalaenopsis.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthUtil {
	public static String getAuthUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
