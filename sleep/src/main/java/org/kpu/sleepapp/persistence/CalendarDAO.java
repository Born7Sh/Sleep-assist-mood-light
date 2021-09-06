package org.kpu.sleepapp.persistence;

import java.util.List;

import org.kpu.sleepapp.domain.CalendarVO;

public interface CalendarDAO {
	public void insertPlan(CalendarVO CalendarVO) throws Exception;
	public List<CalendarVO> readList(String email) throws Exception;
}
