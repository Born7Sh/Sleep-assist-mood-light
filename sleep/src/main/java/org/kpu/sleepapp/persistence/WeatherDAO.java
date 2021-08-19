package org.kpu.sleepapp.persistence;

import org.kpu.sleepapp.domain.WeatherVO;

public interface WeatherDAO {
	public void insertNow(WeatherVO weatherVO)throws Exception;
}
