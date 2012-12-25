package com.financial.tools.recorderserver.payload;

public class UserFinancialInfoResponse {

	private String userName;
	private float balance;

	public UserFinancialInfoResponse() {
	}

	public UserFinancialInfoResponse(String userName, float balance) {
		super();
		this.userName = userName;
		this.balance = balance;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

}
