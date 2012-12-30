package com.financial.tools.recorderserver.payload;

import java.io.Serializable;

public class RegistrationRequest implements Serializable {

	private static final long serialVersionUID = 93505359143849538L;

	private String userName;

	private String password;

	public RegistrationRequest() {
	}

	public RegistrationRequest(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
