package org.kpu.sleepapp.domain;

public class SleepReportVO {
	private int id;
	private String email;
	private int score;
	private float sleeping_time;
	private int elements;
	private String date;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	public float getSleeping_time() {
		return sleeping_time;
	}
	public void setSleeping_time(float sleeping_time) {
		this.sleeping_time = sleeping_time;
	}
	public int getElements() {
		return elements;
	}
	public void setElements(int elements) {
		this.elements = elements;
	}

}
