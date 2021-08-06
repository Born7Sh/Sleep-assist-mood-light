package org.kpu.sleepapp.persistence;

import org.apache.ibatis.session.SqlSession;
import org.kpu.sleepapp.domain.SleepReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SleepReportDAOImpl implements SleepReportDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "org.kpu.sleepapp.mapper.SRMapper";
	
	public SleepReportVO read(SleepReportVO sleep_reportVO) throws Exception {
		SleepReportVO vo = sqlSession.selectOne(namespace+".selectByEmail", sleep_reportVO);
		
		return vo;   
	}
}
