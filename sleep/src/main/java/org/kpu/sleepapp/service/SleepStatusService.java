package org.kpu.sleepapp.service;

import org.kpu.sleepapp.domain.SleepStatusVO;

public interface SleepStatusService {
	public void insertStatus(SleepStatusVO statusVO) throws Exception;
}
