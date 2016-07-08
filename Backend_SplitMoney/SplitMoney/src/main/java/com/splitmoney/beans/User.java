package com.splitmoney.beans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	
	private String userId;
	private String fName;
	private String lName;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(String fName, String lName) {
		super();
		this.userId = "user-"+fName+"-"+lName;
		this.fName = fName;
		this.lName = lName;
	}

	public User(String userId, String fName, String lName) {
		super();
		this.userId = userId;
		this.fName = fName;
		this.lName = lName;
	}

	public String getUserId() {
		return userId;
	}

	@XmlElement
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getfName() {
		return fName;
	}

	@XmlElement
	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	@XmlElement
	public void setlName(String lName) {
		this.lName = lName;
	}
		
}
