package org.kpu.mood.persistence;

import java.util.List;

import org.kpu.mood.domain.ElementsVO;
import org.kpu.mood.domain.SleepReportVO;
import org.kpu.mood.domain.SleepReportVO2;

public interface SleepReportDAO {
	public SleepReportVO readToday(SleepReportVO sleep_reportVO) throws Exception;
	public List<SleepReportVO> readAll(String email) throws Exception;
	public List<ElementsVO> readElements(String email) throws Exception;
	public List<ElementsVO> readElements(SleepReportVO sleep_reportVO) throws Exception;
	public List<SleepReportVO> readPeriod(SleepReportVO2 reportVO) throws Exception;
	public List<ElementsVO> readElements(SleepReportVO2 sleep_reportVO) throws Exception;
}
