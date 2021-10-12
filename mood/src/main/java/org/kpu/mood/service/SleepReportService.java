package org.kpu.mood.service;

import org.kpu.mood.domain.SleepReportVO;
import org.kpu.mood.domain.SleepReportVO2;

public interface SleepReportService {
	public SleepReportVO readTodayReport(String email) throws Exception;
	public SleepReportVO readAllReport(String email) throws Exception;
	public SleepReportVO readSelectReport(SleepReportVO reportVO) throws Exception;
	public SleepReportVO readPeriodReport(SleepReportVO2 reportVO) throws Exception;
}
