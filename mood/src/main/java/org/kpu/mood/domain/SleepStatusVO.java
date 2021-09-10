package org.kpu.mood.domain;

public class SleepStatusVO {
	private float x;
	private float y;
	private float gyro_sensor;
	private int sleep_id;
	private String time;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getSleep_id() {
		return sleep_id;
	}
	public void setSleep_id(int sleep_id) {
		this.sleep_id = sleep_id;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getGyro_sensor() {
		return gyro_sensor;
	}
	public void setGyro_sensor(float gyro_sensor) {
		this.gyro_sensor = gyro_sensor;
	}

	
	
}
