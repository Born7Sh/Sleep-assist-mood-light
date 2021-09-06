package org.kpu.sleepapp.persistence;

import org.apache.ibatis.session.SqlSession;
import org.kpu.sleepapp.domain.SleepVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SleepDAOImpl implements SleepDAO{
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "org.kpu.sleepapp.mapper.SleepMapper";
	
	public void insert(SleepVO sleepVO) throws Exception {
		sqlSession.insert(namespace+".insert", sleepVO);
	}
}
