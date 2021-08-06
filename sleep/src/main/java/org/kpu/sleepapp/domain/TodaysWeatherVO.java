package org.kpu.sleepapp.domain;

public class TodaysWeatherVO {
	private String datetime;
	private float temperature;
	private int humidity;
	private int precipitation_type;
	private int fine_dust10;
	private int fine_dust2_5;
	
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public float getTemperature() {
		return temperature;
	}
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	public int getPrecipitation_type() {
		return precipitation_type;
	}
	public void setPrecipitation_type(int precipitation_type) {
		this.precipitation_type = precipitation_type;
	}
	public int getFine_dust10() {
		return fine_dust10;
	}
	public void setFine_dust10(int fine_dust10) {
		this.fine_dust10 = fine_dust10;
	}
	public int getFine_dust2_5() {
		return fine_dust2_5;
	}
	public void setFine_dust2_5(int fine_dust2_5) {
		this.fine_dust2_5 = fine_dust2_5;
	}
}
