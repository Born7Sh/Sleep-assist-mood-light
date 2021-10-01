package org.kpu.mood.service;

import org.kpu.mood.domain.UserVO;

public interface UserService {
	public void signup(UserVO userVO) throws Exception;
	public void deleteUser(String email) throws Exception;
}
