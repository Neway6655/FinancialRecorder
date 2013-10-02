package com.financial.tools.recorderserver.payload;


public class CashinRequest {

	private String userName;

	private float amount;

	public CashinRequest() {
	}

	public CashinRequest(String userName, long amount) {
		this.userName = userName;
		this.amount = amount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

}
