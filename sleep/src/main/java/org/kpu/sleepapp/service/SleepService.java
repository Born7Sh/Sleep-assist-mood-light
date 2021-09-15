package org.kpu.sleepapp.service;

import org.kpu.sleepapp.domain.SleepVO;

public interface SleepService {
	public int insertSleep(SleepVO sleepVO) throws Exception;
	public void updateSleep(SleepVO sleepVO) throws Exception;
}
