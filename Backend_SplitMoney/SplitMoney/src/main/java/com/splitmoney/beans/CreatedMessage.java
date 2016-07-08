package com.splitmoney.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CreatedMessage {

	private String message;

	public CreatedMessage(String message) {
		super();
		this.message = message;
	}

	public CreatedMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
