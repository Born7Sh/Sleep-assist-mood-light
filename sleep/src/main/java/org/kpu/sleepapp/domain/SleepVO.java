package org.kpu.sleepapp.domain;

public class SleepVO {
	private int id;
	private String start;
	private String end;
	private int elements;
	private String email;
	
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
