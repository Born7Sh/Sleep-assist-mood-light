package org.kpu.mood.persistence;

import org.kpu.mood.domain.SleepVO;

public interface SleepDAO {
	public int insert(SleepVO sleepVO) throws Exception;
	public void update(SleepVO sleepVO) throws Exception;
}
