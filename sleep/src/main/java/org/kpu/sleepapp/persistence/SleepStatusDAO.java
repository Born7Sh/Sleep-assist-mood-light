package org.kpu.sleepapp.persistence;

import org.kpu.sleepapp.domain.SleepStatusVO;

public interface SleepStatusDAO {
	public void insert(SleepStatusVO sleep_reportVO) throws Exception;
}
