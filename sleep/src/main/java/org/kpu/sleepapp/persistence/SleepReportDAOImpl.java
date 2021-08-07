package org.kpu.sleepapp.persistence;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.kpu.sleepapp.domain.ElementsVO;
import org.kpu.sleepapp.domain.SleepReportVO;
import org.kpu.sleepapp.domain.SleepReportVO2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SleepReportDAOImpl implements SleepReportDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "org.kpu.sleepapp.mapper.SRMapper";
	
	public SleepReportVO readToday(SleepReportVO sleep_reportVO) throws Exception {
		SleepReportVO vo = sqlSession.selectOne(namespace+".selectByEmailToday", sleep_reportVO);
		return vo;   
	}
	
	public List<SleepReportVO> readAll(String email) throws Exception{
		List<SleepReportVO> list = new ArrayList<SleepReportVO>();
		list = sqlSession.selectList(namespace + ".selectByEmailAll", email);
		return list;
	}
	
	public List<SleepReportVO> readPeriod(SleepReportVO2 reportVO) throws Exception{
		List<SleepReportVO> list = new ArrayList<SleepReportVO>();
		list = sqlSession.selectList(namespace + ".selectByEmailDateValue", reportVO);
		return list;
	}
	
	//이메일과 날짜 범위 조건에 해당되는 원소값을 보내는 메소드
	public List<ElementsVO> readElements(SleepReportVO2 sleep_reportVO) throws Exception{
		List<ElementsVO> list = new ArrayList<ElementsVO>();
		System.out.println(sleep_reportVO.getDate());
		System.out.println(sleep_reportVO.getEnd());
		System.out.println(sleep_reportVO.getEmail());
		list = sqlSession.selectList(namespace + ".selectByEmailPeriodElements", sleep_reportVO);
		return list;
 	}
	
	//이메일과 날짜 조건에 해당되는 원소값을 보내는 메소드
	public List<ElementsVO> readElements(SleepReportVO sleep_reportVO) throws Exception{
		List<ElementsVO> list = new ArrayList<ElementsVO>();
		list = sqlSession.selectList(namespace + ".selectByEmailDateElements", sleep_reportVO);
		return list;
 	}
	
	//이메일에 해당되는 전체 원소값을 보내는 메소드
	public List<ElementsVO> readElements(String email) throws Exception{
		List<ElementsVO> list = new ArrayList<ElementsVO>();
		list = sqlSession.selectList(namespace + ".selectByEmailElements", email);
		return list;
 	}
} 
