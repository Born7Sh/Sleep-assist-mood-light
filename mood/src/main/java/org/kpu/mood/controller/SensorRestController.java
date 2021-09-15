package org.kpu.mood.controller;

import java.nio.charset.Charset;

import org.kpu.mood.domain.Hardware_Control_StatusVO;
import org.kpu.mood.domain.SleepReportVO;
import org.kpu.mood.service.SensorService;
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
public class SensorRestController {
	
	@Autowired
	private SensorService sensorService;
	
	private static final Logger logger = LoggerFactory.getLogger(ReportRestController.class);
	
	@RequestMapping(value = "/{email}/sensor", method = RequestMethod.GET)
	public ResponseEntity<Hardware_Control_StatusVO> readSensorData(@PathVariable String email) throws Exception {
		Hardware_Control_StatusVO hcsVO = new Hardware_Control_StatusVO();
		hcsVO = sensorService.readSensorData(email);
		logger.info(" /{}/sensor REST-API GET method called. then method executed.",email);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		headers.set("My-Header", "MyHeaderValue");
		return new ResponseEntity<Hardware_Control_StatusVO>(hcsVO, headers, HttpStatus.OK);
	}
}
