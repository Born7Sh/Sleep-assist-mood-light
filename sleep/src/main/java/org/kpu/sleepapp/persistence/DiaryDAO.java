package org.kpu.sleepapp.persistence;

import java.util.List;

import org.kpu.sleepapp.domain.DiaryVO;

public interface DiaryDAO {
	public List<DiaryVO> readAll(String email) throws Exception;
	public void insertDiary(DiaryVO diaryVO)throws Exception;
}
