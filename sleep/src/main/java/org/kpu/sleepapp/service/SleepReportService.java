package org.kpu.sleepapp.service;

import org.kpu.sleepapp.domain.SleepReportVO;

public interface SleepReportService {
	public SleepReportVO readReport(String email) throws Exception;
}
