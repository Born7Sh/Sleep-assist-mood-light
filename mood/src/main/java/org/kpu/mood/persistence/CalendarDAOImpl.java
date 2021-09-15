package org.kpu.mood.persistence;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.kpu.mood.domain.CalendarVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CalendarDAOImpl implements CalendarDAO{
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "org.kpu.sleepapp.mapper.CalendarMapper";
	public List<CalendarVO> readList(String email) throws Exception{
		List<CalendarVO> calendarVO = new ArrayList<CalendarVO>();
		System.out.println(email);
		calendarVO = sqlSession.selectList(namespace + ".selectPlans", email);
		return calendarVO;
	}
	
	public void insertPlan(CalendarVO calendarVO) throws Exception {
		sqlSession.insert(namespace+".insertPlan", calendarVO);
	}
}
