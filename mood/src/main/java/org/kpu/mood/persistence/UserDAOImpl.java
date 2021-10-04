package org.kpu.mood.persistence;

import org.apache.ibatis.session.SqlSession;
import org.kpu.mood.domain.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO{
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "org.kpu.sleepapp.mapper.userMapper";
	
	public UserVO readEmail(String username) throws Exception{
		UserVO userVO = new UserVO();
		userVO = sqlSession.selectOne(namespace+".selectbyUserName",username);
		return userVO;
	}
	public String readIP(String email) throws Exception{
		return sqlSession.selectOne(namespace+".selectbyEmail",email);
	}
	public void insertUser(UserVO userVO) throws Exception{
		userVO.setPassword("{noop}" + userVO.getPassword());
		sqlSession.insert(namespace+".insert",userVO); 
	}
	
	public void deleteUser(String email) throws Exception{
		sqlSession.delete(namespace+".deleteUser",email); 
	}
}
