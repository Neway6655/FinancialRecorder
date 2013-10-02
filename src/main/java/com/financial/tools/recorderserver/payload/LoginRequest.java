package com.financial.tools.recorderserver.payload;

import java.io.Serializable;

public class LoginRequest implements Serializable {

	private static final long serialVersionUID = -4334272403529534510L;

	private String userName;

	private String password;

	public LoginRequest() {
	}

	public LoginRequest(String userName, String password) {
		super();
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
