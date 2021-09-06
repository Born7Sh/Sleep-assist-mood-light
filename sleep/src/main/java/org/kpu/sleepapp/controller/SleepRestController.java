package org.kpu.sleepapp.controller;

import org.kpu.sleepapp.domain.SleepVO;
import org.kpu.sleepapp.service.SleepService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sleep")
public class SleepRestController {
	
	@Autowired
	private SleepService sleepService;
	
	private static final Logger logger = LoggerFactory.getLogger(ReportRestController.class);
	
	@RequestMapping(method = RequestMethod.POST)
	public String insertSleep(@RequestBody SleepVO sleepVO) throws Exception {
		logger.info(" /status REST-API POST method called. then method executed.");
		sleepService.insertSleep(sleepVO);
		return "OK";
	}
	
	
}
