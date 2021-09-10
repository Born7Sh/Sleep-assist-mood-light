package org.kpu.mood.service;

import java.util.ArrayList;

import org.kpu.mood.domain.UserVO;
import org.kpu.mood.persistence.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@ComponentScan({"org.kpu.mood.persistence"})
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserDAO userDAO;
	
	public UserDetails loadUserByUsername(String userName) {
		UserVO user = new UserVO();

		try {
			user = userDAO.readEmail(userName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
	}

}
