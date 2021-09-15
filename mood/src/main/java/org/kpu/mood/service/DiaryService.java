package org.kpu.mood.service;

import java.util.List;

import org.kpu.mood.domain.DiaryVO;

public interface DiaryService {
	public List<DiaryVO> readDiary(String email) throws Exception;
	public void insertDiary(DiaryVO diaryVO) throws Exception;
}
