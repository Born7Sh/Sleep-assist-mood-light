package org.kpu.mood.service;

import org.kpu.mood.domain.Hardware_Control_StatusVO;
import org.kpu.mood.persistence.SensorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SensorServiceImpl implements SensorService {
	
	@Autowired
	private SensorDAO sensorDAO;
	
	public Hardware_Control_StatusVO readSensorData(String Email) throws Exception{
		return sensorDAO.readSensorData(Email);
	}
	
}
