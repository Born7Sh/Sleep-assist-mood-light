package org.kpu.mood.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailService {
	public UserDetails loadUserByUsername(String username) throws Exception;
}
