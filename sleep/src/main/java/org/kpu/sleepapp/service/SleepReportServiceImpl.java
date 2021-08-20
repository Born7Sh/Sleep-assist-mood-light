package org.kpu.sleepapp.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.kpu.sleepapp.domain.ElementsVO;
import org.kpu.sleepapp.domain.SleepReportVO;
import org.kpu.sleepapp.domain.SleepReportVO2;
import org.kpu.sleepapp.persistence.SleepReportDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SleepReportServiceImpl implements SleepReportService{
	
	@Autowired
	private SleepReportDAO sleepreportDAO;
	
	public SleepReportVO readTodayReport(String email) throws Exception {
		//Value Object 생성
		SleepReportVO reportVO = new SleepReportVO();
		//오늘 날짜 지정
		Date nowDate = new Date();
		SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd");
		String date = format1.format(nowDate);
		reportVO.setEmail(email);
		reportVO.setDate(date);
		
		reportVO = sleepreportDAO.readToday(reportVO);
		
		List<ElementsVO> list = sleepreportDAO.readElements(reportVO);
		String element = str(list);
		reportVO.setElements(element);
		
		return reportVO;
	}
	public SleepReportVO readPeriodReport(SleepReportVO2 reportVO) throws Exception {
		//기간 내 자료들 리스트에 넣기 
		List<SleepReportVO> sreportVO = new ArrayList<SleepReportVO>();
		sreportVO = sleepreportDAO.readPeriod(reportVO);

		//평균값내서 집어넣기
		SleepReportVO sleepreportVO = new SleepReportVO();

		sleepreportVO = average(sreportVO);
		sleepreportVO.setEmail(reportVO.getEmail());
		
		List<ElementsVO> elementslist = new ArrayList<ElementsVO>();
		elementslist=sleepreportDAO.readElements(reportVO);
		String elements = str(elementslist);
		
		sleepreportVO.setElements(elements.toString());
		return sleepreportVO;
	}
	
	public SleepReportVO readSelectReport(SleepReportVO reportVO) throws Exception {
		SleepReportVO sleepreportVO = new SleepReportVO();
		
		sleepreportVO = sleepreportDAO.readToday(reportVO);
		List<ElementsVO> list = sleepreportDAO.readElements(reportVO);
		String element = str(list);
		
		sleepreportVO.setElements(element);
		return sleepreportVO;
	}
		
	public SleepReportVO readAllReport(String email) throws Exception{
		//데이터 처리
		SleepReportVO reportVO = new SleepReportVO();
		List<SleepReportVO> list = new ArrayList<SleepReportVO>();
		list = sleepreportDAO.readAll(email);
		if(list.isEmpty()) {
			return reportVO;
		}
		reportVO = average(list);
		//요소 처리
		List<ElementsVO> elementslist = new ArrayList<ElementsVO>();
		elementslist=sleepreportDAO.readElements(email);

		String elements = str(elementslist);
		
		reportVO.setElements(elements.toString());
		reportVO.setEmail(email);

		
		return reportVO;
	}
	
	public SleepReportVO average(List<SleepReportVO> list) {
		SleepReportVO report = new SleepReportVO();
		int score = 0, sleeping_time = 0;
		
		for(SleepReportVO reportVO : list) {
			score += reportVO.getScore();
			sleeping_time += reportVO.getSleeping_time();
		}
		report.setScore(score/ list.size());
		report.setSleeping_time(sleeping_time/ list.size());
		return report;
	}
	
	public String str(List<ElementsVO> vo) {
		String elements = "";
		for (ElementsVO str : vo) {
		    if (str != null) {
		        elements += str.getElement() + " ";
		    }
		}
		return elements;
	}
}
