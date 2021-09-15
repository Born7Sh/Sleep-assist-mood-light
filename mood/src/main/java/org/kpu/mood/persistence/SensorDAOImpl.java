package org.kpu.mood.persistence;

import org.apache.ibatis.session.SqlSession;
import org.kpu.mood.domain.Hardware_Control_StatusVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SensorDAOImpl implements SensorDAO{
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "org.kpu.sleepapp.mapper.SensorMapper";
	
	public Hardware_Control_StatusVO readSensorData(String email) throws Exception{
		return sqlSession.selectOne(namespace+".selectbyEmail",email);
	}
	
}
