package org.kpu.sleepapp.service;

import java.util.List;

import org.kpu.sleepapp.domain.DiaryVO;
import org.kpu.sleepapp.persistence.DiaryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
