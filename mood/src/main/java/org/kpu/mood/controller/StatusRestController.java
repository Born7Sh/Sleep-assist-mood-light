package org.kpu.mood.controller;


import org.kpu.mood.domain.SleepStatusVO;
import org.kpu.mood.service.SleepStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ComponentScan({"org.kpu.mood.service"})
@RequestMapping("/status")
public class StatusRestController {
	
	@Autowired
	private SleepStatusService statusService;
	
	private static final Logger logger = LoggerFactory.getLogger(ReportRestController.class);
	
	@RequestMapping(method = RequestMethod.POST)
	public String insertStatus(@RequestBody SleepStatusVO statusVO) throws Exception {
		logger.info(" /status REST-API POST method called. then method executed.");
		statusService.insertStatus(statusVO);
		return "OK";
	}
	
	
}
