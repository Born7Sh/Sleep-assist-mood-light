package org.kpu.mood.persistence;

import java.util.List;

import org.kpu.mood.domain.CalendarVO;

public interface CalendarDAO {
	public void insertPlan(CalendarVO CalendarVO) throws Exception;
	public List<CalendarVO> readList(String email) throws Exception;
}
