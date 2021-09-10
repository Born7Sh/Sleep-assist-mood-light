package org.kpu.sleepapp.service;

import org.kpu.sleepapp.domain.SleepVO;
import org.kpu.sleepapp.persistence.SleepDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SleepServiceImpl implements SleepService {
	
	@Autowired
	private SleepDAO sleepDAO;
	
	public int insertSleep(SleepVO sleepVO) throws Exception {
		return sleepDAO.insert(sleepVO);
	}
	public void updateSleep(SleepVO sleepVO) throws Exception {
		sleepDAO.update(sleepVO);
	}
}
