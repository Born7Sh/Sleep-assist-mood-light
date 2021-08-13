package org.kpu.sleepapp.controller;


import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.kpu.sleepapp.domain.DiaryVO;
import org.kpu.sleepapp.domain.SleepReportVO;
import org.kpu.sleepapp.domain.SleepStatusVO;
import org.kpu.sleepapp.service.DiaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value="/diary")
public class DiaryRestController 
{
	@Autowired
	private DiaryService diaryService;
	
	private static final Logger logger = LoggerFactory.getLogger(ReportRestController.class);
	
	@RequestMapping(value = "{email}/all", method = RequestMethod.GET)
	public ResponseEntity<List<DiaryVO>> readAllDiary(@PathVariable String email) throws Exception {
		//report에 필요한 값 저장 후 서비스 레이어에서 처리 요청
		List<DiaryVO> list = new ArrayList<DiaryVO>();
		list = diaryService.readDiary(email);
		// 로그 생성
		logger.info(" /diary/{}/all REST-API GET method called. then method executed.",email);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		headers.set("My-Header", "MyHeaderValue");
		//return new ResponseEntity<SleepReportVO>(report, headers, HttpStatus.OK);
		// 이거와 차이를 좀 알았으면 좋겠음.
		return new ResponseEntity<>(list, headers, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String insertDiary(@RequestBody DiaryVO diaryVO) throws Exception {
		logger.info(" /diary REST-API POST method called. then method executed.");
		diaryService.insertDiary(diaryVO);
		return "OK";
	}
}
