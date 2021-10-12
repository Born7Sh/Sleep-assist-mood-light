package org.kpu.mood.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.kpu.mood.domain.ElementsVO;
import org.kpu.mood.domain.SleepReportTrans;
import org.kpu.mood.domain.SleepReportVO;
import org.kpu.mood.domain.SleepReportVO2;
import org.kpu.mood.persistence.SleepReportDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan({"org.kpu.mood.persistence"})
public class SleepReportServiceImpl implements SleepReportService{
	
	@Autowired
	private SleepReportDAO sleepreportDAO;
	
	public SleepReportVO readTodayReport(String email) throws Exception {
		//Value Object 생성
		SleepReportVO reportVO = new SleepReportVO();
		SleepReportTrans sleep = new SleepReportTrans();
		//오늘 날짜 지정
		Date nowDate = new Date();
		SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd");
		String date = format1.format(nowDate);
		sleep.setEmail(email);
		sleep.setDate(date);
		reportVO = sleepreportDAO.readToday(sleep);
		
		
		return reportVO;
	}
	public SleepReportVO readPeriodReport(SleepReportVO2 reportVO) throws Exception {
		//기간 내 자료들 리스트에 넣기 
		List<SleepReportVO> sreportVO = new ArrayList<SleepReportVO>();
		sreportVO = sleepreportDAO.readPeriod(reportVO);

		//평균값내서 집어넣기
		SleepReportVO sleepreportVO = new SleepReportVO();

		sleepreportVO = average(sreportVO);
		//sleepreportVO.setEmail(reportVO.getEmail());
		
		List<ElementsVO> elementslist = new ArrayList<ElementsVO>();
		elementslist=sleepreportDAO.readElements(reportVO);
		String elements = str(elementslist);
		
		//sleepreportVO.setElements(elements.toString());
		return sleepreportVO;
	}
	
	public SleepReportVO readSelectReport(SleepReportVO reportVO) throws Exception {
		SleepReportVO sleepreportVO = new SleepReportVO();
		
		//sleepreportVO = sleepreportDAO.readToday(reportVO);
		List<ElementsVO> list = sleepreportDAO.readElements(reportVO);
		String element = str(list);
		
		//sleepreportVO.setElements(element);
		return sleepreportVO;
	}
	
	public List<SleepReportVO> readAllReports(String email) throws Exception{
		
		List<SleepReportVO> list = sleepreportDAO.readAll(email);
		
		return list;
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
		reportVO.setElements("");
		List<String> string_list = new ArrayList<String>();
		string_list = sleepreportDAO.readAllElement(email);
		for (String str : string_list) {
			if(str.equals("없음")) {
				reportVO.setElements("없음");
				break;
			}
		}
		int a=0,b=0,c=0; //요소 체크용
		String text = "";
		
		String element = reportVO.getElements();
		System.out.println(element);
		if(!(element.equals("없음"))||element==null) {
			for (String str : string_list) {
				if(str.equals("흡연") && a==0) {
					text += "흡연 ";
					a = 1;
				}
				if(str.equals("감기") && b==0) {
					text += "감기 ";
					b = 1;
				}
				if(str.equals("내 집 아님") && c==0) {
					text += "내 집 아님 ";
					c = 1;
				}
				System.out.println(str);
			}
			reportVO.setElements(text);
		}
		
		return reportVO;
	}
	
	public SleepReportVO average(List<SleepReportVO> list) {
		SleepReportVO report = new SleepReportVO();
		int score = 0, sleeping_time = 0;
		
		for(SleepReportVO reportVO : list) {
			score += reportVO.getScore();
			sleeping_time += reportVO.getSleeping_time();
			
		}
		System.out.println(score);
		System.out.println(sleeping_time);
		System.out.println(list.size());
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
