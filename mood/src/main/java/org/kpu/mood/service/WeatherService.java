package org.kpu.mood.service;

import java.util.List;

import org.kpu.mood.domain.WeatherVO;

public interface WeatherService {
	public void getNowWeather() throws Exception;
	public void getTomWeather() throws Exception;
	public List<WeatherVO> readForecastWeather() throws Exception;
	public WeatherVO readNowWeather() throws Exception;
	public void getNowAirPollution() throws Exception;
}
