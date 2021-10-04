package org.kpu.mood.domain;

public class SleepVO {
	private int id;
	private String start;
	private String end;
	private int elements;
	private String email;
	private String alarm_time;
	
	public String getAlarm_time() {
		return alarm_time;
	}
	public void setAlarm_time(String alarm_time) {
		this.alarm_time = alarm_time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public int getElements() {
		return elements;
	}
	public void setElements(int elements) {
		this.elements = elements;
	}

	
	
}
