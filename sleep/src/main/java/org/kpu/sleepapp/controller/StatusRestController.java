package org.kpu.sleepapp.controller;

import java.nio.charset.Charset;


import org.kpu.sleepapp.domain.SleepStatusVO;
import org.kpu.sleepapp.service.SleepStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class StatusRestController {
	
	@Autowired
	private SleepStatusService statusService;
	
	private static final Logger logger = LoggerFactory.getLogger(ReportRestController.class);
	
	@RequestMapping(method = RequestMethod.POST)
	public String insertStatus(@RequestBody SleepStatusVO statusVO) throws Exception {
		logger.info(" /status REST-API GET method called. then method executed.");
		statusService.insertStatus(statusVO);
		return "OK";
	}
}
