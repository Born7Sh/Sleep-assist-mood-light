package org.kpu.sleepapp.service;

import java.util.List;

import org.kpu.sleepapp.domain.DiaryVO;

public interface DiaryService {
	public List<DiaryVO> readDiary(String email) throws Exception;
	public void insertDiary(DiaryVO diaryVO) throws Exception;
}
