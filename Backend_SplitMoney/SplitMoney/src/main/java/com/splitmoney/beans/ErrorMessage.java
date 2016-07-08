package com.splitmoney.beans;

public class ErrorMessage {

	private String error;
	private int status;
	private String description;
	
	public ErrorMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ErrorMessage(String error, int status, String description) {
		super();
		this.error = error;
		this.status = status;
		this.description = description;
	}
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
