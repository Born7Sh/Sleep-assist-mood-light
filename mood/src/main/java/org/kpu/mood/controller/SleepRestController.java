package org.kpu.mood.controller;

import org.kpu.mood.domain.SleepReportVO;
import org.kpu.mood.domain.SleepVO;
import org.kpu.mood.service.SleepService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ComponentScan({"org.kpu.mood.service"})
@RequestMapping("/sleep")
public class SleepRestController {
	
	@Autowired
	private SleepService sleepService;
	
	private static final Logger logger = LoggerFactory.getLogger(ReportRestController.class);
	
	@RequestMapping(method = RequestMethod.POST)
	public int insertSleep(@RequestBody SleepVO sleepVO) throws Exception {
		logger.info(" /status REST-API POST method called. then method executed.");
		return sleepService.insertSleep(sleepVO);
	}
	
	@RequestMapping(value = "/update/", method = RequestMethod.POST)
	public String updateSleep(@RequestBody SleepVO sleepVO) throws Exception {
		logger.info(" /status REST-API POST method called. then method executed.");
		sleepService.updateSleep(sleepVO);
		return "OK";
	}
	
}
