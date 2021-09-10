package org.kpu.mood.persistence;

import org.apache.ibatis.session.SqlSession;
import org.kpu.mood.domain.SleepStatusVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SleepStatusDAOImpl implements SleepStatusDAO{
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "org.kpu.sleepapp.mapper.SSMapper";
	
	public void insert(SleepStatusVO sleep_reportVO) throws Exception {
		sqlSession.insert(namespace+".insert", sleep_reportVO);
	}
}
