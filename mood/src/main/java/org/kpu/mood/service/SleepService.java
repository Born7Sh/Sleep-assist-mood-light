package org.kpu.mood.service;

import org.kpu.mood.domain.SleepVO;

public interface SleepService {
	public int insertSleep(SleepVO sleepVO) throws Exception;
	public void updateSleep(SleepVO sleepVO) throws Exception;
}
