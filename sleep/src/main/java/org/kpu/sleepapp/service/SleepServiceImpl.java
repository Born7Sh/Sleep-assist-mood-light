package org.kpu.sleepapp.service;

import org.kpu.sleepapp.domain.SleepVO;
import org.kpu.sleepapp.persistence.SleepDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SleepServiceImpl implements SleepService {
	
	@Autowired
	private SleepDAO sleepDAO;
	
	public void insertSleep(SleepVO sleepVO) throws Exception {
		sleepDAO.insert(sleepVO);
	}
	
}
