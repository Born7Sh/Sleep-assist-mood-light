package org.kpu.sleepapp.domain;

public class SleepStatusVO {
	private int id;
	private float shallow_sleep;
	private float deep_sleep;
	private float gyro_sensor;
	private String sound;
	private String email;
	private String datetime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getShallow_sleep() {
		return shallow_sleep;
	}
	public void setShallow_sleep(float shallow_sleep) {
		this.shallow_sleep = shallow_sleep;
	}
	public float getDeep_sleep() {
		return deep_sleep;
	}
	public void setDeep_sleep(float deep_sleep) {
		this.deep_sleep = deep_sleep;
	}
	public float getGyro_sensor() {
		return gyro_sensor;
	}
	public void setGyro_sensor(float gyro_sensor) {
		this.gyro_sensor = gyro_sensor;
	}
	public String getSound() {
		return sound;
	}
	public void setSound(String sound) {
		this.sound = sound;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	
	
}
