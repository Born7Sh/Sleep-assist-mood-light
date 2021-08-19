package org.kpu.sleepapp.persistence;

import org.apache.ibatis.session.SqlSession;
import org.kpu.sleepapp.domain.WeatherVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class WeatherDAOImpl implements WeatherDAO{
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "org.kpu.sleepapp.mapper.weatherMapper";
	
	public void insertNow(WeatherVO weatherVO)throws Exception {
		sqlSession.selectOne(namespace+".insertNow", weatherVO);
	}
}
