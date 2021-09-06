package org.kpu.sleepapp.persistence;

import org.kpu.sleepapp.domain.SleepVO;

public interface SleepDAO {
	public void insert(SleepVO sleepVO) throws Exception;
}
