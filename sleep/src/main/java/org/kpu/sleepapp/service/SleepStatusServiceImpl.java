package org.kpu.sleepapp.service;

import org.kpu.sleepapp.domain.SleepStatusVO;
import org.kpu.sleepapp.persistence.SleepStatusDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SleepStatusServiceImpl implements SleepStatusService {
	
	@Autowired
	private SleepStatusDAO statusDAO;
	
	public void insertStatus(SleepStatusVO statusVO) throws Exception {
		statusDAO.insert(statusVO);
	}
}
