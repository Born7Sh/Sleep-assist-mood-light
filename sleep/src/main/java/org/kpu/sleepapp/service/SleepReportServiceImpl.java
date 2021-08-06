package org.kpu.sleepapp.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.kpu.sleepapp.domain.SleepReportVO;
import org.kpu.sleepapp.persistence.SleepReportDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SleepReportServiceImpl implements SleepReportService{
	
	@Autowired
	private SleepReportDAO sleep_reportDAO;
	
	public SleepReportVO readReport(String email) throws Exception {
		SleepReportVO reportVO = new SleepReportVO();
		Date nowDate = new Date();
		SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd");
		String date = format1.format(nowDate);
		reportVO.setEmail(email);
		reportVO.setDate(date);
		System.out.println(reportVO.getEmail());
		return sleep_reportDAO.read(reportVO);
	}
}
