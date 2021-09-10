package org.kpu.mood.domain;

public class SleepReportVO2 {

	private String email;
	private int score;
	private float sleeping_time;
	private String element;
	private String date;
	private String end;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
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
	public String getElement() {
		return element;
	}
	public void setElements(String element) {
		this.element = element;
	}

}
