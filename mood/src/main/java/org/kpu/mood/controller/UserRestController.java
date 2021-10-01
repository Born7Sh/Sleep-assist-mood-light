package org.kpu.mood.controller;

import org.kpu.mood.domain.SleepStatusVO;
import org.kpu.mood.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ComponentScan({"org.kpu.mood.service"})
@RequestMapping(value="/user")
public class UserRestController {
	@Autowired
	private UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(ReportRestController.class);
	
	@RequestMapping(value = "/{email}/delete", method = RequestMethod.GET)
	public String deleteUser(@PathVariable String email) throws Exception {
		logger.info(" /status REST-API POST method called. then method executed.");
		userService.deleteUser(email);
		return "OK";
	}
}
