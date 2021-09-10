package org.kpu.mood.persistence;

import org.kpu.mood.domain.SleepStatusVO;

public interface SleepStatusDAO {
	public void insert(SleepStatusVO sleep_reportVO) throws Exception;
}
