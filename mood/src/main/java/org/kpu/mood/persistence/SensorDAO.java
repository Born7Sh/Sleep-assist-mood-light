package org.kpu.mood.persistence;

import org.kpu.mood.domain.Hardware_Control_StatusVO;

public interface SensorDAO {
	public Hardware_Control_StatusVO readSensorData(String email) throws Exception;
}
