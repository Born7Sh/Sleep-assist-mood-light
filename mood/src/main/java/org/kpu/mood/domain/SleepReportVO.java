package org.kpu.mood.domain;

public class SleepReportVO {

	private int sleepid;
	private int score;
	private float sleeping_time;
	private String elements;

	
	public String getElements() {
		return elements;
	}
	public void setElements(String elements) {
		this.elements = elements;
	}

	public int getSleepid() {
		return sleepid;
	}
	public void setSleepid(int sleepid) {
		this.sleepid = sleepid;
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


}
