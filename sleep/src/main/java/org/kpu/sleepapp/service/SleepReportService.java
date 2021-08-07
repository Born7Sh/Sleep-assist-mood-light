package org.kpu.sleepapp.service;

import org.kpu.sleepapp.domain.SleepReportVO;
import org.kpu.sleepapp.domain.SleepReportVO2;

public interface SleepReportService {
	public SleepReportVO readTodayReport(String email) throws Exception;
	public SleepReportVO readAllReport(String email) throws Exception;
	public SleepReportVO readSelectReport(SleepReportVO reportVO) throws Exception;
	public SleepReportVO readPeriodReport(SleepReportVO2 reportVO) throws Exception;
}
