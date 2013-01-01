package com.financial.tools.recorderserver.payload;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse implements Serializable {

	private static final long serialVersionUID = -5687260564639507925L;

	@JsonProperty("name")
	private String userName;

	@JsonProperty("balance")
	private float userBalance;

	@JsonProperty("type")
	private int userType;

	public LoginResponse() {
	}

	public LoginResponse(String userName, float userBalance, int userType) {
		super();
		this.userName = userName;
		this.userBalance = userBalance;
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public float getUserBalance() {
		return userBalance;
	}

	public void setUserBalance(float userBalance) {
		this.userBalance = userBalance;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

}
