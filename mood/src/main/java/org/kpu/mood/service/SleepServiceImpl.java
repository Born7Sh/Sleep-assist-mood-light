package org.kpu.mood.service;

import org.kpu.mood.domain.SleepVO;
import org.kpu.mood.persistence.SleepDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan({"org.kpu.mood.persistence"})
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
