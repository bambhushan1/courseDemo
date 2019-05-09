package com.course.demo.exception;

public class CourseDemoException  extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;

	public CourseDemoException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
