package org.kpu.sleepapp.service;

import java.util.List;

import org.kpu.sleepapp.domain.CalendarVO;
import org.kpu.sleepapp.persistence.CalendarDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
