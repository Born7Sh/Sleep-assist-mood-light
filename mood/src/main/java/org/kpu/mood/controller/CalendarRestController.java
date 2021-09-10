package org.kpu.mood.controller;


import java.nio.charset.Charset;
import java.util.List;

import org.kpu.mood.domain.CalendarVO;
import org.kpu.mood.service.CalendarService;
import org.kpu.mood.service.SleepReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ComponentScan({"org.kpu.mood.service"})
@RequestMapping(value="/calendar")
public class CalendarRestController {

	@Autowired
	private CalendarService calendarService;
	
	private static final Logger logger = LoggerFactory.getLogger(CalendarRestController.class);
	
	@RequestMapping(method = RequestMethod.POST)
	public String insertPlan(@RequestBody CalendarVO calendarVO) throws Exception {
		logger.info(" /status REST-API POST method called. then method executed.");
		calendarService.insertPlan(calendarVO);
		return "OK";
	}
	

	// 사용자 정의 범위
	@RequestMapping(value = "/{email}/list/", method = RequestMethod.GET)
	public ResponseEntity<List<CalendarVO>> readSelectCalendar(@PathVariable String email) throws Exception {
		logger.info(" /list/{} REST-API GET method called. then method executed.",email);
		List<CalendarVO> calendar = calendarService.readList(email);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		headers.set("My-Header", "MyHeaderValue");
		return new ResponseEntity<>(calendar, headers, HttpStatus.OK);
	}
	
}
