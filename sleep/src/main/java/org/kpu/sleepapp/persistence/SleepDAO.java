package org.kpu.sleepapp.persistence;

import org.kpu.sleepapp.domain.SleepVO;

public interface SleepDAO {
	public int insert(SleepVO sleepVO) throws Exception;
	public void update(SleepVO sleepVO) throws Exception;
}
