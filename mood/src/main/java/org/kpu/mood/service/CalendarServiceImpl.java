package org.kpu.mood.service;

import java.util.List;

import org.kpu.mood.domain.CalendarVO;
import org.kpu.mood.persistence.CalendarDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan({"org.kpu.mood.persistence"})
public class CalendarServiceImpl implements CalendarService{
	@Autowired
	private CalendarDAO calendarDAO;
	
	public List<CalendarVO> readList(String email) throws Exception{
		return calendarDAO.readList(email);
	}

	public void insertPlan(CalendarVO calendarVO) throws Exception {
		calendarDAO.insertPlan(calendarVO);
	}
}
