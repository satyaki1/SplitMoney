package com.splitmoney.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Expenditure {

	private String expenditureId;
	private String groupId;
	private String userId;
	private String spendingName;
	private float spendingAmount;
	
	public Expenditure(String expenditureId, String groupId, String userId,
			String spendingName, float spendingAmount) {
		super();
		this.expenditureId = expenditureId;
		this.groupId = groupId;
		this.userId = userId;
		this.spendingName = spendingName;
		this.spendingAmount = spendingAmount;
	}
	
	public Expenditure() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getExpenditureId() {
		return expenditureId;
	}
	public void setExpenditureId(String expenditureId) {
		this.expenditureId = expenditureId;
	}

	public String getSpendingName() {
		return spendingName;
	}
	public void setSpendingName(String spendingName) {
		this.spendingName = spendingName;
	}
	public float getSpendingAmount() {
		return spendingAmount;
	}
	public void setSpendingAmount(float spendingAmount) {
		this.spendingAmount = spendingAmount;
	}
}
