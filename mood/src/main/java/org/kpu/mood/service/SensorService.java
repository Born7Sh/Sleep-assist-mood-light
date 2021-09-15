package org.kpu.mood.service;

import org.kpu.mood.domain.Hardware_Control_StatusVO;

public interface SensorService {
	public Hardware_Control_StatusVO readSensorData(String Email) throws Exception;
}
