package org.kpu.sleepapp.persistence;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.kpu.sleepapp.domain.DiaryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DiaryDAOImpl implements DiaryDAO{
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "org.kpu.sleepapp.mapper.DiaryMapper";
	
	public List<DiaryVO> readAll(String email) throws Exception{
		List<DiaryVO> diaryVO = new ArrayList<DiaryVO>();
		diaryVO = sqlSession.selectList(namespace + ".selectbyEmailAll", email);
		return diaryVO;
	}
	
	public void insertDiary(DiaryVO diaryVO)throws Exception {
		sqlSession.insert(namespace + ".insert", diaryVO);
	}
	
	
}
