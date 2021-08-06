package org.kpu.sleepapp.controller;


import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.kpu.sleepapp.domain.SleepReportVO;
import org.kpu.sleepapp.service.SleepReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/report")
public class ReportRestController {
	
	@Autowired
	private SleepReportService reportService;
	
	private static final Logger logger = LoggerFactory.getLogger(ReportRestController.class);
	
	@RequestMapping(value = "/{email}/email", method = RequestMethod.GET)
	public ResponseEntity<SleepReportVO> readMember(@PathVariable String email) throws Exception {
		//report에 필요한 값 저장 후 서비스 레이어에서 처리 요청

		
		SleepReportVO report = reportService.readReport(email);
		
		// 로그 생성
		logger.info(" /report/{id} REST-API GET method called. then method executed.");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		headers.set("My-Header", "MyHeaderValue");
		return new ResponseEntity<SleepReportVO>(report, headers, HttpStatus.OK);
	}
	
}
