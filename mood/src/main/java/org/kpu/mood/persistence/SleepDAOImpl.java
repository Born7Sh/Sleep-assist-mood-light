package org.kpu.mood.persistence;

import org.apache.ibatis.session.SqlSession;
import org.kpu.mood.domain.SleepVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SleepDAOImpl implements SleepDAO{
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "org.kpu.sleepapp.mapper.SleepMapper";
	
	public int insert(SleepVO sleepVO) throws Exception {
		sqlSession.insert(namespace+".insert", sleepVO);
		System.out.println("check1");
		return sqlSession.selectOne(namespace+".selectId", sleepVO);
	}
	
	public void update(SleepVO sleepVO) throws Exception {
		sqlSession.update(namespace+".update", sleepVO);
	}
}
