package org.kpu.mood.service;

import org.kpu.mood.domain.SleepStatusVO;
import org.kpu.mood.persistence.SleepStatusDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan({"org.kpu.mood.persistence"})
public class SleepStatusServiceImpl implements SleepStatusService {
	
	@Autowired
	private SleepStatusDAO statusDAO;
	
	public void insertStatus(SleepStatusVO statusVO) throws Exception {
		statusDAO.insert(statusVO);
	}
}
