package org.kpu.mood.controller;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.kpu.mood.domain.WeatherVO;
import org.kpu.mood.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ComponentScan({"org.kpu.mood.service"})
@RequestMapping("/weather")
public class WeatherRestController {
	
	@Autowired
	private WeatherService weatherService;
	
	private static final Logger logger = LoggerFactory.getLogger(ReportRestController.class);
	
	@RequestMapping(value = "/forecast", method = RequestMethod.GET)
	public ResponseEntity<List<WeatherVO>> readAllForecast() throws Exception {
		//report에 필요한 값 저장 후 서비스 레이어에서 처리 요청
		List<WeatherVO> list = new ArrayList<WeatherVO>();
		list = weatherService.readForecastWeather();
		// 로그 생성
		logger.info(" /weather/forecast REST-API GET method called. then method executed.");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		headers.set("My-Header", "MyHeaderValue");
		//return new ResponseEntity<SleepReportVO>(report, headers, HttpStatus.OK);
		// 이거와 차이를 좀 알았으면 좋겠음.
		return new ResponseEntity<>(list, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/now", method = RequestMethod.GET)
	public ResponseEntity<WeatherVO> readNowWeather() throws Exception {
		//report에 필요한 값 저장 후 서비스 레이어에서 처리 요청
		WeatherVO weatherVO = weatherService.readNowWeather();
		// 로그 생성
		logger.info(" /weather/now REST-API GET method called. then method executed.");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		headers.set("My-Header", "MyHeaderValue");
		//return new ResponseEntity<SleepReportVO>(report, headers, HttpStatus.OK);
		// 이거와 차이를 좀 알았으면 좋겠음.
		return new ResponseEntity<>(weatherVO, headers, HttpStatus.OK);
	}
}
