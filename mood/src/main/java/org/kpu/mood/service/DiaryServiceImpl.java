package org.kpu.mood.service;

import java.util.List;

import org.kpu.mood.domain.DiaryVO;
import org.kpu.mood.persistence.DiaryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan({"org.kpu.mood.persistence"})
public class DiaryServiceImpl implements DiaryService{
	@Autowired
	private DiaryDAO diaryDAO;
	
	public List<DiaryVO> readDiary(String email) throws Exception{
		return diaryDAO.readAll(email);
	}
	
	public void insertDiary(DiaryVO diaryVO) throws Exception {
		diaryDAO.insertDiary(diaryVO);
	}
}
