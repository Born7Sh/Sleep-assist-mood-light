package org.kpu.sleepapp.persistence;

import org.kpu.sleepapp.domain.SleepReportVO;

public interface SleepReportDAO {
	public SleepReportVO read(SleepReportVO sleep_reportVO) throws Exception;
}
