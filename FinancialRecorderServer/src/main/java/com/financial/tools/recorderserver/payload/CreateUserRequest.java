package com.financial.tools.recorderserver.payload;

public class CreateUserRequest {

	private String name;

	private String password;

	private long balance;

	private int type;

	public CreateUserRequest() {
	}

	public CreateUserRequest(String name, String password, long balance, int type) {
		this.name = name;
		this.password = password;
		this.balance = balance;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
