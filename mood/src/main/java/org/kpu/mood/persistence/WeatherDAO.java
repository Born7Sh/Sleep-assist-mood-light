package org.kpu.mood.persistence;



import java.util.List;

import org.kpu.mood.domain.WeatherVO;

public interface WeatherDAO {
	public void insertNow(WeatherVO weatherVO)throws Exception;
	public void insertForecast(WeatherVO weatherVO)throws Exception;
	public List<WeatherVO> selectForecast() throws Exception;
	public WeatherVO selectNow() throws Exception;
}
