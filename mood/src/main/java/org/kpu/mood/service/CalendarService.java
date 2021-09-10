package org.kpu.mood.service;

import java.util.List;

import org.kpu.mood.domain.CalendarVO;

public interface CalendarService {
	public void insertPlan(CalendarVO calendarVO) throws Exception;
	public List<CalendarVO> readList(String email) throws Exception;
}
