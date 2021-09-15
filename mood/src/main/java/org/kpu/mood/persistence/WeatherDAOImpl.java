package org.kpu.mood.persistence;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.kpu.mood.domain.WeatherVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class WeatherDAOImpl implements WeatherDAO{
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "org.kpu.sleepapp.mapper.weatherMapper";
	
	public void insertNow(WeatherVO weatherVO)throws Exception {
		sqlSession.insert(namespace+".insertNow", weatherVO);
	}
	
	public void insertForecast(WeatherVO weatherVO)throws Exception{
		sqlSession.insert(namespace+".insertForecast", weatherVO);
	}
	
	public List<WeatherVO> selectForecast() throws Exception{
		List<WeatherVO> weatherlist = new ArrayList<WeatherVO>();
		weatherlist = sqlSession.selectList(namespace+".select");
		return weatherlist;
	}
	
	public WeatherVO selectNow() throws Exception{
		return sqlSession.selectOne(namespace+".selectNow");
	}
}
