package org.kpu.mood.service;

import org.kpu.mood.domain.UserVO;
import org.kpu.mood.persistence.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan({"org.kpu.mood.persistence"})
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO;
	
	public void signup(UserVO userVO) throws Exception {
		userDAO.insertUser(userVO);
	}
	
	public void deleteUser(String email) throws Exception {
		userDAO.deleteUser(email);
	}
}
