package org.kpu.sleepapp.service;

import java.util.List;

import org.kpu.sleepapp.domain.WeatherVO;

public interface WeatherService {
	public void getNowWeather() throws Exception;
	public void getTomWeather() throws Exception;
}
