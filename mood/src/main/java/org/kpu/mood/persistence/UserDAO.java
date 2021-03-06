package org.kpu.mood.persistence;

import org.kpu.mood.domain.UserVO;

public interface UserDAO {
	public UserVO readEmail(String email) throws Exception;
	public void insertUser(UserVO userVO) throws Exception;
	public void deleteUser(String email) throws Exception;
	public String readIP(String email) throws Exception;
}
