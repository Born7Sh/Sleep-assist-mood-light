package org.kpu.mood.controller;


import java.nio.charset.Charset;
import java.util.List;

import org.kpu.mood.domain.SleepReportVO;
import org.kpu.mood.domain.SleepReportVO2;
import org.kpu.mood.service.SleepReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ComponentScan({"org.kpu.mood.service"})
@RequestMapping(value="/report")
public class ReportRestController {
	
	@Autowired
	private SleepReportService reportService;
	
	private static final Logger logger = LoggerFactory.getLogger(ReportRestController.class);
	
	@RequestMapping(value = "/{email}/today", method = RequestMethod.GET)
	public ResponseEntity<SleepReportVO> readTodayReport(@PathVariable String email) throws Exception {
		//report에 필요한 값 저장 후 서비스 레이어에서 처리 요청
		SleepReportVO report = reportService.readTodayReport(email);

		// 로그 생성
		logger.info(" /report/{}/today REST-API GET method called. then method executed.",email);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		headers.set("My-Header", "MyHeaderValue");
		return new ResponseEntity<SleepReportVO>(report, headers, HttpStatus.OK);
	}
	@RequestMapping(value = "/{email}/all1", method = RequestMethod.GET)
	public ResponseEntity<SleepReportVO> readAllReport(@PathVariable String email) throws Exception {
		//report에 필요한 값 저장 후 서비스 레이어에서 처리 요청

		SleepReportVO report = reportService.readAllReport(email);
		// 로그 생성
		logger.info(" /report/{}/all REST-API GET method called. then method executed.",email);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		headers.set("My-Header", "MyHeaderValue");
		return new ResponseEntity<SleepReportVO>(report, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{email}/all", method = RequestMethod.GET)
	public ResponseEntity<List<SleepReportVO>> readAllReports(@PathVariable String email) throws Exception {
		//report에 필요한 값 저장 후 서비스 레이어에서 처리 요청

		List<SleepReportVO> report = reportService.readAllReports(email);
		// 로그 생성
		logger.info(" /report/{}/all REST-API GET method called. then method executed.",email);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		headers.set("My-Header", "MyHeaderValue");
		return new ResponseEntity<>(report, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{email}/{date}/selection", method = RequestMethod.GET)
	public ResponseEntity<SleepReportVO> readSelectReport(@ModelAttribute SleepReportVO reportVO) throws Exception {
		
		SleepReportVO report = reportService.readSelectReport(reportVO);
		
		//logger.info(" /{}/{}/selection REST-API GET method called. then method executed.",reportVO.getEmail(),reportVO.getDate());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		headers.set("My-Header", "MyHeaderValue");
		return new ResponseEntity<SleepReportVO>(report, headers, HttpStatus.OK);
	}
	
	// 사용자 정의 범위
	@RequestMapping(value = "/{email}/{date}/{end}/selection", method = RequestMethod.GET)
	public ResponseEntity<SleepReportVO> readSelectReport(@ModelAttribute SleepReportVO2 reportVO) throws Exception {
		logger.info(reportVO.getDate());
		logger.info(reportVO.getEnd());
		logger.info(" /{}/{}/selection REST-API GET method called. then method executed.",reportVO.getDate(),reportVO.getEnd());
		SleepReportVO report = reportService.readPeriodReport(reportVO);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		headers.set("My-Header", "MyHeaderValue");
		return new ResponseEntity<SleepReportVO>(report, headers, HttpStatus.OK);
	}
	
}
